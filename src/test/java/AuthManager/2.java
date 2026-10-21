package AuthManager;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import UtilityPackage.CryptoUtil;
import UtilityPackage.ReadProperty;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenGenerator 
{
	static String accToken;
	static Instant exptime;
	public static String getToken() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		if(accToken== null || Instant.now().isAfter(exptime))
		{
			Response resp=renewToken();
			JsonPath jp = resp.jsonPath();
			accToken=jp.getString("access_token");
			int expTimeInsec = jp.getInt("expires_in");
			exptime = Instant.now().plusSeconds(expTimeInsec-300);
		}
		else
		{
			System.out.println("Token is good to use");
		}
		return accToken;
	}

	public static Response renewToken() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		
		ReadProperty.getPropertyData("en_refreshToken");
		ReadProperty.getPropertyData("en_clientId");
		ReadProperty.getPropertyData("en_clientSecret");
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "refresh_token");
		param.put("refresh_token", CryptoUtil.decryption(ReadProperty.getPropertyData("en_refreshToken")));
		param.put("client_id", CryptoUtil.decryption(ReadProperty.getPropertyData("en_clientId")));
		param.put("client_secret", CryptoUtil.decryption(ReadProperty.getPropertyData("en_clientSecret")));
		
		baseURI="https://accounts.spotify.com";
		Response resp = given()
		.header("Content-Type","application/x-www-form-urlencoded")
		.formParams(param)
		.log().all()
		.when()
		.post("api/token")
		.then()
		.log().all()
		.extract()
		.response();
		
		int stsCode = resp.statusCode();
		
		if(stsCode!=200)
		{
			throw new RuntimeException("Token Generation api call failed");
		}
		return resp;
	}
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		renewToken();
	}
	
}
