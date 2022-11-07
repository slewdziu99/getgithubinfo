package pl.adam.challenge.getgithubinfo.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorJsonObject {

    @JsonProperty("status")
    private String status;

    @JsonProperty("Message")
    private String message;
}
