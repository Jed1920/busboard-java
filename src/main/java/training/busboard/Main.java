
package training.busboard;

import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Bus Stop finder! Input your Postcode to find out the next 5 buses" +
                " arriving at your nearest stops!");
        String postCode = input.nextLine();

        PostcodeURL coordinates = new PostcodeURL(postCode);
        tflURL tflAPI = new tflURL();
        StopPointOverview nearbyStopList = tflAPI.tflURL_busStops(coordinates);
        List<String> busStops= new ArrayList<>();


        for (int x = 0; x < 2; x++) {
            StopPoints stop = nearbyStopList.getStopPoints().get(x);

            List<Bus> busList = tflAPI.tflURL_bus(stop);

            Comparator<Bus> compareByTime = (Bus o1, Bus o2) -> o1.getTimeToStation().compareTo(o2.getTimeToStation());
            Collections.sort(busList, compareByTime);
            busStops.add(String.format("| %-30s | Distance %3.0f m|",stop.getCommonName(), stop.getDistance()));

            String printout = String.format("| %-30s | Distance %3.0f m|",stop.getCommonName(), stop.getDistance());
            System.out.println(busStops);

            for (int i = 1; i <= 5 && i < (busList.size()); i++) {
                System.out.println(busList.get(i));
            }
        }
        System.out.println(busStops);
    }
}