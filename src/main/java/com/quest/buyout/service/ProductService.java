package com.quest.buyout.service;

import com.quest.buyout.dao.ProductRepository;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.model.Product;
import com.quest.buyout.model.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private int increase = 5;

    public List<Product> index(){ return productRepository.findAll(); }

    public List<Product> productsInParts(@RequestBody List<HashMap<String, String>> body){

        String part = body.get(0).get("part"); // 1
        List<Product> list = new ArrayList<>();
        int numberPart = Integer.parseInt(part);
        int mult = numberPart * increase; // 5

        int repositoryLength = (int) productRepository.count();
        int start = mult - increase; // 0
        int end = repositoryLength > mult ? mult : repositoryLength;

        if(repositoryLength > start){
            list = productRepository.findAll().subList(start, end);
        }

      return list;
    }


    // when load more floating button of the respective field is clicked
    public List<Product> generalProductsInParts(@RequestBody List<HashMap<String, String>> newBody){

        HashMap<String, String> body = newBody.get(0);
        String ref = body.get("ref"); // product, hotsales,history,trending,category,similarProducts
        String part = body.get("part");
        String other = body.get("other");

        List<Product> repository = new ArrayList<>();
        List<Product> list = new ArrayList<>();
        int numberPart = Integer.parseInt(part);
        int mult = numberPart * increase; // 5
        int start = mult - increase; // 0
        
        switch (ref){
            // when a category is clicked from home
            case "category":
                for(int j=0;j<productRepository.count();j++){
                    Product product = productRepository.findAll().get(j);
                    if(product.getCategory().contains(other)){
                        repository.add(product);
                    }
                }
                break;


             case "similar":      // other = productGroup[1] == subCategory
             case "viewed":  // other = productGroup[2]
                for(int j=0;j<productRepository.count();j++){
                    Product product = productRepository.findAll().get(j);
                    if(product.getProductGroup().contains(other)){
                        repository.add(product);
                    }
                }
                break;

// eraser
// pic art

            // seller recommended products ie
            // products from the same seller
            // other = sellerId
            case "seller":
                for(int j=0;j<productRepository.count();j++){
                    Product product = productRepository.findAll().get(j);
                    if(product.getSellerId().contains(other)){
                        repository.add(product);
                    }
                }
                break;


            case "explore":
            case "product":
            case "hotsales":
            case "trending":
                for(int j=0;j<productRepository.count();j++){
                    Product product = productRepository.findAll().get(j);
                    if(product.getStatus().contains(other)){
                        repository.add(product);
                    }
                }
                break;

                // when a subCategory is clicked from All caegories page
            case  "subCategory":
                String[] cAndSub = other.split(",");
                String category = cAndSub[0];
                String subCategory = cAndSub[1];

                for(int j=0;j<productRepository.count();j++){
                    Product product = productRepository.findAll().get(j);
                    if(product.getCategory().contains(category) &&
                            product.getSubCategory().contains(subCategory)){
                        repository.add(product);
                    }
                }

                break;
                default:
                    // do nothing
                    break;
        }

        int repositoryLength = repository.size();
        int end = repositoryLength > mult ? mult : repositoryLength;
        if(repositoryLength > start){
            list = repository.subList(start, end);
        }
        return list;

    }


    public Optional<Product> findProductById(HashMap<String, String> body){
        String _id = body.get("_id");
        return productRepository.findById(_id);
    }

    public ArrayList<Product> findProductsByIds(List<HashMap<String, String>> body){
        ArrayList<Product> findList = new ArrayList<>();
        for(HashMap<String, String> newBody : body){
            Product product = productRepository.findById(newBody.get("_id")).orElse(null);
            findList.add(product);
        }
        return findList;
    }

    public Stream<Product> findProductsOutOfStock(){
        return productRepository.findAll()
                .stream()
                .filter(product -> (Integer.parseInt(product.getStock()) <= 0 ));
    }

    public Stream<Product> findProductsByCategory(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        String category = body.get("category");
        return productRepository.findAll().stream()
                .filter(product -> product.getCategory().contains(category));
    }

    public List<Product> generalSearch(List<HashMap<String, String>> newBody){

        HashMap<String, String> body = newBody.get(0);

        String ref = body.get("ref"); // from home, category, status
        String part = body.get("part");
        String word = body.get("word");
        String other = body.get("other");

        List<Product> repository = new ArrayList<>();
        List<Product> list = new ArrayList<>();
        int numberPart = Integer.parseInt(part);
        int mult = numberPart * increase; // 5
        int start = mult - increase; // 0

        /**
         * Search can only be done from home or category point of view
         * I've omitted searching from status so eg if user searches after opening
         *  a status like trending products, the results will be as if he searched from home
         *  But when you search from a category like man, you will only get products form that category
         */

        // Search can be made from home or category (ive ommitted status searching)
        // searching from a status should search entire product
        // trending,history,hotsales
//        if(ref.equals("history") ||
//                ref.equals("status")){
//            ref = "home";
//            other = "product";
//        }

        if(ref.equals("category")){
            for(int j=0;j<productRepository.count();j++) {
                Product product = productRepository.findAll().get(j);
                if (product.getCategory().contains(other) &&
                        product.getSearchQuery().contains(word)) {
                    repository.add(product);
                }
            }
        } else {
                for(int j=0;j<productRepository.count();j++){
                    Product product = productRepository.findAll().get(j);
                    if(product.getSearchQuery().contains(word)){
                        repository.add(product);
                    }
                }
        }

//        switch (ref){
//            case "home":
//                for(int j=0;j<productRepository.count();j++){
//                    Product product = productRepository.findAll().get(j);
//                    if(product.getSearchQuery().contains(word)){
//                        repository.add(product);
//                    }
//                }
//                break;
//            case "category":
//                for(int j=0;j<productRepository.count();j++){
//                    Product product = productRepository.findAll().get(j);
//                    if(product.getCategory().contains(other) &&
//                            product.getSearchQuery().contains(word)){
//                        repository.add(product);
//                    }
//                }
//                break;
//            case "status":
//                for(int j=0;j<productRepository.count();j++){
//                    Product product = productRepository.findAll().get(j);
//                    if(product.getStatus().contains(other) &&
//                            product.getSearchQuery().contains(word)){
//                        repository.add(product);
//                    }
//                }
//                break;
//                default:
//                    // do nothing
//                    break;
//        }

        // if user searches by productId it should work in all refs
        Product prod = productRepository.findAll().stream()
                .filter(product -> product.get_id().contains(word))
                .findFirst()
                .orElse(null);
        if(prod != null){ repository.add(prod); }

        int repositoryLength = repository.size();
        int end = repositoryLength > mult ? mult : repositoryLength;
        if(repositoryLength > start){
            list = repository.subList(start, end);
        }
        return list;

    }

    public Stream<Product> findProductsBySearch(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        String word = body.get("search");
        return productRepository.findAll().stream()
                .filter(product -> product.getSearchQuery().contains(word));
    }

//    public List<Product> findProductsBySearchInParts(List<HashMap<String, String>> newBody){
//        HashMap<String, String> body = newBody.get(0);
//        String word = body.get("search");
//        String part = body.get("part"); // 1
//        List<Product> repository = new ArrayList<>();
//
//        List<Product> list = new ArrayList<>();
//        int numberPart = Integer.parseInt(part);
//        int mult = numberPart * increase; // 5
//        int start = mult - increase; // 0
//
//        for(int j=0;j<productRepository.count();j++){
//            Product product = productRepository.findAll().get(j);
//            if(product.getSearchQuery().contains(word)){
//                repository.add(product);
//            }
//        }
//
//        int repositoryLength = repository.size();
//        int end = repositoryLength > mult ? mult : repositoryLength;
//        if(repositoryLength > start){
//            list = repository.subList(start, end);
//        }
//        return list;
//    }




//    public Stream<Product> searchCategory(List<HashMap<String, String>> newBody){
//        HashMap<String, String> body = newBody.get(0);
//        String category = body.get("category");
//        String word = body.get("search");
//
//        return productRepository.findAll().stream()
//                .filter(product -> product.getCategory().contains(category) &&
//                        product.getSearchQuery().contains(word));
//    }

//    public List<Product> searchCategoryInParts(List<HashMap<String, String>> newBody){
//        HashMap<String, String> body = newBody.get(0);
//        String word = body.get("search");
//        String category = body.get("category");
//        String part = body.get("part"); // 1
//        List<Product> repository = new ArrayList<>();
//
//        List<Product> list = new ArrayList<>();
//        int numberPart = Integer.parseInt(part);
//        int mult = numberPart * increase; // 5
//        int start = mult - increase; // 0
//
//        for(int j=0;j<productRepository.count();j++){
//            Product product = productRepository.findAll().get(j);
//            if(product.getSearchQuery().contains(word) &&
//                    product.getCategory().contains(category)){
//                repository.add(product);
//            }
//        }
//
//        int repositoryLength = repository.size();
//        int end = repositoryLength > mult ? mult : repositoryLength;
//        if(repositoryLength > start){
//            list = repository.subList(start, end);
//        }
//        return list;
//    }





    public Stream<Product> searchByStatus(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        String word = body.get("search");
        String status = body.get("status");

        return productRepository.findAll().stream()
                .filter(product -> product.getStatus().equals(status) &&
                        product.getSearchQuery().contains(word));
    }

    public Stream<Product> getProductsBySubCategory(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        String category = body.get("category");
        String subCategory = body.get("subCategory");

        return productRepository.findAll().stream()
                .filter(product -> product.getCategory().contains(category) &&
                        product.getSubCategory().contains(subCategory));
    }


    public ArrayList<SubCategory> getSubCategories(List<HashMap<String, String>> newBody){
        ArrayList<String> list = new ArrayList<>();
        HashMap<String, String> body = newBody.get(0);
        String category = body.get("category");

        for(int z=0; z<productRepository.count(); z++){
            Product product = productRepository.findAll().get(z);
            if(product.getCategory().contains(category)){ list.add(product.getSubCategory()); }
        }

        LinkedHashSet<String> hashSet = new LinkedHashSet<>(list);
        ArrayList<SubCategory> newList = new ArrayList<>();

        for (String str : hashSet){ newList.add(new SubCategory(category, str)); }
        return newList;
    }


    public List<Product> featuredProducts(){
        List<Product> list = productRepository.findAll();
        if(list.size() > 7) {
            return list.subList(0, 7);
        }
        return list;
    }

    public List<Product> featuredHotsales(){
        ArrayList<Product> list = new ArrayList<>();
        for(int j=0;j<productRepository.count();j++){
            Product product = productRepository.findAll().get(j);
            if(product.getStatus().contains("hotsales")){
                list.add(product);
            }
        }
        if (list.size() > 7){
            return list.subList(0,7);
        }
        return list;
    }

    public Stream<Product> hotsales(){
        return productRepository.findAll().stream()
                .filter(product -> product.getStatus().contains("hotsales"));
    }


//    public List<Product> hotSalesInParts(List<HashMap<String, String>> body){
//        String part = body.get(0).get("part"); // 1
//        List<Product> repository = new ArrayList<>();
//        List<Product> list = new ArrayList<>();
//        int numberPart = Integer.parseInt(part);
//
//        int mult = numberPart * increase; // 5
//        int start = mult - increase; // 0
//
//        for(int j=0;j<productRepository.count();j++){
//            Product product = productRepository.findAll().get(j);
//            if(product.getStatus().equals("hotsales")){
//                repository.add(product);
//            }
//        }
//
//        int repositoryLength = repository.size();
//        int end = repositoryLength > mult ? mult : repositoryLength;
//        if(repositoryLength > start){
//            list = repository.subList(start, end);
//        }
//        return list;
//    }





    public Stream<Product> boughtTogether(List<HashMap<String, String>> body){
        HashMap<String, String> newBody = body.get(0);
        String boughtTogether = newBody.get("boughtTogether"); // productGroup[3]
        return productRepository.findAll().stream()
                .filter(product -> product.getStatus().contains(boughtTogether));
    }

    public Stream<Product> similarProducts(List<HashMap<String, String>> body){
        HashMap<String, String> newBody = body.get(0);
        String productFamily = newBody.get("productGroupType"); // productGroup[2]
        return productRepository.findAll().stream()
                .filter(product -> product.getProductGroup().contains(productFamily));
    }

    public Stream<Product> sellerRecommendation(List<HashMap<String, String>> body){
        String sellerId = body.get(0).get("sellerId");
        return productRepository.findAll().stream()
                .filter(product -> product.getSellerId().contains(sellerId));
    }


    public Product productOfTheDay(){
       return productRepository
               .findAll()
               .stream()
               .filter(product -> product.getStatus().contains("today"))
               .findFirst()
               .orElse(null);
    }


    public String countProducts(){ return String.valueOf(productRepository.count()); }

    public String countProductsOutOfStock(){
        return String.valueOf(productRepository.findAll()
                .stream()
                .filter( product -> Integer.parseInt(product.getStock()) <= 0 ).count() );
    }

    public String productTotalStock() {
        int sum = 0;
        for(int y=0; y<productRepository.count(); y++){
            sum += Integer.parseInt(productRepository.findAll().get(y).getStock());
        }
        return String.valueOf(sum);
    }


    public OutLaw createProduct(HashMap<String, String> body){
        productRepository.save(productMkProduct(body));
        return new OutLaw("Product created");
    }

    public String[] createProducts(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            productRepository.save(productMkProduct(newBody));
        }
        return new String[]{"Multiple products created"};
    }


    public OutLaw updateProductById(HashMap<String, String> body){
        // use the _id to get the old object
        Product oldProduct = productRepository.findById(body.get("_id")).orElse(null);

        if(oldProduct != null){
            Product newProduct = productMkProduct(body);// create an object of the incoming Properties
            Product updateOldProduct = productCompareProducts(oldProduct, newProduct);      // compare old to new
            productRepository.save(updateOldProduct); //save product
        }
        return new OutLaw("Product updated");
    }

    public String[] updateProductsByIds(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            // use the _id to get the old object
            Product oldProduct = productRepository.findById(newBody.get("_id")).orElse(null);
            if(oldProduct != null){
                Product newProduct = productMkProduct(newBody);// create an object of the incoming Properties
                Product updateOldProduct = productCompareProducts(oldProduct, newProduct);   // compare old to new
                productRepository.save(updateOldProduct);  //save product
            }
        }
        return new String[]{"Products updated"};
    }

    public String updateSizeColor(HashMap<String, String> body){

        String sizes = body.get("availSizes");
        String colors = body.get("availColors");
        String msg = "failed to update";

        if(sizes.length() > 0 && colors.length() > 0){
            for(int z=0; z<productRepository.count(); z++){
                Product product = productRepository.findAll().get(z);
                product.setAvailSize(sizes);
                product.setAvailColors(colors);
                productRepository.save(product);
            }
            msg = "Update sizes and colors";
        }
        return msg;
    }

    public OutLaw discountStatusUpdate(HashMap<String, String> body){

        String msg = "Failed to update";
        String proOrSale = body.get("proOrSale");
        String sub = body.get("sub");
        String subId = body.get("subId");

        // change status or discount of all products
        for(int z=0; z<productRepository.count(); z++){
            if(productRepository.findAll().get(z).getStatus().equals(proOrSale)){
                Product product = productRepository.findAll().get(z);
                switch (sub){
                    case "status":
                        product.setStatus(subId);
                        msg = "Status updated";
                        break;
                    case "discount":
                        product.setDiscount(subId);
                        msg = "Discount updated";
                        break;
                    default:
                        // do nothing
                        break;
                }
                productRepository.save(product);
            }
        }
        return new OutLaw(msg);
    }

    public OutLaw deleteProductById(HashMap<String, String> body ){
        String _id = body.get("_id");
        productRepository.deleteById(_id);
        return new OutLaw("Product deleted");
    }

    public String[] deleteProductsByIds(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            productRepository.deleteById(newBody.get("_id"));
        }
        return new String[]{"Multiple products deleted"};
    }

    public String deleteAll(){
        productRepository.deleteAll();
        return "All deleted";
    }

    private Product productMkProduct(HashMap<String, String> body){

        String[] images = {
                body.get("imgMain"),
                body.get("imgSecond"),
                body.get("imgThird")};

        return new Product(
                body.get("name"),
                body.get("about"),
                body.get("material"),
                body.get("availColors"),
                body.get("availSize"),
                body.get("price"),
                body.get("stock"),
                body.get("category"),
                body.get("subCategory"),
                body.get("sellerId"),
                body.get("discount"),
                body.get("searchQuery"),
                body.get("status"),
                body.get("productGroup"),
                images
        );
    }



    private Product productCompareProducts(Product oldProduct, Product newProduct){
        // sellerId can't be updated
        // product Ids can't be updated, it is provided by the server

        oldProduct.setName(newProduct.getName() != null && newProduct.getName() != "" ? newProduct.getName() : oldProduct.getName());
        oldProduct.setAbout(newProduct.getAbout() != null && newProduct.getAbout() != "" ? newProduct.getAbout() : oldProduct.getAbout());
        oldProduct.setMaterial(newProduct.getMaterial() != null && newProduct.getMaterial() != "" ? newProduct.getMaterial() : oldProduct.getMaterial());
        oldProduct.setAvailColors(newProduct.getAvailColors() != null  ? newProduct.getAvailColors() : oldProduct.getAvailColors());

        oldProduct.setAvailSize(newProduct.getAvailSize() != null ? newProduct.getAvailSize() : oldProduct.getAvailSize());
        oldProduct.setPrice(newProduct.getPrice() != null && newProduct.getPrice() != "" ? newProduct.getPrice() : oldProduct.getPrice() );
        oldProduct.setStock(newProduct.getStock() != null && newProduct.getStock() != "" ? newProduct.getStock() : oldProduct.getStock());
        oldProduct.setCategory(newProduct.getCategory() != null && newProduct.getCategory() != "" ? newProduct.getCategory() : oldProduct.getCategory());

        oldProduct.setSubCategory(newProduct.getSubCategory() != null && newProduct.getSubCategory() != "" ? newProduct.getSubCategory() : oldProduct.getSubCategory());
        oldProduct.setDiscount(newProduct.getDiscount() != null && newProduct.getDiscount() != "" ? newProduct.getDiscount() : oldProduct.getDiscount());
        oldProduct.setSearchQuery(newProduct.getSearchQuery() != null && newProduct.getSearchQuery() != "" ? newProduct.getSearchQuery() : oldProduct.getSearchQuery());
        oldProduct.setStatus(newProduct.getStatus() != null && newProduct.getStatus() != "" ? newProduct.getStatus() : oldProduct.getStatus());

        oldProduct.setProductGroup(newProduct.getProductGroup() != null && !newProduct.getProductGroup().isEmpty() ?
                newProduct.getProductGroup() : oldProduct.getProductGroup());
        oldProduct.setImages(newProduct.getImages()[0] != null ? newProduct.getImages() : oldProduct.getImages());

        return oldProduct;
    }



}
