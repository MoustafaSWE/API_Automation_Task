package utilities;

import config.EnvironmentConfig;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
public class GoogleAuth {

    public static String generateToken () {
        String secretKey = EnvironmentConfig.testedSecretKey;
        GoogleAuthenticator authenticator = new GoogleAuthenticator(
                new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder().setCodeDigits(8).setTimeStepSizeInMillis(120000).build());
        Integer code = authenticator.getTotpPassword(secretKey);
        String token = String.format("%08d", code);
        return token;
    }
}
