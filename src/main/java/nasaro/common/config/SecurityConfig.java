package nasaro.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
	        .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
	        	.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
	        	.anyRequest().authenticated()
	        	)
	        
            .csrf((csrf) -> csrf
            	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            	.ignoringRequestMatchers(new AntPathRequestMatcher("/"))  // "/api/**" 경로에 대해 CSRF 비활성화
            )
            .headers((headers) -> headers
            	.frameOptions(frameOptions -> frameOptions.sameOrigin())
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        ;
        return http.build();
    }
    
    
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
