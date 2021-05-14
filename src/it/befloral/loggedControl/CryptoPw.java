package it.befloral.loggedControl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoPw {

	private static String ALGO = "SHA-256" ;
	
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String OUTPUT_FORMAT = "%-20s:%s";

    public static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(ALGO);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    
    public static boolean isEqual(String pw , String pwStored) {
    	String pass = CryptoPw.crypt(pw);
    	return pass.equals(pwStored);
    }
    
    
    public static String crypt(String str) {
    	byte[] shaInBytes = CryptoPw.digest(str.getBytes(UTF_8));
    	return bytesToHex(shaInBytes);
    }
        
    
}

    
    
    
