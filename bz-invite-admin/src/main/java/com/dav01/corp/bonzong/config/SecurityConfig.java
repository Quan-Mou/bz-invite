package com.dav01.corp.bonzong.config;

import com.dav01.corp.bonzong.handler.filter.JwtTokenFiler;
import com.dav01.corp.bonzong.service.impl.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtTokenFiler jwtTokenFiler;

    @Resource
    private CustomerUserDetailService customerUserDetailService;



//    @Autowired
//    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(customerUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/admin/login","/doc.html","/webjars/**","/swagger-resources/**","/v2/**").anonymous()
//                //注销接口需要认证才能访问
//                .antMatchers("/logout").authenticated()
//                .antMatchers("/user/userInfo").authenticated()
//                .antMatchers("/upload").authenticated()
                // 除上面外的所有请求全部需要认证
                .anyRequest().authenticated()
                        .and().sessionManagement()
                .invalidSessionUrl("/login")
                .maximumSessions(1)
                        .expiredUrl("/login");
//                                .sessionRegistry(sessionRegistry());

        //配置异常处理器
//        http.exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler);
        //关闭默认的注销功能
        http.logout().disable();
        //把jwtAuthenticationTokenFilter添加到SpringSecurity的过滤器链中
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //允许跨域
        http.cors();
        http.addFilterBefore(jwtTokenFiler, UsernamePasswordAuthenticationFilter.class);

//      去除默认登录页面
        http.removeConfigurer(DefaultLoginPageConfigurer.class);


//        http.sessionManagement(session -> {
//            session.maximumSessions(1)
//                    .sessionRegistry(sessionRegistry());
//        });
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
//        return new BCryptPasswordEncoder();




    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }



    @Bean
    // @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy informationExpiredStrategy(){
        return new MySessionInformationExpiredStrategy();
    }





}
