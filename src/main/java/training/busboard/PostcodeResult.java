package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class PostcodeResult {
    private PostcodeLocation result;


    public PostcodeLocation getResult() { return result;   }
}
