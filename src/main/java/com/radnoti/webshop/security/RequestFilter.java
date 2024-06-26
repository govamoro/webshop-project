package com.radnoti.webshop.security;

import com.radnoti.webshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.aspectj.util.LangUtil.isEmpty;

@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImp userDetailsServiceImp;


    /**
     * Filters incoming requests and performs JWT authentication.
     *
     * @param request     The incoming HTTP request.
     * @param response    The HTTP response.
     * @param filterChain The filter chain to proceed with the request.
     * @throws ServletException If an error occurs during the filter execution.
     * @throws IOException      If an I/O error occurs during the filter execution.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isEmpty(authHeader) || !authHeader.startsWith("Bearer ") || !jwtUtil.validateJwt(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(jwtUtil.getUserNameFromJwt(authHeader));

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ? List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}