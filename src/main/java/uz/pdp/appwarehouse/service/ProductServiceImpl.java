package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dto.ProductDto;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.reqository.AttachmentRepository;
import uz.pdp.appwarehouse.reqository.CategoryRepository;
import uz.pdp.appwarehouse.reqository.MeasurementRepository;
import uz.pdp.appwarehouse.reqository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    @Override
    public Response addProduct(ProductDto productDto) {
        if (productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategory_id()))
            return new Response("This product exists in this category", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory_id());
        if (optionalCategory.isEmpty())
            return new Response("Category not found", false);
        Optional<Attachment> optionalPhoto = attachmentRepository.findById(productDto.getPhoto_id());
        if (optionalPhoto.isEmpty())
            return new Response("Photo not found", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurement_id());
        if (optionalMeasurement.isEmpty())
            return new Response("Measurement not found", false);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalPhoto.get());
        product.setCode(generateCode());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Response("Successfully saved", true);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Response update(Integer productId, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            return new Response("This product not found", false);
        Product editingProduct = optionalProduct.get();

        Response categoryExists = doesCategoryExists(productDto.getCategory_id());
        if (!categoryExists.isResult())
            return categoryExists;
        Response photoExists = doesPhotoExists(productDto.getPhoto_id());
        if (!photoExists.isResult())
            return photoExists;
        Response measurementExists = doesMeasurementExists(productDto.getMeasurement_id());
        if (!measurementExists.isResult())
            return measurementExists;

        editingProduct.setName(productDto.getName());
        editingProduct.setCategory((Category) categoryExists.getObject());
        editingProduct.setPhoto((Attachment) photoExists.getObject());
        editingProduct.setMeasurement((Measurement) measurementExists.getObject());
        productRepository.save(editingProduct);
        return new Response("Successfully edited", true);
    }

    @Override
    public Response delete(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            return new Response("This product not found", false);
        productRepository.delete(optionalProduct.get());
        return new Response("Successfully deleted", true);
    }

    private String generateCode() {
        long code;
        List<Product> productList = productRepository.findAll();
        if (productList.size() < 1) {
            code = 1;
        } else
            code = Long.parseLong(productList.get(productList.size() - 1).getCode());
        return String.valueOf(code + 1);
    }

    private Response doesCategoryExists(int categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty())
            return new Response("Category not found", false);
        return new Response("Success", true, optionalCategory.get());
    }

    private Response doesPhotoExists(int photoId) {
        Optional<Attachment> optionalPhoto = attachmentRepository.findById(photoId);
        if (optionalPhoto.isEmpty())
            return new Response("Photo not found", false);
        return new Response("Success", true, optionalPhoto.get());
    }

    private Response doesMeasurementExists(int measurementId) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (optionalMeasurement.isEmpty())
            return new Response("Measurement not found", false);
        return new Response("Success", true, optionalMeasurement.get());
    }

}
