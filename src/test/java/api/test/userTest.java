package api.test;

import api.endpoints.userEndPoints;
import api.payload.user;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.bytebuddy.build.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class userTest {
    Faker fake ;
    user userpayload;
    public Logger logger;
    @BeforeClass
    public void data(){
        fake =new Faker();
        userpayload =new user();

        userpayload.setId(fake.idNumber().hashCode());
        userpayload.setUsername(fake.name().username());
        userpayload.setFirstName(fake.name().firstName());
        userpayload.setLastName(fake.name().lastName());
        userpayload.setEmail(fake.internet().emailAddress());
        userpayload.setPassword(fake.internet().password());
        userpayload.setPhone(fake.phoneNumber().phoneNumber());

        //logs
        logger = LogManager.getLogger(this.getClass());
    }
    @Test(priority = 1)
    public void postUser(){
        logger.info("**********creating______user**********");
        Response res = userEndPoints.createUser(userpayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);
        logger.info("**********created______user**********");

    }
    @Test(priority = 2)
    public void getUser(){
        logger.info("**********reading_____userINfo**********");
        Response res = userEndPoints.getUser(this.userpayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);
        logger.info("**********readed______user**********");
    }
    @Test(priority = 3)
    public void updateUser(){
        logger.info("**********updating______user**********");
        //to update the data
        userpayload.setUsername(fake.name().username());
        userpayload.setFirstName(fake.name().firstName());
        userpayload.setLastName(fake.name().lastName());
        userpayload.setEmail(fake.internet().emailAddress());
        userpayload.setPassword(fake.internet().password());

        Response res = userEndPoints.updateUser(this.userpayload.getUsername(),userpayload);
        res.then().log().all();
        res.then().log().body().statusCode(200);//chai asserstion
        //check data after updation
        Response res1 = userEndPoints.getUser(this.userpayload.getUsername());
        res1.then().log().body();
        res1.then().log().body().statusCode(200);
        Assert.assertEquals(res.getStatusCode(),200);
        logger.info("**********updated______user**********");

    }
    @Test(priority = 4)
    public void delUser(){
        logger.info("**********deleting______user**********");
        Response res = userEndPoints.delUser(this.userpayload.getUsername());
        Assert.assertEquals(res.getStatusCode(),200);
        logger.info("**********deleted______user**********");
    }


}
