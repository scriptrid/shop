package ru.scriptrid.productservice.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Validated
public record ProductCreateDto(
        @NotBlank
        String productName,

        String description,

        @NotNull
        long organizationId,

        @Min(0)
        @NotNull
        BigDecimal price,

        @Min(0)
        @NotNull
        int quantityInStock,

        Set<String> tags,

        Map<String, String> specs
) {
}
