package will.cart2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //set dich vu de tim kiem USER trong Database
        //va set Password Encoder
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();

        // Các trang khong yeu cau login
        httpSecurity.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

        // trang /userInfo yeu cau phai login voi vai tro ROLE_USER hoac ROLE_ADMIN
        // neu chua login thi redirect ve trang login
        httpSecurity.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')");

        //trang chi danh cho admin
        httpSecurity.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");

        // khi nguoi dung  da login, voi vai tro XX
        // nhung truy cap voi vai tro YY
        // Ngoai le AccessDeniedException se nem ra
        httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        //cau hinh cho login form
        httpSecurity.authorizeRequests().and().formLogin()//
                // Submit URL cua trang login
                .loginProcessingUrl("/j_spring_security_check")//submit URL
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                //cau hinh cho logout page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

        //cau hinh remember me
        httpSecurity.authorizeRequests().and()
                .rememberMe().tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(1 * 24 * 60 * 60); //24h
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository () {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
