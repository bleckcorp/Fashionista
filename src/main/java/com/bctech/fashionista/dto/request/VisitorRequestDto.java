package com.bctech.fashionista.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class VisitorRequestDto {
    private String name;
    private String password;
    private String confirmPassword;
    private String email;
}
