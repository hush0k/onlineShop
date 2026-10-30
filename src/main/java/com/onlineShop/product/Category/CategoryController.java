package com.onlineShop.product.Category;

import com.onlineShop.product.Category.dto.request.CategoryCreateDto;
import com.onlineShop.product.Category.dto.response.CategoriesListResponseDto;
import com.onlineShop.product.Category.dto.response.CategoryResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Category management")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponseDto createCategory(@Valid @RequestBody CategoryCreateDto dto) {
        return categoryService.createCategory(dto);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponseDto getCategory(@PathVariable UUID categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @GetMapping
    public CategoriesListResponseDto getCategories() {
        return categoryService.getCategories();
    }

}
