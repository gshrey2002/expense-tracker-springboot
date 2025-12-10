package com.example.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAccountRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String currency;

}
