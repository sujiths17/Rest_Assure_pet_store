package api.test;

import api.endpoints.userEndPoints;
import api.payload.user;
import api.utilites.dataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class dataDriven_test {
    public Logger logger;

    @Test(priority =1,dataProvider = "Data",dataProviderClass = dataProviders.class)
    public void postUser(String userID,String username,String firstName,String lastName,String email,String password ,String phone){

        user userPayload =new user();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(username);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);
        logger = LogManager.getLogger(this.getClass());

        logger.info("**********creating______user**********");
        Response res = userEndPoints.createUser(userPayload);
        res.then().log().all();
        res.then().log().body().statusCode(200);
        Assert.assertEquals(res.getStatusCode(),200);
        logger.info("**********created______user**********");


    }
    @Test(priority =2,dataProvider = "UserNames",dataProviderClass = dataProviders.class)
    public void getUser(String username) {
        logger.info("**********reading_____userINfo**********");

        Response res = userEndPoints.getUser(username);
        res.then().log().body();
        Assert.assertEquals(res.getStatusCode(),200);
        logger.info("**********readed______user**********");



    }
    @Test(priority =3,dataProvider = "UserNames",dataProviderClass = dataProviders.class)
    public void delUser(String username){
        logger.info("**********deleting______user**********");

        Response res = userEndPoints.delUser(username);
        Assert.assertEquals(res.getStatusCode(),200);
        logger.info("**********deleted______user**********");

    }

    }
