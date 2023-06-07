package uz.pdp.appwarehouse.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private Integer category_id;
    private Integer photo_id;
    private Integer measurement_id;
}
