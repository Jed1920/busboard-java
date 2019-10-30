package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPoints {
    private String id;
    private String commonName;
    private Double distance;


    @Override
    public String toString () {
        String print = String.format("| %-5s | %-5s |", id, distance);
        return print;
    }

    public String getId() {
        return id;
    }

    public String getCommonName() {
        return commonName;
    }

    public Double getDistance() {
        return distance;
    }
}
