package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class PostcodeURL {

    private PostcodeResult coordinates;

    public PostcodeURL(String postCode) {
            Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
            coordinates = client.target(String.format("https://api.postcodes.io/postcodes/%s", postCode))
                    .request(MediaType.APPLICATION_JSON)
                    .get(PostcodeResult.class);
    }

    public PostcodeResult getCoordinates() { return coordinates; }
}
