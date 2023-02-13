package com.bctech.fashionista.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AdminResponseDto {

    private Long id;
    private String fullName;
}
