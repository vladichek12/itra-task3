import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KeyGenerator {
    private SecureRandom random;
    private String[] args;

    public KeyGenerator(SecureRandom random, String[] args){
        this.random = random;
        this.args = args;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length*2);
        for(byte b: bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public SecureRandom getRandom(){
        return random;
    }

    public String[] getArgs(){
        return args;
    }

    public Integer getComputerMove(){
        Integer ComputerMove = getRandom().nextInt(args.length)+1;
        return ComputerMove;
    }

    public byte[] getBytes(){
        byte[] bytes = new byte[16];
        getRandom().nextBytes(bytes);
        return bytes;
    }

    public void getHmacKey() {
        System.out.println("Key: " + bytesToHex(getBytes()));
    }

    public void getHmac(){
        Mac signer = null;
        try {
            signer = Mac.getInstance("HmacSHA512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKeySpec keySpec = new SecretKeySpec(getBytes(), "HmacSHA512");
        try {
            signer.init(keySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] digest = new byte[0];
        try {
            digest = signer.doFinal(getComputerMove().toString().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("HMAC: " + bytesToHex(digest));

    }

}