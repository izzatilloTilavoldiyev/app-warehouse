package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dto.ProductDto;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    //CREATE
    @PostMapping
    public Response addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    //READ
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productService.getProduct(id);
    }

    @PutMapping(value = "/{id}")
    public Response editeProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        return productService.update(id, productDto);
    }

    @DeleteMapping("/{id}")
    public Response deleteProduct(@PathVariable Integer id) {
        return productService.delete(id);
    }
}
