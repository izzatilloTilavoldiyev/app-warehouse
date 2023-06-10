package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dto.CategoryDto;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.reqository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Response addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParent_category_id() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParent_category_id());
            if (optionalParentCategory.isEmpty())
                return new Response("This parent category not found", false);
            category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return new Response("Successfully saved", true);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Response update(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return new Response("This category not found", false);
        Category category = optionalCategory.get();
        if (categoryDto.getParent_category_id() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParent_category_id());
            if (optionalParentCategory.isEmpty())
                return new Response("This parent category not found", false);
            category.setParentCategory(optionalParentCategory.get());
        }
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new Response("Successfully edited", true);
    }

    @Override
    public Response delete(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return new Response("This category not found", false);
        categoryRepository.delete(optionalCategory.get());
        return new Response("Successfully deleted", true);
    }
}
