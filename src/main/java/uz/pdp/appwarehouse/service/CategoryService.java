package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.dto.CategoryDto;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Category;

import java.util.List;

public interface CategoryService {
    Response addCategory(CategoryDto categoryDto);
    List<Category> getAllCategories();
    Response update(Integer id, CategoryDto categoryDto);
    Response delete(Integer id);

    Category getCategory(Integer id);
}
