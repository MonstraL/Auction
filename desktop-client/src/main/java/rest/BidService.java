package rest;

import entity.Bid;
import entity.BidContextWrapper;
import entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class BidService extends  Headers{
    public static final String REST_SERVICE_URI = "http://localhost:8080/bids";

    /*
     * Send a GET request to get a specific bid.
     */
    public Bid getBid(int id){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        return restTemplate.exchange(REST_SERVICE_URI +"/"+ id, HttpMethod.GET, request, Bid.class).getBody();
    }

       /*
     * Send a POST request to create a new bid.
     */


    public boolean createBidB(Bid bid)  {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<>(bid, getHeaders());
       // try {
            restTemplate.exchange(REST_SERVICE_URI, HttpMethod.POST, request, Bid.class).getBody();
            return true;
       /*} catch (RuntimeException x){
            return false;
        }*/
    }

}
