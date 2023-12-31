package org.saleem.restclient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.saleem.enums.HttpType;
import org.saleem.enums.Resources;

import java.text.MessageFormat;
import java.util.HashMap;

public class BaseRestClient {

    private static final Logger LOGGER = LogManager.getLogger(BaseRestClient.class);

    private String baseUrl;
    private RequestSpecification request;
    private Response response;
    private String username;
    private String password;

    public BaseRestClient(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public BaseRestClient(){}

    private BaseRestClient(String baseUrl, String username, String password){
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        setAuthentication();
    }

    protected void setAuthentication(){
        if(this.username!=null && password!=null) {
            this.request.auth().preemptive().basic(username, password);
        }
    }

    public Response whenPostRequestIsInvoked(Resources resources, HashMap<String,String> headers, String payload){
        return this.whenRequestIsInvoked(HttpType.POST,resources,headers,payload,null,null,null);
    }

    public Response whenGetRequestIsInvoked(Resources resources, HashMap<String,String> headers){
        return this.whenRequestIsInvoked(HttpType.GET,resources,headers,null,null,null,null);
    }

    public Response whenRequestIsInvoked(HttpType httpType,
                                         Resources resource,
                                         HashMap<String, String> headers,
                                         String payload,
                                         String queryParams,
                                         String entityId,
                                         String... urlParams
                                     ) {
        RestAssured.baseURI = this.baseUrl;
        request = RestAssured.given();

        if(headers!=null && !headers.isEmpty()){
            request.headers(headers);
        }

        if(payload!=null && !payload.isEmpty()){
            request.body(payload);
        }

        String endPoint = formattedUrl(resource,entityId,queryParams,urlParams);

        LOGGER.info("Server URL : {}", this.baseUrl);
        LOGGER.info(String.format("Http Request type : %s", httpType));
        LOGGER.info(String.format("Http Request endpoint: %s", endPoint));
        LOGGER.info(String.format("Http Request payload: %s", payload));
        switch (httpType){
            case GET -> {
                if(endPoint!=null){
                    return request.get(endPoint);
                }else return request.get();
            }
            case PUT -> {
                if(endPoint!=null){
                   return request.put(endPoint);
                }else return request.put();
            }
            case POST -> {
                if(endPoint!=null){
                   return request.post(endPoint);
                }else return request.post();
            }
            case DELETE -> {
                if(endPoint!=null){
                   return request.delete(endPoint);
                }else return request.delete();
            }
        }
        return null;
    }

    private String formattedUrl(Resources resources, String queryParams, String entityId, String[] urlParams) {
        String endPoint = resources.getResourceUrl();
        if(entityId!=null){
            endPoint = endPoint + "/" +entityId;
        }

        if(queryParams!=null){
            endPoint = endPoint + "?" +queryParams;
        }

        if(urlParams!=null){
            endPoint = endPoint + MessageFormat.format(endPoint,urlParams);
        }

        return endPoint;
    }




}
