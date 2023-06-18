
package ru.autotest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Builder;
import lombok.Data;
import ru.autotest.data.StatusPetEnum;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@SuppressFBWarnings({"EI_EXPOSE_REP2", "EI_EXPOSE_REP"})

public class AddPetDTO {
    private Category category;
    private Long id;
    private String name;
    private List<String> photoUrls;
    private StatusPetEnum status;
    private List<Tag> tags;
}
