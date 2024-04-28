package raiper.miu.cs489.service;


import raiper.miu.cs489.model.Category;

import java.util.List;

public interface CategoryService {


    Category getCategoryById(Long categoryId);
    List<Category> getAllCategory();
    Category createNewCategory(Category category);
}
