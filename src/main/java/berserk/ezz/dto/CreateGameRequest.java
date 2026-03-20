package berserk.ezz.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateGameRequest {

    private String name;
    private List<CreateCategoryRequest> categories;}
