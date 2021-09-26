package com.asusoftware.tacocloud.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   /* Is used for jdbc user store
   @Autowired
    DataSource dataSource;
    */

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      /*  In Memory user store (For static user that cannot change in the time)
      auth
                .inMemoryAuthentication()
                .withUser("buzz")
                .password("infinity")
                .authorities("ROLE_USER")
                .and()
                .withUser("woody")
                .password("bullseye")
                .authorities("ROLE_USER"); */

     /*   Jdbc user store
     auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from Users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from UserAuthorities where username=?"); */


        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* Prima variante
       http
                .authorizeRequests() // Ci permette di specificare i path i pattern e le security requirements per quei path
                .antMatchers("/design", "/orders") // Specifica i path per i quale si vuole aggiungere una specifica security
                // Permette l'accesso se l'utente ha questo ruolo
                .hasRole("ROLE_USER") // Devono essere per gli utenti che hanno il granted role su ROLE_USER
                .antMatchers("/", "/**") // Questi path
                .permitAll(); // sono permessi per tutti gli utenti */

        /* Seconda varinate con SpEl expression, ti permette di definire piu espressioni per i path specifici */
        http
                .authorizeRequests()
                .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**")
                .access("permitAll");

        /* Se vogliamo permettere agli utenti con il role di user di creare i taco on Tuesday

        http
                .authorizeRequests()
                .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER') && " +
                        "T(java.util.Calendar).getInstance().get(" +
                        "T(java.util.Calendar).DAY_OF_WEEK) == " +
                        "T(java.util.Calendar).TUESDAY")
                .antMatchers("/", "/**")
                .access("permitAll"); */
    }
}
