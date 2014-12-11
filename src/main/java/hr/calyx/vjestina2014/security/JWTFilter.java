package hr.calyx.vjestina2014.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.calyx.vjestina2014.config.SecurityConfig;
import hr.calyx.vjestina2014.services.RSAKeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by Tomislav on 12/8/2014.
 */

@Component
public class JWTFilter extends GenericFilterBean {
    @Autowired
    RSAKeysService rsaKeysService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String tokenString = httpServletRequest.getHeader(SecurityConfig.JWT_TOKEN_HEADER);
            if (StringUtils.hasText(tokenString)) {
                RsaVerifier verifier = new RsaVerifier((RSAPublicKey) rsaKeysService.getKeys().getPublic());
                try {
                    Jwt jwt = JwtHelper.decodeAndVerify(tokenString, verifier);

                    ObjectMapper mapper = new ObjectMapper();

                    JWTClaims claims = mapper.readValue(jwt.getClaims(), JWTClaims.class);

                    if (claims.getExp() > System.currentTimeMillis()) {
                        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(claims.getEmail(),
                                null, AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));

                        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                        SecurityContextHolder.getContext().setAuthentication(token);
                    } else {
                    }
                } catch (InvalidSignatureException e) {
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            filterChain.doFilter(request, response);
        }
    }
}
