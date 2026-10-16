package steps;

import com.spotify.pojo.Playlist;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.hamcrest.Matchers;
import org.testng.Assert;

import UtilityPackage.DateandTimeProvider;
import UtilityPackage.ReqandResSpecbuilders;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlaylistSteps 
{

	RequestSpecification reqs;
	Response res;
	static String PlayListID;
	
	@Given("create playlist api payload")
	public void create_playlist_api_payload() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException 
	{
		Playlist PL=new Playlist();
		PL.setName("Aniket Playlist BDD Framework 2");
		PL.setDescription("BDD Framework");
		PL.setPublic(false);
		
		 reqs = given(ReqandResSpecbuilders.ReqSpecBuild())
		.body(PL);
	}
	
	@When("user calls with POST http request for create playlist")
	public void user_calls_with_post_http_request_for_create_playlist() 
	{
		  res = reqs.when()
		.post("/me/playlists");
	}
	
	@Then("api call executed with status code {int}")
	public void api_call_executed_with_status_code(Integer int1) 
	{
		Playlist respPlay = res.as(Playlist.class);
		String nameofPlay= respPlay.getName();
		res.then()
		.body("name", Matchers.equalTo(nameofPlay))
		.spec(ReqandResSpecbuilders.RespSpecBuild(int1))
		.extract()
		
		.response();
		PlayListID = respPlay.getId();
	}

	@Given("GET playlist api payload")
	public void get_playlist_api_payload() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException 
	{
		
		reqs = given(ReqandResSpecbuilders.ReqSpecBuild())	
			.pathParam("id", PlayListID);	
		
	}
	
	@When("user calls with GET http request")
	public void user_calls_with_get_http_request() 
	{
		res= reqs.when()
		
		.get("playlists/{id}");
	}
	
	@Then("api call should executes with status code {int}")
	public void api_call_should_executes_with_status_code(Integer int1)
	{
		res.then()
		.spec(ReqandResSpecbuilders.RespSpecBuild(int1));
		

	}
	
	@Given("update playlist api payload")
	public void update_playlist_api_payload() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{	
		Playlist PL=new Playlist();
		PL.setName("Aniket Playlist BDD Framework 2 " + DateandTimeProvider.getCurrentDatandTime());
		PL.setDescription("BDD Framework");
		PL.setPublic(false);
		
		reqs = given(ReqandResSpecbuilders.ReqSpecBuild())
		.body(PL)
		.pathParam("PID", PlayListID);
	}
	@When("user calls with PUT http request")
	public void user_calls_with_put_http_request() 
	{
		 res = reqs.when()
		.put("/playlists/{PID}");
	}
	@Then("api call executes with status code {int}")
	public void api_call_executes_with_status_code(Integer int1) 
	{
		res.then()
		.statusCode(int1);
	}

}
