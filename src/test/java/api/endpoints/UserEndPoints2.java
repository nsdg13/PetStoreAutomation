package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.endpoints.Routes;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
		
	}
	
	public static  Response CreateUser(User payload){   //Create User
		
		String post_url= getURL().getString("post_url");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		
		 .post(post_url);
		 return response;
		
	}
	
  public static Response ReadUser(String userName){  //Get User by id
	  String get_url= getURL().getString("get_url");
		
		Response response=given()
				.pathParams("username", userName)
		.when()
		
		.get(get_url);
		 return response;
	}
  
  public static Response UpdateUser(String userName,User payload){   //Update User
	  String update_url= getURL().getString("update_url");
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParams("username", userName)
		.body(payload)
		
		.when()
		
		 .put(update_url);
		 return response;
		
	}
  public static Response DeleteUser(String userName){  //Get User by id
	  String delete_url= getURL().getString("delete_url");
		
		Response response=given()
				.pathParams("username", userName)
		.when()
		
		.delete(delete_url);
		 return response;
	}


}
