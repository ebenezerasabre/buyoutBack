package com.quest.buyout.service;

import com.quest.buyout.dao.CategoryRepository;
import com.quest.buyout.model.Category;
import com.quest.buyout.model.OutLaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CategoryService {


    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> index(){
        return categoryRepository.findAll();
    }

    public Category findCategoryById(HashMap<String, String> body){
        return categoryRepository.findById(body.get("_id")).orElse(null);
    }

    public OutLaw createCategory(HashMap<String, String> body){
        categoryRepository.save(mkCategory(body));
        return new OutLaw("Category record created");
    }

    public String[] createCategories(List<HashMap<String, String>> body){
        for(HashMap<String , String> newBody : body){
            categoryRepository.save(mkCategory(newBody));
        }
        return new String[]{"Category records created"};
    }

    private Category mkCategory(HashMap<String, String> body){
        return new Category( body.get("name"), body.get("image"));
    }

    public String countCategory(){
        return String.valueOf(categoryRepository.findAll().size());
    }


    public OutLaw deleteCategoryById(HashMap<String, String> body){
        categoryRepository.deleteById(body.get("_id"));
        return new OutLaw("Category record deleted");
    }

    public String[] deleteCategoriesByIds(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            categoryRepository.deleteById(newBody.get("_id"));
        }
        return new String[]{"Category records deleted"};
    }

    public String deleteAll(){
        categoryRepository.deleteAll();
        return "All category records deleted";
    }



}
