package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.*;

import java.util.*;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postCode) {

        PostcodeURL coordinates = new PostcodeURL(postCode);
        tflURL tflAPI = new tflURL();
        StopPointOverview nearbyStopList = tflAPI.tflURL_busStops(coordinates);
        Map<String, List<Bus>> busStops = new HashMap<String,List<Bus>>();

        for (int x = 0; x < 2; x++) {
            StopPoints stop = nearbyStopList.getStopPoints().get(x);

            List<Bus> busList = tflAPI.tflURL_bus(stop);
            List<Bus> busListSorted = new ArrayList<>();

            Comparator<Bus> compareByTime = (Bus o1, Bus o2) -> o1.getTimeToStation().compareTo(o2.getTimeToStation());
            Collections.sort(busList, compareByTime);

            for (int i = 1; i <= 5 && i < (busList.size()); i++) {
                busListSorted.add(busList.get(i));
            }
            busStops.put((String.format(" %-30s | %3.0f m Away", stop.getCommonName(), stop.getDistance())),busListSorted);
        }
            return new ModelAndView("info", "busModel", new BusModel(postCode, busStops));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}