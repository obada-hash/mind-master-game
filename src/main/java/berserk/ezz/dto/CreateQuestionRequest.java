package berserk.ezz.dto;


import lombok.Data;

@Data
public class CreateQuestionRequest {

    private String description;
    private String answer;
    private int score;
}
