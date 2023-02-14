package com.bctech.fashionista.dto.request;

import com.bctech.fashionista.entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
public class PostRequestDTO {
    private String title;
    private String content;
    private String imagePath;
    private String categories;
    private Long adminId;
}
