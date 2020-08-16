package org.example.bpdtsconnections;

import org.example.userinfo.User;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CallAPITest {


    @Test
    public void CallAllUsersAndEnsureReturnedListNotEmpty(){
        List<User> AllUsersTestList = CallAPI.callAllUsers();
        assert AllUsersTestList.size() >0;
    }

    @Test
    // KNOWN USER BEING Ancell
    public void CallAllUserAndCheckKnownRecordsAreIncluded() {
        List<User> KnownRecordList = CallAPI.callLivingInLondon();
        String x = KnownRecordList.toString();
        x.contains("Ancell");
    }

    @Test
    public void CheckCallAllUserEndPointConnection() throws IOException {
        URL url = new URL("https://bpdts-test-app.herokuapp.com/users");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int code = connection.getResponseCode();
        assert code == 200;
    }


    @Test
    public void CallLondonUsersOnlyAndNotAnEmptyListReturned(){
        List<User> LondonTestUsers = CallAPI.callLivingInLondon();
        assert LondonTestUsers.size() >0;

    }

    @Test
    public void CheckLondonUserOnlyEndPointConnection() throws IOException {
        URL url = new URL("https://bpdts-test-app.herokuapp.com/city/London/users");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int code = connection.getResponseCode();
        assert code == 200;
    }

}
