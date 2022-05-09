package com.monitor.bankendmonitoreoLinks.security.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.monitor.bankendmonitoreoLinks.security.jwt.JwtEntryPoint;
import com.monitor.bankendmonitoreoLinks.security.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	JwtEntryPoint jwtEntryPoint;

	@Bean
	JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/estadoAnuncio/**").permitAll()
				.antMatchers("/adCreative/**").permitAll().antMatchers("/anuncio/**").permitAll()
				.antMatchers("/oauth/**").permitAll().antMatchers("/correoAlerta/**").permitAll()
				.antMatchers("/page/**").permitAll()
				.antMatchers("/tags/**").permitAll()
				.antMatchers("/ocr/**").permitAll()
				.antMatchers("/palabras/**").permitAll()
				.antMatchers("/linkExterno/**").permitAll()
				.antMatchers("/estadolinkExterno/**").permitAll()	
				.antMatchers("/cuentaExterna/**").permitAll()	
				.antMatchers("/revisarLinks/**").permitAll()	
				.antMatchers("/cuentaFB/**").permitAll().antMatchers("/users/**").permitAll().antMatchers("/post/**").permitAll()
				.antMatchers("/alerta/**").permitAll().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(jwtEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
