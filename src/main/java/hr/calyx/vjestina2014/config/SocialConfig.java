package hr.calyx.vjestina2014.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.google.connect.GoogleConnectionFactory;

/**
 * Created by Tomislav on 12/8/2014.
 */
@Configuration
public class SocialConfig {

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new GoogleConnectionFactory(
                SecurityConfig.CLIENT_ID, SecurityConfig.CLIENT_SECRET));
        return registry;
    }
}
