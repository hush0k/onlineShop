package com.onlineShop.product.Category;

import com.onlineShop.exception.category.HandleCategoryAlreadyExists;
import com.onlineShop.exception.category.HandleCategoryNotFound;
import com.onlineShop.product.Category.dto.request.CategoryCreateDto;
import com.onlineShop.product.Category.dto.response.CategoriesListResponseDto;
import com.onlineShop.product.Category.dto.response.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private Category categoryIsExists(UUID categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new HandleCategoryNotFound(categoryId)
        );
    }

    @Transactional(readOnly = true)
    public CategoriesListResponseDto getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoriesListResponseDto(categories, categories.size());
    }

    @Transactional(readOnly = true)
    public CategoryResponseDto getCategory(UUID categoryId) {
        Category category = categoryIsExists(categoryId);
        return categoryMapper.toCategoryResponseDto(category);
    }

    public CategoryResponseDto createCategory(CategoryCreateDto categoryCreateDto) {
        if (categoryRepository.existsByName(categoryCreateDto.name())) {
            throw new HandleCategoryAlreadyExists(categoryCreateDto.name());
        }

        Category category = categoryMapper.toCategory(categoryCreateDto);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponseDto(category);
    }

    public void deleteCategory(UUID categoryId) {
        categoryIsExists(categoryId);
        categoryRepository.deleteById(categoryId);
    }

}
