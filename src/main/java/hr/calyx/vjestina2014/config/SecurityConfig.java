package hr.calyx.vjestina2014.config;

import com.google.api.client.http.HttpMethods;
import hr.calyx.vjestina2014.security.JWTFilter;
import hr.calyx.vjestina2014.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Tomislav on 12/7/2014.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public final static String CLIENT_ID = "225925658800-aldm0vn094hmo30kh1cs2t475boaq7al.apps.googleusercontent.com";
    public final static String CLIENT_SECRET = "OdMD_TVeRrxbzkjgL04raVc0";

    public final static String JWT_TOKEN_HEADER = "token";

    public final static long TOKEN_EXPIRATION = 1000 * 60 * 30;

    @Autowired
    JWTFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/", "/bower_components/**", "/css/*", "/js/**", "/lib/**", "/partials/**", "/index.html").permitAll()
                .antMatchers("/tournaments-dummy").permitAll()
                .antMatchers("/tournaments/*", HttpMethods.GET).hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/tournament-templates", HttpMethods.GET).hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/tournament-templates/**/tournaments", HttpMethods.GET).hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/tournament-templates/**/tournaments", HttpMethods.POST).hasAnyAuthority("USER", "ADMIN")
                .anyRequest().hasAuthority("ADMIN");

        http.csrf().disable();

        http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());

    }

}
