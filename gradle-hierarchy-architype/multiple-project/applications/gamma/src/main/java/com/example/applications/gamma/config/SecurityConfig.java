package com.example.applications.gamma.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.applications.gamma.path.StaticConst;
import com.example.cores.gamma.jpa.constant.FunctionType;
import com.example.cores.gamma.jpa.constant.RequestMethodType;
import com.example.cores.gamma.jpa.entity.AuthoritysFunction;
import com.example.cores.gamma.jpa.entity.Function;
import com.example.cores.gamma.jpa.entity.Member;
import com.example.cores.gamma.jpa.entity.MembersAuthority;
import com.example.cores.gamma.jpa.repository.AuthoritysFunctionRepository;
import com.example.cores.gamma.jpa.repository.FunctionRepository;
import com.example.cores.gamma.jpa.repository.MemberRepository;
import com.example.cores.gamma.jpa.repository.MembersAuthorityRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * SecurityConfig
 * 
 * @author _sCream
 * 
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String ROLE_NAME_PREFIX = "ROLE_"; 
    public static final String ROLE_SUPERUSER = "ROLE_SUPERUSER"; 

    /**
     * databaseAuthenticationProvider:
     * 
     * @param memberRepository
     * @param membersAuthorityRepository
     * @return
     */
    @Bean
    public AuthenticationProvider databaseAuthenticationProvider(MemberRepository memberRepository,
            MembersAuthorityRepository membersAuthorityRepository) {
        return new AuthenticationProvider() {

            /* (non-Javadoc)
             * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
             */
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                Member memberData = memberRepository.findById(authentication.getName()).orElseThrow(() -> {
                    log.info("cannot found member. {}", authentication.getName());
                    throw new BadCredentialsException("cannot found member.");
                });
                if (!memberData.getPassword().equals(authentication.getCredentials().toString())) {
                    log.info("invalid password.");
                    throw new InsufficientAuthenticationException("invalid password.");
                }

                MembersAuthority memberConditionData = new MembersAuthority();
                memberConditionData.setMemberId(authentication.getPrincipal().toString());
                List<MembersAuthority> foundMembersAuthorityList = membersAuthorityRepository.findAll(Example.of(memberConditionData));

                List<? extends GrantedAuthority> authorityList = foundMembersAuthorityList.stream()
                        .map(item -> new SimpleGrantedAuthority(ROLE_NAME_PREFIX + item.getAuthorityId()))
                        .collect(Collectors.toList());

                UserDetails userDetail = new User(authentication.getName(), authentication.getCredentials().toString(),
                        authorityList);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
                authenticationToken.setDetails(userDetail);

                return authenticationToken;
            }

            /* (non-Javadoc)
             * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
             */
            @Override
            public boolean supports(Class<?> authentication) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
            }
        };
    }

    /**
     * localAuthenticationProvider:
     * <ul>
     * <li>for local environment</li>
     * </ul>
     * 
     * @return
     */
    private AuthenticationProvider localAuthenticationProvider() {
        return new AuthenticationProvider() {

            /* (non-Javadoc)
             * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
             */
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                if (!"superuser".equalsIgnoreCase(authentication.getName())) {
                    throw new BadCredentialsException("not spueruser.");
                }

                UserDetails userDetail = new User(authentication.getName(), "",
                        Arrays.asList(new SimpleGrantedAuthority(ROLE_SUPERUSER)));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
                authenticationToken.setDetails(userDetail);

                return authenticationToken;
            }

            /* (non-Javadoc)
             * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
             */
            @Override
            public boolean supports(Class<?> authentication) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
            }
        };
    }

    /**
     * apiAuthenticationManagerForLocal:
     * <ul>
     * <li>for local environment</li>
     * </ul>
     * 
     * @param databaseAuthenticationProvider
     * @param localAuthenticationProvider
     * @return
     * @throws Exception
     */
    @Bean
    @Profile("local")
    public AuthenticationManager apiAuthenticationManagerForLocal(
            @Qualifier("databaseAuthenticationProvider") AuthenticationProvider databaseAuthenticationProvider) throws Exception {
        log.info("apiAuthenticationManagerForLocal : local");
        return new ProviderManager(localAuthenticationProvider(), databaseAuthenticationProvider);
    }

    /**
     * apiAuthenticationManagerForAny:
     * 
     * @param databaseAuthenticationProvider
     * @return
     * @throws Exception
     */
    @Bean
    @Profile("!local")
    public AuthenticationManager apiAuthenticationManagerForAny(
            @Qualifier("databaseAuthenticationProvider") AuthenticationProvider databaseAuthenticationProvider) throws Exception {
        log.info("apiAuthenticationManagerForLocal : not local");
        return new ProviderManager(databaseAuthenticationProvider);
    }

    /**
     * authenticationEntryPoint:
     * 
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {

            /* (non-Javadoc)
             * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
             */
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                    AuthenticationException authException) throws IOException, ServletException {
                if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                } else if (MediaType.TEXT_HTML_VALUE.equals(request.getContentType())) {
                    response.setContentType(MediaType.TEXT_HTML_VALUE);
                    response.sendRedirect(request.getContextPath() + "/ui/main");
                }
            }
        };
    }

    /**
     * loginSuccesshandler:
     * 
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccesshandler() {
        return new AuthenticationSuccessHandler() {

            /* (non-Javadoc)
             * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
             */
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                log.info("login success!!");
                response.sendRedirect(request.getContextPath() + "/ui/main");
                // todo..
            }
        };
    }

    /**
     * loginFailureHandler:
     * 
     * @return
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return new AuthenticationFailureHandler() {

            /* (non-Javadoc)
             * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
             */
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                    AuthenticationException exception) throws IOException, ServletException {
                log.info("login failure!!");
                response.sendRedirect(request.getContextPath() + "/auth/login?error=fail");
                //todo..
            }
            
        };
    }

    /**
     * logoutSuccessHandler:
     * 
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler () {

            /* (non-Javadoc)
             * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
             */
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                log.info("logout success!!");
                response.sendRedirect(request.getContextPath() + "/ui/main");
                // todo..
            }
            
        };
    }

    /**
     * pathAuthorizationManager:
     * 
     * @param functionRepository
     * @param authoritysFunctionRepository
     * @return
     */
    @Bean
    public AuthorizationManager<RequestAuthorizationContext> pathAuthorizationManager(FunctionRepository functionRepository,
            AuthoritysFunctionRepository authoritysFunctionRepository) {
        return new AuthorizationManager<RequestAuthorizationContext>() {

            /**
             * isAuthenticated:
             * 
             * @param authentication
             * @return
             */
            private boolean isAuthenticated(Authentication authentication) {
                return authentication != null && authentication.isAuthenticated();
            }

            /**
             * isAuthorized:
             * 
             * @param authentication
             * @param request
             * @return
             */
            private boolean isAuthorized(Authentication authentication, HttpServletRequest request) {
                final String servletPath = request.getServletPath().replaceFirst(StaticConst.API_PATH, "");
                String method = request.getMethod();
                log.info("request info : [{}] - {}", method, servletPath);

                if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                        .contains(ROLE_SUPERUSER)) {
                    log.info("accesss role : {}", ROLE_SUPERUSER);
                    return true;
                }

                Set<String> accessableAuthorities = new HashSet<>();
                List<Function> foundFunctionList = functionRepository.findAll();

                if (foundFunctionList != null && !foundFunctionList.isEmpty()) {
                    List<Integer> matchedFunctionIdList = foundFunctionList.stream()
                            .filter(item -> FunctionType.API == item.getType())
                            .filter(item -> RequestMethodType.valueOf(method) == item.getMethod())
                            .filter(item -> servletPath.matches(item.getPath().replaceAll("\\{[A-Za-z0-9]+\\}", "([a-zA-Z0-9-])+")))
                            .map(Function::getFunctionId)
                            .collect(Collectors.toList());

                    List<AuthoritysFunction> foundAuthoritysFunctionList = authoritysFunctionRepository.findByFunctionIdIn(matchedFunctionIdList);

                    if (foundAuthoritysFunctionList != null && !foundAuthoritysFunctionList.isEmpty()) {
                        foundAuthoritysFunctionList.forEach(item -> accessableAuthorities.add(ROLE_NAME_PREFIX + item.getAuthorityId()));
                    }
                }

                for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                    if (accessableAuthorities.contains(grantedAuthority.getAuthority())) {
                        log.info("access role : {}", grantedAuthority.getAuthority());
                        return true;
                    }
                }
                return false;
            }

            /* (non-Javadoc)
             * @see org.springframework.security.authorization.AuthorizationManager#check(java.util.function.Supplier, java.lang.Object)
             */
            @Override
            public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
                boolean granted = isAuthenticated(authentication.get()) && isAuthorized(authentication.get(), object.getRequest());
                return new AuthorizationDecision(granted);
            }
        };
    }
    
    /**
     * apiSecurityFilterChain:
     * 
     * @param http
     * @param authenticationManager
     * @param authenticationEntryPoint
     * @param loginSuccesshandler
     * @param loginFailureHandler
     * @param logoutSuccessHandler
     * @param authorizationManager
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http,
            AuthenticationManager authenticationManager,
            AuthenticationEntryPoint authenticationEntryPoint,
            AuthenticationSuccessHandler loginSuccesshandler,
            AuthenticationFailureHandler loginFailureHandler,
            LogoutSuccessHandler logoutSuccessHandler,
            AuthorizationManager<RequestAuthorizationContext> authorizationManager) throws Exception {
        http
            .authenticationManager(authenticationManager)
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
            .cors()
                .and()
            .csrf()
                .disable()
            .formLogin()
                .permitAll()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login-process")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(loginSuccesshandler)
                .failureHandler(loginFailureHandler)
                .and()
            .logout()
                .permitAll()
                .logoutUrl("/auth/logout-process")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
            .authorizeHttpRequests()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/monitoring/**").permitAll()
                .antMatchers("/ui/**").permitAll()
                .antMatchers("/api/**").access(authorizationManager)
                .anyRequest().denyAll()
                .and();

        return http.build();
    }
}
