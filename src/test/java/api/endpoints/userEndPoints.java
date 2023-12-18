package api.endpoints;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import static io.restassured.RestAssured.given;

//created for perform crud operations request
public class userEndPoints {

    public static Response createUser(user payload) {

        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .post(routes.post_url);

        return res;
    }

    public static Response getUser(String username) {

        Response res = given()
                .pathParam("username", username)

                .when()
                .get(routes.cmn_url);

        return res;
    }


    public static Response updateUser(String username, user payload) {

        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)

                .when()
                .put(routes.cmn_url);

        return res;
    }

    public static Response delUser(String username) {

        Response res = given()
                .pathParam("username", username)

                .when()
                .delete(routes.cmn_url);

        return res;
    }
}
