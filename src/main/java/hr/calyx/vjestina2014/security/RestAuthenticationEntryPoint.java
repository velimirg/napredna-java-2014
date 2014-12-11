package hr.calyx.vjestina2014.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.calyx.vjestina2014.config.SecurityConfig;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        String tokenString = request.getHeader(SecurityConfig.JWT_TOKEN_HEADER);
        if (StringUtils.hasText(tokenString)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                long exp = mapper.readValue(JwtHelper.decode(tokenString).getClaims(), JWTClaims.class).getExp();
                if (exp < System.currentTimeMillis()) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Expired token");
                    return;
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}