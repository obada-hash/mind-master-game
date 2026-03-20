package berserk.ezz.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateCategoryRequest {

    @NotEmpty(message = "can not be empty")
    private String name;
    private List<CreateQuestionRequest> questions;

}
