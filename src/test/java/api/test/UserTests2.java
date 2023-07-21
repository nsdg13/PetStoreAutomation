package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	Faker fake;
	User Userpayload;
	public Logger logger;
	
	@BeforeClass
	public void setUP() {
		
		fake=new Faker();
	    Userpayload=new User();
	    
	    Userpayload.setId(fake.idNumber().hashCode());
	    Userpayload.setUsername(fake.name().username());
	    Userpayload.setFirstName(fake.name().firstName());
	    Userpayload.setLastName(fake.name().lastName());
	    Userpayload.setEmail(fake.internet().emailAddress());
	    Userpayload.setPassword(fake.internet().password());
	    Userpayload.setPhone(fake.phoneNumber().cellPhone());
	    logger=LogManager.getLogger(this.getClass());
	    
	    
	}
	    @Test(priority=1)
	    public void postUser_TC() {
	    	
	    Response response=	UserEndPoints2.CreateUser(Userpayload);
	    response.then().log().all();
	    Assert.assertEquals(response.getStatusCode(), 200);
	    
	    System.out.println("User created");
	    	
	    }
	    @Test(priority=2)
	    public void readUserbyName() {
	    	logger.info("***********Creating User************");
	    	
	    Response response=	UserEndPoints2.ReadUser(this.Userpayload.getUsername());
	    Assert.assertEquals(response.getStatusCode(), 200);
	    response.then().log().all();
	    System.out.println("User DetaILS fetched");
	    }
	    @Test(priority=3)
	    public void UpdateDeatisOfUser() {
	    	logger.info("***********Updating User************");
	    	 Userpayload.setId(fake.idNumber().hashCode());
	 	     Userpayload.setFirstName(fake.name().firstName());
	 	     Userpayload.setLastName(fake.name().lastName());
	 	     Response response= UserEndPoints2.UpdateUser(this.Userpayload.getUsername(), Userpayload);
	 	     
	 	    Assert.assertEquals(response.getStatusCode(), 200);
		    response.then().log().all();
		    System.out.println("User updated");
		    Response response1=	UserEndPoints2.ReadUser(this.Userpayload.getUsername());
		    Assert.assertEquals(response1.getStatusCode(), 200);
		    response1.then().log().all();
	    }
	    @Test(priority=4)
	    public void DelterUserByNmae() {
	    	
	    	logger.info("***********Delete User************");
	    	 Response response=UserEndPoints2.DeleteUser(this.Userpayload.getUsername());
	    	  Assert.assertEquals(response.getStatusCode(), 200);
			    response.then().log().all();
			    System.out.println("User Deleted");
	    	 
	    }
		
		
		
		
	}



