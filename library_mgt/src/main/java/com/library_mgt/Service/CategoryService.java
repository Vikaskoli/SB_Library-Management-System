package com.library_mgt.Service;

import com.library_mgt.Entity.Author;
import com.library_mgt.Entity.Category;
import com.library_mgt.Repository.AuthorRepository;
import com.library_mgt.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //Fetching author data
    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    //fetching specific author
    public Category findCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Author bot Found"));
        return category;
    }

    //Add new Category
    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Category category){
        categoryRepository.save(category);
    }


    //Deleting Category
    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Author Not Found"));
        categoryRepository.deleteById(category.getId());
    }

}
