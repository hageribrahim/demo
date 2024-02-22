package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class GenericResponseDTO {
    private String message;

    public GenericResponseDTO(String message) {
        this.message = message;
    }
}
