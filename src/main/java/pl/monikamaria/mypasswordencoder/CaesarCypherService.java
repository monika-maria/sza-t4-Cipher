package pl.monikamaria.mypasswordencoder;

import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

@Service
public class CaesarCypherService {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final Integer shiftKey = 13;

    public String encode(String password) {
        String hashedPassword = "";
        password = password.toLowerCase();

            for (int i = 0; i < password.length(); i++) {
                int charPosition = ALPHABET.indexOf(password.charAt(i));
                int keyVal = (shiftKey + charPosition) % 26;
                String replaceVal = String.valueOf(ALPHABET.charAt(keyVal));
                hashedPassword = hashedPassword.concat(replaceVal);
            }

        return hashedPassword;
    }

    public Boolean matches(String password, String hashedPassword) {
        if(hashedPassword.equals(encode(password))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
