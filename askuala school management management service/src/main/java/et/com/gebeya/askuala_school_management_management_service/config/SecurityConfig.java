package et.com.gebeya.askuala_school_management_management_service.config;

import et.com.gebeya.askuala_school_management_management_service.security.RoleHeaderAuthenticationFilter;
import et.com.gebeya.askuala_school_management_management_service.security.RoleHeaderAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {

    protected static final String [] UNAUTHORIZED_MATCHERS = {
            "/api/v1/customer/register",
            "/api/v1/parking-lot/register"
    };

    protected static final String [] ADMIN_MATCHERS = {
            "/api/v1/customer/register",
            "/api/v1/parking-lot/register"
    };

    protected static final String [] STUDENT_MATCHERS = {
            "/api/v1/customer/register",
            "/api/v1/parking-lot/register"
    };

    protected static final String [] TEACHER_MATCHERS = {
            "/api/v1/customer/register",
            "/api/v1/parking-lot/register"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers(UNAUTHORIZED_MATCHERS).permitAll())
                .authorizeHttpRequests(request -> request.requestMatchers(ADMIN_MATCHERS).hasAuthority("PROVIDER"))
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
}
