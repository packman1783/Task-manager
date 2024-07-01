package hexlet.code.dto.taskStatus;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.openapitools.jackson.nullable.JsonNullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateDTO {

    @Column(unique = true)
    @Size(min = 1)
    @NotBlank
    private JsonNullable<String> name;

    @Column(unique = true)
    @Size(min = 1)
    @NotBlank
    private JsonNullable<String> slug;
}
