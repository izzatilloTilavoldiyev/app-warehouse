package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dto.CategoryDto;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //CREATE
    @PostMapping
    public Response addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    //READ
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id) {
        return categoryService.getCategory(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Response updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public Response deleteCategory(@PathVariable Integer id) {
        return categoryService.delete(id);
    }
}
