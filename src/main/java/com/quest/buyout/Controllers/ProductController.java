
package com.quest.buyout.Controllers;

import com.quest.buyout.model.*;
import com.quest.buyout.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;

/*
    1. Product should contain all, eg hotsales, featured, trending,
    2. Set the status of the product to the respective type
    3. Hotsales will have the biggest discounts
    4. A customer can't create a product record

    https://quest.asabre.com/buyout-0.0.1-SNAPSHOT/v1/product
 */

@RestController
public class ProductController {


    @Autowired
    ProductService productService;

    // TODO FIND

    // find all products
    // Show this when the user clicks see all featured products -
    // from the home page
    // true
    @GetMapping("/v1/product")
    public List<Product> index(){ return productService.index(); }


    // find one product by id
    //true
    @PostMapping("/v1/product/_id")         // app
    public Optional<Product> findProductById(@RequestBody HashMap<String, String> body){
        return productService.findProductById(body);
    }

    // find multiple products
    // use this algorithm to create recommended sections for users
    // true
    @PostMapping("/v1/product/_ids")
    public ArrayList<Product> findProductsByIds(@RequestBody List<HashMap<String, String>> body){
        return productService.findProductsByIds(body);
    }

    // find products out of stock
    @GetMapping("/v1/productOutOfStock")
    public Stream<Product> findProductsOutOfStock(){
        return productService.findProductsOutOfStock();
    }

    // when a category is selected from the home page
    // find products by category
    @PostMapping("/v1/product/category")
    public Stream<Product> findProductsByCategory(@RequestBody List<HashMap<String, String>> newBody){
        return productService.findProductsByCategory(newBody);
    }

    // TODO SEARCH
    // searching in parts is not necessary but loading more of the
    // searched item is, so searching can use a single request


    // find products by searching from home        // app
    @PostMapping("/v1/product/search")
    public List<Product> findProductsBySearch(@RequestBody List<HashMap<String, String>> newBody){
        return productService.generalSearch(newBody);
    }

    // find products by searching a particular category
    @PostMapping("/v1/product/search/category")
    public List<Product> searchCategory(@RequestBody List<HashMap<String, String>> newBody){
        return productService.generalSearch(newBody);
    }

    // find products searching by status
    @PostMapping("/v1/product/search/status")
    public List<Product> searchByStatus(@RequestBody List<HashMap<String, String>> newBody){
        return productService.generalSearch(newBody);
    }


    // find products by category and subCategory
    @PostMapping("/v1/product/subCategory")
    public Stream<Product>  bySubCategory(@RequestBody List<HashMap<String, String>> newBody){
        return productService.getProductsBySubCategory(newBody);
    }

    // find products by category and subCategory in parts
    @PostMapping("/v1/product/subCategory/parts")
    public List<Product> productByCatAndSubCatInParts(@RequestBody List<HashMap<String, String>> body){
        return productService.generalProductsInParts(body);
    }

    // show subCategories of a category
//    @PostMapping("/v1/product/category/sub")
//    public ArrayList<SubCategory> getSubCategories(@RequestBody List<HashMap<String, String>> newBody){
//        return productService.getSubCategories(newBody);
//    }

    @PostMapping("/v1/product/category/sub")
    public ArrayList<SubCategory> getSubCategories(@RequestBody List<HashMap<String, String>> newBody){
        return productService.getSubCategories(newBody);
    }


    /**
     * create a catalog for trending products
     */


    // first 7 of product will be featured on home
    // app home page
    @GetMapping("/v1/product/featured")
    public List<Product> featuredProducts(){
        return productService.featuredProducts();
    }



    // show first 7 on homepage
    // hot sale featured on homepage
    @GetMapping("/v1/product/featuredHotsales")
    public List<Product> hotsale(){
        return productService.featuredHotsales();
    }

    // all hot sales
    @GetMapping("/v1/product/hotsales")
    public Stream<Product> hotsales(){
        return productService.hotsales();
    }

    // TODO IN PARTS

    // all hot sales
    @PostMapping("/v1/product/hotsales/parts")
    public List<Product> hotSalesInParts(@RequestBody List<HashMap<String, String>> body){
        return productService.generalProductsInParts(body);
    }

    // find related products in parts
    @PostMapping("/v1/product/similar/parts")
    public List<Product> similarProductsInParts(@RequestBody List<HashMap<String, String>> body){
//        return productService.similarProductsInParts(body);
        return productService.generalProductsInParts(body);
    }

    // similar products for wishList
    @PostMapping("/v1/product/similarw/parts")
    public List<Product> similarwProductsInParts(@RequestBody List<HashMap<String, String>> body){
//        return productService.similarProductsInParts(body);
        return productService.generalProductsInParts(body);
    }

    // users also viewed
    @PostMapping("v1/product/viewed/parts")
    public List<Product> usersAlsoViewed(@RequestBody List<HashMap<String, String>> body){
        return productService.generalProductsInParts(body);
    }

    @PostMapping("/v1/product/parts")
    public List<Product> productsInParts(@RequestBody List<HashMap<String, String>> body){
        return productService.generalProductsInParts(body);
    }

    @PostMapping("v1/product/trending/parts")
    public List<Product> trendingProductsInParts(@RequestBody List<HashMap<String, String>> body){
        return productService.generalProductsInParts(body);
    }

    @PostMapping("v1/product/explore/parts")
    public List<Product> exploreProductsInParts(@RequestBody List<HashMap<String, String>> body){
        return productService.generalProductsInParts(body);
    }


    // find products bought together
    @PostMapping("/v1/product/boughtTogether")
    public Stream<Product> boughtTogether(@RequestBody List<HashMap<String, String>> body){
        return productService.boughtTogether(body);
    }

    // find related products
//    @PostMapping("/v1/product/similar")
//    public Stream<Product> similarProducts(@RequestBody List<HashMap<String, String>> body){
//        return productService.similarProducts(body);
//    }

    @PostMapping("/v1/product/similar")
    public List<Product> similarProducts(@RequestBody List<HashMap<String, String>> body){
        return productService.generalProductsInParts(body);
    }

//    // seller recommendations ie products from the same seller
//    @PostMapping("/v1/product/seller")
//    public Stream<Product> sellerRecommendation(@RequestBody List<HashMap<String, String>> body){
//        return productService.sellerRecommendation(body);
//    }

    // seller recommendations ie products from the same seller
    @PostMapping("/v1/product/seller")
    public List<Product> sellerRecommendation(@RequestBody List<HashMap<String, String>> body){
        return productService.generalProductsInParts(body);
    }



    // product of the day
    @GetMapping("/v1/product/today")
    public Product productOfTheDay(){
        return productService.productOfTheDay();
    }



    // TODO COUNT

    // count all products
    @GetMapping("/v1/product/count")
    public String countProducts(){ return productService.countProducts(); }

    // count products out of stock
    @GetMapping("/v1/productOutOfStock/count")
    public String countProductsOutOfStcok(){
        return productService.countProductsOutOfStock();
    }

    // count the total stock of products
    @GetMapping("/v1/product/stock")
    public String productTotalStock() {
        return productService.productTotalStock();
    }

    // TODO CREATE

    // create one product
    // true
    @PostMapping("/v1/product")
    public OutLaw createProduct(@RequestBody HashMap<String, String> body){
        return productService.createProduct(body);
    }

    // create multiple products
    // true
    @PostMapping("/v1/products")
    public String[] createProducts(@RequestBody List<HashMap<String, String>> body){
        return productService.createProducts(body);
    }



    // TODO UPDATE

    // update a product
    // true
    @PostMapping("/v1/product/update")
    public OutLaw updateProductById(@RequestBody HashMap<String, String> body){
        return productService.updateProductById(body);
    }

    // update multiple products
    // true
    @PostMapping("/v1/product/updates")
    public String[] updateProductsByIds(@RequestBody List<HashMap<String, String>> body){
        return productService.updateProductsByIds(body);
    }

    @PostMapping("/v1/product/update/all")
    public String updateSizeColor(@RequestBody HashMap<String, String> body){
        return productService.updateSizeColor(body);
    }

    // update discount, status of all hotsales
    @PostMapping("v1/update/prohot")
    public OutLaw discountStatusUpdate(@RequestBody HashMap<String, String> body){
        return productService.discountStatusUpdate(body);
    }


    // TODO DELETE

    // delete a product
    // true
    @PostMapping("/v1/product/delete")
    public OutLaw deleteProductById(@RequestBody HashMap<String, String> body ){
        return productService.deleteProductById(body);
    }

    // delete multiple products
    // true
    @PostMapping("/v1/product/deletes")
    public String[] deleteProductsByIds(@RequestBody List<HashMap<String, String>> body){
        return productService.deleteProductsByIds(body);
    }

    // delete all products
    // for development build only
    @GetMapping("/v1/product/del_all_admin")
    public String deleteAll(){
        return productService.deleteAll();
    }



}


















