
package com.quest.buyout.Controllers;

import com.quest.buyout.model.Category;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CategoryController {


    @Autowired
    CategoryService categoryService;


    // TODO FIND

    // find all categories
    @GetMapping("/v1/category")
    public List<Category> index(){
        return categoryService.index();
    }

    // find category by id
    @PostMapping("/v1/category/_id")
    public Category findCategoryById(@RequestBody HashMap<String, String> body){
        return categoryService.findCategoryById(body);
    }

    // TODO CREATE


    // create a category record
    @PostMapping("/v1/category")
    public OutLaw createCategory(@RequestBody HashMap<String, String> body){
        return categoryService.createCategory(body);
    }

    // create category records
    @PostMapping("/v1/categories")
    public String[] createCategories(@RequestBody List<HashMap<String, String>> body){
        return categoryService.createCategories(body);
    }


    // TODO COUNT

    // count category records
    @PostMapping("/v1/category/count")
    public String countCategory(){
        return categoryService.countCategory();
    }

    // TODO UPDATE
    // I don't think category should be updated


    // TODO DELETE

    // delete a category
    @PostMapping("/v1/category/delete")
    public OutLaw deleteCategoryById(@RequestBody HashMap<String, String> body){
        return categoryService.deleteCategoryById(body);
    }

    // delete  category records
    @PostMapping("/v1/category/deletes")
    public String[] deleteCategoriesByIds(@RequestBody List<HashMap<String, String>> body){
        return categoryService.deleteCategoriesByIds(body);
    }

    // delete all records
    // only in development builds
    @GetMapping("/v1/category/del_all_admin")
    public String deleteAll(){
        return categoryService.deleteAll();
    }


}
