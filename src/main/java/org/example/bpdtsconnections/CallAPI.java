package org.example.bpdtsconnections;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.userinfo.User;
import org.example.utils.Constants;
import org.example.utils.GeoUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CallAPI {

    private static HttpGet request;
    private final static CloseableHttpClient httpClient = HttpClients.createDefault();
    private static List<User> usersInfoObj;



    public static List<User> callAllUsers() {
        List<User> returnList = new ArrayList<>();
        try {

            request = new HttpGet(Constants.CALL_ALL_USERS_API);
            request.addHeader("accept", "application/json");

            CloseableHttpResponse response = httpClient.execute(request);

            if (response != null && response.getStatusLine() != null && 200 == response.getStatusLine().getStatusCode()) {
                if (response.getEntity() != null) {
                    usersInfoObj = getResponse(EntityUtils.toString(response.getEntity()));
                }
                for (User user : usersInfoObj) {
                    if (GeoUtils.haversineDistance(Constants.LONDON_CENTER_LATITUDE, Constants.LONDON_CENTER_LONGITUDE, +
                            user.getLatitude(), user.getLongitude()) <= 50d) {
                        returnList.add(user);
                    }
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnList;

    }

    public static List<User> callLivingInLondon(){
        List<User> livingInLondon = new ArrayList<>();
        try {
            request = new HttpGet(Constants.CALL_LIVING_LONDON_API);
            request.addHeader("accept", "application/json");

            CloseableHttpResponse response = httpClient.execute(request);

            if (response != null && response.getStatusLine() != null && 200 == response.getStatusLine().getStatusCode()) {
                if (response.getEntity() != null) {
                    usersInfoObj = getResponse(EntityUtils.toString(response.getEntity()));
                }
                for (User user : usersInfoObj) {
                    livingInLondon.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return livingInLondon;
    }

    public static List<User> getResponse(String inputStream) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TypeReference<List<User>> typeReference = new TypeReference<>() {};
            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
