package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.datatable.*;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static org.hamcrest.Matchers.*;

import UtilityPackage.ReqandResSpecbuilders;

public class SearchSteps
{
	RequestSpecification reqs;
	Response res;
	String Sname;
	
	@Given("GET Search song api payload is available")
	public void get_search_song_api_payload_is_available(DataTable dataTable) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException 
	{
		List<Map<String,String>> data = dataTable.asMaps();
		Map<String,String> firstline= data.get(0);
		Sname = firstline.get("songname");
		String Tvalue = firstline.get("type");
		String artvalue = firstline.get("artist");
	    
		reqs=given(ReqandResSpecbuilders.ReqSpecBuild())
	    .queryParams("q", Sname, "type",Tvalue, "artist", artvalue);
	}
	
	@When("user calls with GET http request for Search for Item")
	public void user_calls_with_get_http_request_for_search_for_item()
	{
	   res=reqs.when()
	   .get("search");
	   
		
	}
	
	@Then("api calls executed with status code {int}")
	public void api_calls_executed_with_status_code(Integer int1) 
	{
	   res.then()
	   .spec(ReqandResSpecbuilders.RespSpecBuild(int1))
	   .assertThat()
	   .body(containsString(Sname));
	   
	}
	
}
