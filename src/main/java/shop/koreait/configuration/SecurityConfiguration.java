package shop.koreait.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import shop.koreait.handler.CustomOAuth2FailureHandler;
import shop.koreait.handler.CustomOAuth2SuccessHandler;
import shop.koreait.service.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired CustomUserDetailsService userDetailsService;
    @Autowired DataSource dataSource; // application-properties에서 정의한 Database Connection
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(config -> config.disable());
//        http.httpBasic(Customizer.withDefaults());


        http.authorizeHttpRequests( (registry) -> {
            registry.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .requestMatchers("/user/login", "/user/signup", "/user/id/*").permitAll()
                    .anyRequest().authenticated();
        });

        http.oauth2Login(config -> {
            config.loginPage("/user/login");
            config.failureHandler(new CustomOAuth2FailureHandler());
            config.successHandler(new CustomOAuth2SuccessHandler()); // OAuth2 로그인이 성공했다면 실행되는 Handler를 등록
            config.permitAll();
        });


        http.formLogin((config) -> {
            config.loginPage("/user/login"); // FORM 로그인을 할 때 사용하는 Controller(html)경로
            config.usernameParameter("id"); // id 적는 input의 name 속성명 (username이 기본)
            config.passwordParameter("password"); // pw 적는 input의 name 속성명 (password가 기본)
            config.loginProcessingUrl("/user/login"); // 로그인 시도시, form 태그의 action 경로값
//            config.defaultSuccessUrl("/"); // 로그인이 성공 시 자동으로 이동하려는 GET 경로(Redirection)
            config.permitAll();
        });

        http.rememberMe(config -> {
            config.rememberMeParameter("remember_me");
            config.rememberMeCookieDomain("localhost");
            config.userDetailsService(userDetailsService); // 실제 로그인이 이루어지는 절차를 정의하는 Service 객체
            config.tokenRepository(persistentTokenRepository()); // 토큰을 저장하는 저장소 선택. Database를 선택함.
            config.tokenValiditySeconds(60 * 60 * 24 * 30); // 토큰 유효기간. 30일
        });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // remember-me 사용시의 token 저장소
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}
