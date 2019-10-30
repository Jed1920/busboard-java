package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PostcodeLocation {
    private Double longitude;
    private Double latitude;
    private String country;


    @Override
    public String toString () {
        String print = String.format("| %-5s | %-5s |", longitude, latitude);
        return print;
    }
    public Double getLongitude() { return longitude; }

    public Double getLatitude() { return latitude; }
}
