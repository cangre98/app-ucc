package co.edu.ucc.app.modeloCanonico.dto.generic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRequestDTO {

    private static final long serialVersionUID = 2405172041950251807L;

    public Object request;
}
