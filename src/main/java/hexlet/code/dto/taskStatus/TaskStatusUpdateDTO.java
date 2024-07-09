package hexlet.code.dto.taskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.openapitools.jackson.nullable.JsonNullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateDTO {

    @Size(min = 1)
    @NotBlank
    private JsonNullable<String> name;

    @Size(min = 1)
    @NotBlank
    private JsonNullable<String> slug;
}
