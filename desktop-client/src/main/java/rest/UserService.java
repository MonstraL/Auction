package rest;

import entity.User;
import org.springframework.http.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class UserService extends Headers {

    public static final String REST_SERVICE_URI = "http://localhost:8080/users";

    /*
     * Send a GET request to get a specific user.
     */
    public User getUser(int id){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        return restTemplate.exchange(REST_SERVICE_URI +"/"+ id, HttpMethod.GET, request, User.class).getBody();
    }

    /*
     * Send a GET request to login.
     */
    public User getLogin(String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        try {
            User use = restTemplate.exchange(REST_SERVICE_URI + "/login/{email}+{password}", HttpMethod.GET, request, User.class, email, password).getBody();
            return use;
       } catch (RuntimeException x){
            return null;
        }
    }

    /*
     * Send a POST request to create a new user.
     */

    public boolean createUser(User user)  {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<>(user, getHeaders());
        try {
            restTemplate.exchange(REST_SERVICE_URI, HttpMethod.POST, request, User.class).getBody();
            return true;
        } catch (RuntimeException x){
         return false;
        }
    }

    /*
     * Send a PUT request to update an existing user.
     */
    public boolean updateUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(user, getHeaders());
        try {
            restTemplate.exchange(REST_SERVICE_URI , HttpMethod.PUT, request, User.class).getBody();
            return true;
        } catch (RuntimeException x){
            return false;
        }
    }

    /*
     * Send a DELETE request to delete a specific user.
     */
    public boolean deleteUser(int id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        try {
        restTemplate.exchange(REST_SERVICE_URI+"/" + id, HttpMethod.DELETE, request, User.class).getBody();
        return true;
        } catch (RuntimeException x){
         return false;
        }
    }

    public boolean getRegisterForAuction(int auctionId, int userId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        try {
            restTemplate.exchange(REST_SERVICE_URI + "/aregister/{auctionId}+{userId}", HttpMethod.GET, request, String.class, String.valueOf(auctionId), String.valueOf(userId)).getBody();
            System.out.println("Suss");
            return true;
        } catch (RuntimeException x){
            return false;
        }
    }
}
