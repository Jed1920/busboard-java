package training.busboard;

import java.util.*;

public class BusModel {
    private final String postCode;
    private Map<String, List<Bus>> busStops;

    public BusModel(String postCode, Map<String, List<Bus>> busStops) {
        this.postCode = postCode;
        this.busStops = busStops;
    }

        public Map<String, List<Bus>> getBusStops () {
            return busStops;
        }

        public String getPostCode () {
            return postCode;
        }
}
