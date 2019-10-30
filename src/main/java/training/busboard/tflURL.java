package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

public class tflURL {
    private static final String TflURLString = "https://api.tfl.gov.uk";
    private static final String appId = "e1fe5d78";
    private static final String appKey = "d4f94959d5f4517bcf65be07fbdf985b";

    Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    public StopPointOverview tflURL_busStops(PostcodeURL coordinates) {

        return client
                .target(TflURLString)
                .path("StopPoint")
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("radius", "200")
                .queryParam("lat", coordinates.getCoordinates().getResult().getLatitude())
                .queryParam("lon", coordinates.getCoordinates().getResult().getLongitude())
                .queryParam("app_id", appId)
                .queryParam("app_key", appKey)
                .request(MediaType.APPLICATION_JSON)
                .get(StopPointOverview.class);
    }
    public List<Bus> tflURL_bus(StopPoints stop) {

        return client
                .target(TflURLString)
                .path("StopPoint/" + stop.getId() + "/Arrivals")
                .queryParam("app_id", appId)
                .queryParam("app_key", appKey)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Bus>>() {});
    }
}
