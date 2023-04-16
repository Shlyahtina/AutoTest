
package ru.autotest.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ResponseErrorDTO {

    @SerializedName("code")
    private Long code;
    @SerializedName("message")
    private String message;
    @SerializedName("type")
    private String type;

}
