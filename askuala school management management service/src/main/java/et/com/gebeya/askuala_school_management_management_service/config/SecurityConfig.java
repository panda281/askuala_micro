package et.com.gebeya.askuala_school_management_management_service.config;

import et.com.gebeya.askuala_school_management_management_service.security.RoleHeaderAuthenticationFilter;
import et.com.gebeya.askuala_school_management_management_service.security.RoleHeaderAuthenticationProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {

    protected static final String [] UNAUTHORIZED_MATCHERS = {
            "/api/v1/customer/register",
            "/api/v1/parking-lot/register"
    };

    protected static final String [] ADMIN_MATCHERS = {
            "/api/v1/school/admins",
            "/api/v1/school/admins/id",
            "/api/v1/school/grade-sections",
            "/api/v1/school/grade-sections/**",
            "/api/v1/school/grade-sections/search",
            "/api/v1/school/teacher/grade-section",
            "/api/v1/school/students",
            "/api/v1/school/students/**",
            "/api/v1/school/subjects",
            "/api/v1/school/subjects/**",
            "/api/v1/school/subjects",
            "/api/v1/school/subjects/**",
            "/api/v1/school/teachers",
            "/api/v1/school/teachers/**"
    };

    protected static final String [] STUDENT_MATCHERS = {
            "/api/v1/school/grade-section/student",
            "/api/v1/school/attendance/student",
            "/api/v1/school/student/subject",
    };

    protected static final String [] TEACHER_MATCHERS = {
            "/api/v1/school/grade-section/teacher",
            "/api/v1/school/attendance/teacher",
            "/api/v1/school/attendances",
            "/api/v1/school/teacher/subject"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers(UNAUTHORIZED_MATCHERS).permitAll())
                .authorizeHttpRequests(request -> request.requestMatchers(ADMIN_MATCHERS).hasAuthority("ADMIN"))
                .authorizeHttpRequests(request -> request.requestMatchers(STUDENT_MATCHERS).hasAuthority("STUDENT"))
                .authorizeHttpRequests(request -> request.requestMatchers(TEACHER_MATCHERS).hasAuthority("TEACHER"))
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .exceptionHandling(handling -> {
                    handling.authenticationEntryPoint(unauthorizedEntryPoint());
                    handling.accessDeniedHandler(accessDeniedHandler());

                })
                .addFilterBefore(new RoleHeaderAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new RoleHeaderAuthenticationProvider();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) ->
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
