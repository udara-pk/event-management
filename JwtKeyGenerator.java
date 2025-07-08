import javax.crypto.KeyGenerator;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256); // 256-bit key
        byte[] key = keyGen.generateKey().getEncoded();
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("JWT Secret: " + base64Key);
    }
}