package training.busboard;

import java.util.*;

public class BusModel {
    private final String postCode;
    private List<List<Bus>> stopBuses = new ArrayList<>();
    private Map<String, List<Bus>> busStops = new HashMap<String,List<Bus>>();

    public BusModel(String postCode) {
        this.postCode = postCode;

        PostcodeURL coordinates = new PostcodeURL(postCode);
        tflURL tflAPI = new tflURL();
        StopPointOverview nearbyStopList = tflAPI.tflURL_busStops(coordinates);

        for (int x = 0; x < 2; x++) {
            StopPoints stop = nearbyStopList.getStopPoints().get(x);

            List<Bus> busList = tflAPI.tflURL_bus(stop);
            List<Bus> busListSorted = new ArrayList<>();
            for (int i = 1; i <= 4 && i < (busList.size()); i++) {
                busListSorted.add(busList.get(i));
            }

            Comparator<Bus> compareByTime = (Bus o1, Bus o2) -> o1.getTimeToStation().compareTo(o2.getTimeToStation());
            Collections.sort(busList, compareByTime);

            busStops.put((String.format(" %-30s | %3.0f m Away", stop.getCommonName(), stop.getDistance())),busList);
            stopBuses.add(busListSorted);
            }
        }

    public List<List<Bus>> getStopBuses() {
        return stopBuses;
    }

    public Map<String, List<Bus>> getBusStops() {
        return busStops;
    }

    public String getPostCode() {
        return postCode;
    }
}
