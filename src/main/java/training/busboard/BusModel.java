package training.busboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BusModel {
    private List<String> busStops = new ArrayList<>();
    private final String postCode;
    private String printout;

    public BusModel(String postCode){
        this.postCode = postCode;

        PostcodeURL coordinates = new PostcodeURL(postCode);
        tflURL tflAPI = new tflURL();
        StopPointOverview nearbyStopList = tflAPI.tflURL_busStops(coordinates);

        for (int x = 0; x < 2; x++) {
            StopPoints stop = nearbyStopList.getStopPoints().get(x);

            List<Bus> busList = tflAPI.tflURL_bus(stop);

            Comparator<Bus> compareByTime = (Bus o1, Bus o2) -> o1.getTimeToStation().compareTo(o2.getTimeToStation());
            Collections.sort(busList, compareByTime);
            busStops.add(String.format("| %-30s | Distance %3.0f m|",stop.getCommonName(), stop.getDistance()));

            printout = String.format("| %-30s | Distance %3.0f m|",stop.getCommonName(), stop.getDistance());
            System.out.println(printout);

            for (int i = 1; i <= 5 && i < (busList.size()); i++) {
                System.out.println(busList.get(i));
            }
        }
    }
    public List<String> getBusStops() { return busStops; }

    public String getPostCode() { return postCode; }

    public String getPrintout() { return printout; }
}
