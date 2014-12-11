package hr.calyx.vjestina2014.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tomislav on 12/8/2014.
 */
@Service
public class RSAKeysServiceImpl implements RSAKeysService {

    private KeyPair keyPair;

    @Override
    public KeyPair getKeys() {
        return keyPair;
    }

    @PostConstruct
    private void RSAKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        this.keyPair = pair;
    }
}
