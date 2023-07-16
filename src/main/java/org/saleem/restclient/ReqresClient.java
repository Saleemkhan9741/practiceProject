package org.saleem.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.saleem.enums.Resources;
import org.saleem.model.User;
import org.saleem.utils.common.PropertyReader;
import org.saleem.utils.common.SerializationHelper;
import org.testng.Assert;

import java.util.HashMap;


public class ReqresClient extends BaseRestClient{

    private static BaseRestClient baseRestClient;
    private static ReqresClient reqresClient;

    private ReqresClient(){
        baseRestClient = new BaseRestClient(PropertyReader.getPropertyForGivenKey("prod.reqres.baseUrl"));
    }

    public static ReqresClient getInstance() {
        if(reqresClient==null){
            reqresClient = new ReqresClient();
        }
        return reqresClient;
    }

    public User createUsers(String payload) throws JsonProcessingException {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("content-type","application/json;charset = UTF-8");
        Response response = baseRestClient.whenPostRequestIsInvoked(Resources.CREATE_USER, headers, payload);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        return SerializationHelper.getObjectMapper().readValue(response.getBody().asString(),User.class);
    }

    public void getListOfUsers(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("content-type","application/json;charset = UTF-8");
        Response response = baseRestClient.whenGetRequestIsInvoked(Resources.CREATE_USER, headers);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        System.out.println(response.getStatusCode());
    }

    public static void main(String[] args) {
        getInstance().getListOfUsers();
    }
}
