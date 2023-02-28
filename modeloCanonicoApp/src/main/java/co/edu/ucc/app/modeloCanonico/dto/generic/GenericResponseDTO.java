package co.edu.ucc.app.modeloCanonico.dto.generic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class GenericResponseDTO {

    public String message;
    public Object objectResponse;
    public int statusCode;
}
