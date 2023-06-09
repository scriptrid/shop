package ru.scriptrid.productservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.scriptrid.common.dto.ProductDto;
import ru.scriptrid.common.security.JwtAuthenticationToken;
import ru.scriptrid.productservice.model.dto.ProductCreateDto;
import ru.scriptrid.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PutMapping("/{id}")
    public ProductDto editProduct(@AuthenticationPrincipal JwtAuthenticationToken token,
                                  @PathVariable long id,
                                  @RequestBody ProductCreateDto dto) {
        return productService.editProduct(token, id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal JwtAuthenticationToken token,
                                              @PathVariable long id) {
        productService.deleteProduct(token, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable long id) {
        return productService.getProductDto(id);
    }

    @GetMapping("/")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasAuthority('ROLE_SERVICE')")
    @PutMapping("/{id}/reserve")
    public void reserveProduct(@PathVariable long id, @RequestBody @Valid @Min(1) int quantity) {
        productService.reserveProduct(id, quantity);
    }

    @PreAuthorize("hasAuthority('ROLE_SERVICE')")
    @PutMapping("/{id}/return")
    public void returnProduct(@PathVariable long id, @RequestBody @Valid @Min(1) int quantity) {
        productService.returnProduct(id, quantity);
    }

}
