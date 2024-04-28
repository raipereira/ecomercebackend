package raiper.miu.cs489.service.impl;

import org.springframework.stereotype.Service;
import raiper.miu.cs489.model.Category;
import raiper.miu.cs489.repository.CategoryRepository;
import raiper.miu.cs489.service.CategoryService;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createNewCategory(Category category) {
        return null;
    }
}
