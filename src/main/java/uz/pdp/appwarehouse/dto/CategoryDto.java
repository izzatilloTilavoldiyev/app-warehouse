package uz.pdp.appwarehouse.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private String name;
    private Integer parent_category_id;
}
