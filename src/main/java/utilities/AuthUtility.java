package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.json.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import objects.User;

public class AuthUtility {
	static String out_file = "C:\\arenamaster-backend\\auth\\secret-key";
	static String algo = "HMACSHA512";

	public static boolean verify_token(String token){
	 
        JerseyClient client = JerseyClientBuilder.createClient();

		JerseyWebTarget webTarget = client.target("https://oauth2.googleapis.com/tokeninfo?id_token=" + token);

		JerseyInvocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_OCTET_STREAM);

		Response response = invocationBuilder
				.get();

		
		
		if (response.getStatus()/100 != 2) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		String output = response.readEntity(String.class);
		JSONObject ver_res = new JSONObject(output);
		return ver_res.getBoolean("email_verified");
	}
	
		
	public static boolean is_email(String email){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if(mat.matches()){
            return true;
        }else{
        	return false;
        }
	}
	
	public static SecretKey gen_key() {
		//generates a secret key
		KeyGenerator kgen = null;
		try {
			kgen = KeyGenerator.getInstance(algo);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecretKey skey = kgen.generateKey();
		
		//saves the secret key to the output file
		try (FileOutputStream out = new FileOutputStream(new File(out_file))) {
		    out.write(skey.getEncoded());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return skey;
	}
	
	public static SecretKey restore_key() {
		byte[] keyb = null;
		try {
			keyb = Files.readAllBytes(Paths.get(out_file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecretKey skey = new SecretKeySpec(keyb, algo);
		return skey;
	}
	
	public static String gen_token(User login) {
		String jwtToken = Jwts.builder()
				.setIssuer("Tournament Master")
			    .setSubject("" + login.getId())
			    .claim("username", login.getUsername())
			    .signWith(restore_key())
			    .compact();
		return jwtToken;
	}

	public static Jws decode_token(String token) {
		Jws<Claims> jws = null;

		try {
		    jws = Jwts.parserBuilder()  // (1)
		    .setSigningKey(restore_key())         // (2)
		    .build()                    // (3)
		    .parseClaimsJws(token); // (4)
		    
		    // we can safely trust the JWT
		}
		catch (JwtException ex) {       // (5)
		    ex.printStackTrace();
		    // we *cannot* use the JWT as intended by its creator
		}
		
		return jws;
	}
	
	public static String encrypt_password(String password) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		return passwordEncryptor.encryptPassword(password);
	}
	

}
