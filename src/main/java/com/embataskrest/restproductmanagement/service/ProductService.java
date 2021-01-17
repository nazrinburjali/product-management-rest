package com.embataskrest.restproductmanagement.service;

import com.embataskrest.restproductmanagement.entity.Category;
import com.embataskrest.restproductmanagement.entity.Product;
import com.embataskrest.restproductmanagement.repository.CategoryRepository;
import com.embataskrest.restproductmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        Category newCategory = categoryRepository.save(product.getCategory());
        product.getCategory().setCategoryID(newCategory.getCategoryID());
        Product newProduct = productRepository.save(product);
        return product;
    }

    public Product updateProduct(Product product){
        Optional<Product> optionalProduct = productRepository.findById(product.getProductID());
        if (optionalProduct.isPresent()){
            return productRepository.save(product);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, product.getProductID() + "id-li product movcud deyil ");
        }
    }

    public Product getProductById(int id){
        Optional<Product>optionalProduct = productRepository.findById(id);
        Product product = null;
        if (optionalProduct.isPresent()){
            product = optionalProduct.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + " id-li product");
        }
        return  product;
    }

    public void deleteProduct(int id){
        Optional<Product>optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + " id-li product");
        }
    }

    public List<Product> getAll(){
        List<Product>productList = new ArrayList<>();
        Iterable<Product>productIterable = productRepository.findAll();
        productIterable.forEach(productList::add);
        return productList;
    }
}
