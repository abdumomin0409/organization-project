package com.company.organization.service.organization;

import com.company.organization.domain.organization.Product;
import com.company.organization.exception_handler.exception.ValidateException;
import com.company.organization.payload.organization.ProductDTO;
import com.company.organization.repository.organization.ProductRepository;
import com.company.organization.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.company.organization.mapper.organization.ProductMapper.PRODUCT_MAPPER;;

@Service
@RequiredArgsConstructor
public class ProductService implements BaseService {
    private final ProductRepository productRepository;
    Logger logger = Logger.getLogger(ProductService.class.getName());

    public Product create(ProductDTO dto) {
        if (existsByName(dto.name()))
            throw new ValidateException("Product already exists", -1400002);
        Product product = PRODUCT_MAPPER.fromCreateToProduct(dto);
        product.setIsActive(true);
        return productRepository.save(product);
    }

    public Product updateById(Long id, ProductDTO dto) {
        logger.log(Level.INFO, "ProductService updateById method called");
        if (!existsById(id))
            throw new ValidateException("Product not found", -1400001);
        Product byId = getById(id);
        if (!byId.getName().equals(dto.name()) && existsByName(dto.name()))
            throw new ValidateException("Product already exists", -1400002);
        PRODUCT_MAPPER.fromUpdateToProduct(dto, byId);
        return productRepository.save(byId);
    }

    public Product deleteById(Long id) {
        logger.log(Level.INFO, "ProductService deleteById method called");
        if (!existsById(id))
            throw new ValidateException("Product not found", -1400001);
        Product product = getById(id);
        product.setIsActive(false);
        return productRepository.save(product);
    }

    public Product getById(Long id) {
        logger.log(Level.INFO, "ProductService getById method called");
        return productRepository.findById(id).orElseThrow(() -> new ValidateException("Product not found", -1400005));
    }

    public List<Product> getAll() {
        logger.log(Level.INFO, "ProductService getAll method called");
        return productRepository.findAll();
    }

    public Page<Product> getAllFixed(Pageable pageable) {
        logger.log(Level.INFO, "ProductService getAllFixed method called");
        return productRepository.findAllByPageable(pageable);
    }

    public boolean existsByName(String name) {
        logger.log(Level.INFO, "ProductService existsByName method called");
        return productRepository.existsByName(name);
    }

    public boolean existsById(Long id) {
        logger.log(Level.INFO, "ProductService existsById method called");
        return productRepository.existsById(id);
    }


}
