package rest;

import entity.Lot;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class LotService extends Headers{

    public static final String REST_SERVICE_URI = "http://localhost:8080/lots";

    /*
     * Send a GET request to get a specific lot.
     */
    public Lot getLot(int id){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        return restTemplate.exchange(REST_SERVICE_URI +"/"+ id, HttpMethod.GET, request, Lot.class).getBody();
    }

    /*
     * Send a POST request to create a new lot.
     */

    public boolean createLot(Lot lot)  {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<>(lot, getHeaders());
        //try {
            restTemplate.exchange(REST_SERVICE_URI, HttpMethod.POST, request, Lot.class).getBody();
            return true;
        /*} catch (RuntimeException x){
            return false;
        }*/
    }

    /*
     * Send a PUT request to update an existing lot.
     */
    public boolean updateUser(Lot lot) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(lot, getHeaders());
        try {
            restTemplate.exchange(REST_SERVICE_URI , HttpMethod.PUT, request, Lot.class).getBody();
            return true;
        } catch (RuntimeException x){
            return false;
        }
    }

    /*
     * Send a DELETE request to delete a specific lot.
     */
    public boolean deleteLot(int id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        try {
            restTemplate.exchange(REST_SERVICE_URI+"/" + id, HttpMethod.DELETE, request, Lot.class).getBody();
            return true;
        } catch (RuntimeException x){
            return false;
        }
    }
}
