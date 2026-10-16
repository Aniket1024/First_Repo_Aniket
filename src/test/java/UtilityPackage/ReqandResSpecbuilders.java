package UtilityPackage;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import AuthManager.TokenGenerator;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqandResSpecbuilders 
{
	@Step
    public static RequestSpecification ReqSpecBuild() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
    {
    	return new RequestSpecBuilder()
		.setBaseUri("https://api.spotify.com/")
		.setBasePath("v1")
		.addHeader("Content-Type","application/json")
		.addHeader("Authorization", "Bearer "+TokenGenerator.getToken())
		.addFilter(new AllureRestAssured())
		.log(LogDetail.ALL)
		.build();
    }
    
	@Step
    public static ResponseSpecification RespSpecBuild(int expStatusCode)
    {
    	return new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(expStatusCode)
		.log(LogDetail.ALL)
		.build();
    }
}
