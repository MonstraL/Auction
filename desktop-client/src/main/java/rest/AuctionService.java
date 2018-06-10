package rest;

import entity.Auction;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AuctionService extends Headers{
    public static final String REST_SERVICE_URI = "http://localhost:8080/auctions";

    /*
     * Send a GET request to get a specific auction.
     */
    public Auction getAuction(int id){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        return restTemplate.exchange(REST_SERVICE_URI +"/"+ id, HttpMethod.GET, request, Auction.class).getBody();
    }

    /*
     * Send a GET request to get all auctions.
     */
    public List<Auction> getAllAuctions(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        return restTemplate.exchange(REST_SERVICE_URI, HttpMethod.GET, request, new ParameterizedTypeReference<List<Auction>>(){}).getBody();
    }

    /*
     * Send a POST request to create a new auction.
     */

    public boolean createAuction(Auction auction)  {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<>(auction, getHeaders());
        try {
            restTemplate.exchange(REST_SERVICE_URI, HttpMethod.POST, request, Auction.class).getBody();
            return true;
        } catch (RuntimeException x){
            return false;
        }
    }

    /*
     * Send a PUT request to update an existing auction.
     */
    public boolean updateAuction(Auction auction) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(auction, getHeaders());
       // try {
            restTemplate.exchange(REST_SERVICE_URI , HttpMethod.PUT, request, Auction.class).getBody();
            return true;
      /*  } catch (RuntimeException x){
            return false;
        }*/
    }

    /*
     * Send a DELETE request to delete a specific auction.
     */
    public boolean deleteAuction(int id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        try {
            restTemplate.exchange(REST_SERVICE_URI+"/" + id, HttpMethod.DELETE, request, Auction.class).getBody();
            return true;
        } catch (RuntimeException x){
            return false;
        }
    }
}
