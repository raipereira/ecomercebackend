package raiper.miu.cs489.controller;

import org.springframework.web.bind.annotation.*;
import raiper.miu.cs489.dto.converter.entityToDto.ConverterCategoryToDto;
import raiper.miu.cs489.model.Category;
import raiper.miu.cs489.result.Result;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.CategoryService;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;
    private ConverterCategoryToDto converterCategoryToDTO;


    public CategoryController(CategoryService categoryService,
                              ConverterCategoryToDto converterCategoryToDTO) {
        this.categoryService = categoryService;
        this.converterCategoryToDTO = converterCategoryToDTO;
    }

    @GetMapping
    public Result getAllCategories() {
        var categories = categoryService.getAllCategory();
        var categoryDto =categories.stream().map(category -> converterCategoryToDTO.convert(category)).toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", categoryDto);
    }

    @GetMapping("/{categoryId}")
    public Result getCategoryById(@RequestParam Integer categoryId) {
        return null;
    }

    @PostMapping
    public Result addNewCategory(@RequestBody Category category) {
        return null;
    }
}
