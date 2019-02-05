package com.example.denis.networkpractice2.models.api;

import android.util.Log;

import com.example.denis.networkpractice2.models.entities.Address;
import com.example.denis.networkpractice2.models.entities.Company;
import com.example.denis.networkpractice2.models.entities.Geo;
import com.example.denis.networkpractice2.models.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class JSONPlaceholderAPI {
    private String urlPath;

    public JSONPlaceholderAPI(String urlPath) {
        this.urlPath = urlPath;
    }

    public ArrayList<User> getUsers() throws IOException, JSONException {
        Log.d(JSONPlaceholderAPI.class.getName(), "hi, i im in getUsers()");
        String usersJSONString = getJsonFromServer(urlPath, 10000);
        Log.e(JSONPlaceholderAPI.class.getName(), usersJSONString);
        JSONArray usersArray = new JSONArray(usersJSONString);

        ArrayList<User> totalUsersArr = new ArrayList<User>();

        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject currentObj =  usersArray.getJSONObject(i);
            Log.d(JSONPlaceholderAPI.class.getName(), currentObj.toString());
            User newUser = getUserFromString(currentObj);
            totalUsersArr.add(newUser);
        }

        return totalUsersArr;
    }

    private User getUserFromString(JSONObject obj) throws JSONException {
        JSONObject userRoot = obj;

        JSONObject userAddress = userRoot.getJSONObject("address");
        JSONObject userCompany = userRoot.getJSONObject("company");
        JSONObject addressGeo = userAddress.getJSONObject("geo");

        int userId = userRoot.getInt("id");
        String userName = userRoot.getString("name");
        String userNameName = userRoot.getString("username");
        String userEmail = userRoot.getString("email");
        String userPhone = userRoot.getString("phone");
        String userWebSite = userRoot.getString("website");

        String addressStreet = userAddress.getString("street");
        String addressSuite = userAddress.getString("suite");
        String addressCity = userAddress.getString("city");
        String addressZipcode = userAddress.getString("zipcode");

        double geoLat = addressGeo.getDouble("lat");
        double getLon = addressGeo.getDouble("lng");

        String companyName = userCompany.getString("name");
        String companyCatchPhrase = userCompany.getString("catchPhrase");
        String companyBs = userCompany.getString("bs");

        Geo geo = new Geo(geoLat, getLon);
        Address address = new Address(addressStreet, addressSuite, addressCity, addressZipcode, geo);
        Company company = new Company(companyName, companyCatchPhrase, companyBs);

        return new User(userId, userName, userNameName, userEmail, address, userPhone, userWebSite, company);
    }

    public User getUser(int index) throws IOException, JSONException {
        String userJsonStroke = getJsonFromServer(String.format("%s/%d", urlPath, index), 0);

        JSONObject userRoot = new JSONObject(userJsonStroke);
        JSONObject userAddress = userRoot.getJSONObject("address");
        JSONObject userCompany = userRoot.getJSONObject("company");
        JSONObject addressGeo = userAddress.getJSONObject("geo");

        int userId = userRoot.getInt("id");
        String userName = userRoot.getString("name");
        String userNameName = userRoot.getString("username");
        String userEmail = userRoot.getString("email");
        String userPhone = userRoot.getString("phone");
        String userWebSite = userRoot.getString("website");

        String addressStreet = userAddress.getString("street");
        String addressSuite = userAddress.getString("suite");
        String addressCity = userAddress.getString("city");
        String addressZipcode = userAddress.getString("zipcode");

        double geoLat = addressGeo.getDouble("lat");
        double getLon = addressGeo.getDouble("lng");

        String companyName = userCompany.getString("name");
        String companyCatchPhrase = userCompany.getString("catchPhrase");
        String companyBs = userCompany.getString("bs");

        Geo geo = new Geo(geoLat, getLon);
        Address address = new Address(addressStreet, addressSuite, addressCity, addressZipcode, geo);
        Company company = new Company(companyName, companyCatchPhrase, companyBs);

        return new User(userId, userName, userNameName, userEmail, address, userPhone, userWebSite, company);
    }

    private String getJsonFromServer(String urlPath, int timeout) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.connect();

        int serverResponseCode = connection.getResponseCode();
        switch (serverResponseCode) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String tmpLine;
                while ((tmpLine = br.readLine()) != null) {
                    sb.append(tmpLine).append("\n");
                }
                br.close();
                return sb.toString();
            case 404:
                Log.d(JSONPlaceholderAPI.class.getName(), "page not found!");
                break;
            case 400:
                Log.d(JSONPlaceholderAPI.class.getName(), "Bad request!");
                break;
            case 500:
                Log.d(JSONPlaceholderAPI.class.getName(), "Internal server error");
                break;
        }

        return null;
    }
}
