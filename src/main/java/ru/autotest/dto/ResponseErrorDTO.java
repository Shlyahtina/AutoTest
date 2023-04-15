
package ru.autotest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@SuppressWarnings("unused")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseErrorDTO {

    @SerializedName("code")
    private Long code;
    @SerializedName("message")
    private String message;
    @SerializedName("type")
    private String type;

}
