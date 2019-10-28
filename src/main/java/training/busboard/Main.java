
package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Bus Stop finder! Input your Bus Stop Code to find out the next 5 buses" +
                " arriving at your stop!");
        String stopCode = input.nextLine();

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        List<Bus> busList = client.target("" +
                "https://api.tfl.gov.uk/StopPoint/" + stopCode + "/Arrivals?app_id=da4a2af3&app_key=98ba2b65d63f9c812a52c6c095eced7a")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Bus>>() {});

        Comparator<Bus> compareByTime = (Bus o1, Bus o2) -> o1.getTimeToStation().compareTo(o2.getTimeToStation());
        Collections.sort(busList,compareByTime);

        for (int i = 1; i <= 5 && i < (busList.size()); i++) {
            System.out.println(busList.get(i));
        }
    }
}	