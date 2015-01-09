package hr.calyx.vjestina2014.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import hr.calyx.vjestina2014.config.SecurityConfig;
import hr.calyx.vjestina2014.security.JWTClaims;
import hr.calyx.vjestina2014.services.AppUserService;
import hr.calyx.vjestina2014.services.RSAKeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;

/**
 * Created by Tomislav on 12/7/2014.
 */
@Controller
public class AuthController {

    @Autowired
    AppUserService appUserService;

    @Autowired
    RSAKeysService rsaKeysService;

    @RequestMapping(value = "/oauth2/google", method = RequestMethod.POST)
    public ResponseEntity authorizeWithGoogle(HttpServletRequest request) throws IOException, GeneralSecurityException {


        String authorizationCode = request.getHeader("authorization_code");

        GoogleAuthorizationCodeTokenRequest tokenRequest = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new GsonFactory(),
                SecurityConfig.CLIENT_ID, SecurityConfig.CLIENT_SECRET, authorizationCode, "postmessage");

        GoogleTokenResponse response = tokenRequest.execute();

        Google google = new GoogleTemplate(response.getAccessToken());

        Person person = google.plusOperations().getGoogleProfile();

        if (appUserService.getByUsername(person.getAccountEmail()) == null) {
            appUserService.create(person.getAccountEmail(), person.getGivenName(), person.getFamilyName());
        }

        ObjectMapper mapper = new ObjectMapper();
        String tokenContent = mapper.writeValueAsString(new JWTClaims(person.getAccountEmail(), System.currentTimeMillis() + SecurityConfig.TOKEN_EXPIRATION));

        RsaSigner signer = new RsaSigner((RSAPrivateKey) rsaKeysService.getKeys().getPrivate());

        String jwt = JwtHelper.encode(tokenContent, signer).getEncoded();

        return new ResponseEntity(new Token(jwt), HttpStatus.OK);
    }

    public static class Token {
        public String token;

        public Token() {}

        public Token(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
