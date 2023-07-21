package api.test;


import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utilities.DataProviders;

public class DDTest {
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostuser(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph) {
		User userPayload=new User();
		 userPayload.setId(Integer.parseInt(userID));
		 userPayload.setUsername(userName);
		 userPayload.setFirstName(fname);
		 userPayload.setLastName(lname);
		 userPayload.setEmail(useremail);
		 userPayload.setPassword(pwd);
		 userPayload.setPhone(ph);
		 
		io.restassured.response.Response response= UserEndPoints2.CreateUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
		}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void DeleteAllUsers(String userName) {
		io.restassured.response.Response response=UserEndPoints2.DeleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	
		
	}
	}


