package et.com.gebeya.askuala_school_management_management_service.filter;

import et.com.gebeya.askuala_school_management_management_service.enums.Authority;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

//@Component
//@RequiredArgsConstructor
//public class RoleFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//        final String header = request.getHeader("Role");
//        if (StringUtils.isEmpty(header)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        if (header.equals(Authority.ADMIN.name()) || header.equals(Authority.TEACHER.name()) || header.equals(Authority.STUDENT.name())){
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(header);
//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                    null, null, Collections.singleton(authority));
//            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//            filterChain.doFilter(request, response);
//        }
//        else
//            filterChain.doFilter(request, response);
//
//    }
//}
