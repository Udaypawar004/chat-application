import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig{

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeHttpRequests(authorize->authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll()
        ).addFilterBefore(null, null)
        .csrf().disable()
        .cors().ConfigurationSource(new CorsConfigurationSource() {

            @Override
            public CorsConfiguration getCorsConfiguration(ServerWebExchange arg0) {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.setAllowedOrigins(Arrays.asList(
                    "http://localhost:3000/"
                ));
                corsConfig.setAllowedMethods(Collections.singletonList("*"));
                corsConfig.setAllowCredentials(true);
                corsConfig.setAllowedHeaders(Collections.singletonList("*"));


                throw new UnsupportedOperationException("Unimplemented method 'getCorsConfiguration'");
            }
            
        })
        .and().formLogin().and().httpBasic();

        return http.build();
    }
} 