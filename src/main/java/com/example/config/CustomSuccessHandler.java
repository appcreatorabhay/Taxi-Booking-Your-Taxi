package com.example.config;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String selectedRole = request.getParameter("role"); // from login form

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String actualRole = authority.getAuthority();

            // 🔐 Check if selected role matches actual user role
            if (!actualRole.equals(selectedRole)) {
                // Logout user immediately due to mismatch
                request.getSession().invalidate();
                response.sendRedirect("/login?error=wrongrole");
                return;
            }

            // ✅ Role matches → redirect
            if (actualRole.equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin/dashboard");
                return;
            } else if (actualRole.equals("ROLE_USER")) {
                response.sendRedirect("/");
                return;
            }
        }

        // fallback
        response.sendRedirect("/login?error=unknown");
    }
}
