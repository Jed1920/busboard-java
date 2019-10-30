package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointOverview {
    private List<StopPoints> stopPoints;

    public List<StopPoints> getStopPoints() {
        return stopPoints;
    }
}
