package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.dto.ProductDto;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Product;

import java.util.List;

public interface ProductService {
    Response addProduct(ProductDto productDto);
    List<Product> getAllProducts();
    Product getProduct(Integer productId);
    Response update(Integer productId, ProductDto productDto);
    public Response delete(Integer id);
}
