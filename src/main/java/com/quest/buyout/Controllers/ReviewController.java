
package com.quest.buyout.Controllers;

import com.quest.buyout.model.OutLaw;
import com.quest.buyout.model.Review;
import com.quest.buyout.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    // default constructor
    public ReviewController(){}

    // TODO FIND

    // true
    // find all reviews
    @GetMapping("/v1/review")
    public List<Review> index(){ return reviewService.index();  }


    // true
    // find one review byId
    @PostMapping("/v1/review/_id")
    public Optional<Review> findReviewById(@RequestBody HashMap<String, String> body){
        return reviewService.findReviewById(body);
    }

    // true
    // find multiple reviews
    @PostMapping("/v1/review/_ids")
    public ArrayList<Review> findReviewsByIds(@RequestBody List<HashMap<String, String>> body){
        return reviewService.findReviewsByIds(body);
    }


    // true
    // find all the reviews by a customer             // app
    @PostMapping("/v1/review/customer")
    public Stream<Review> reviewsByCustomer(@RequestBody List<HashMap<String, String>> newBody){
        return reviewService.reviewsByCustomer(newBody);
    }

    // find reviews of a product by productId         // app
    @PostMapping("/v1/review/product")
    public Stream<Review> productReviews(@RequestBody List<HashMap<String, String>> newBody){
        return reviewService.productReviews(newBody);
    }

    // find reviews of a product using sellerId
    @PostMapping("/v1/review/seller")
    public Stream<Review> sellerReviews(@RequestBody List<HashMap<String, String>> newBody){
        return reviewService.sellerReviews(newBody);
    }

    // find the average star of a product
    @PostMapping("/v1/review/stars/product")
    public OutLaw averageProductStar(@RequestBody HashMap<String, String> body){
        return reviewService.averageProductStar(body);
    }


    // TODO COUNT

    // true
    // count all reviews
    @GetMapping("/v1/review/count")
    public String countReviews(){ return reviewService.countReviews(); }

    // true
    // count reviews of a customer        // app
    @PostMapping("/v1/customer/review/count")
    public OutLaw countCustomerReviews(@RequestBody HashMap<String, String> body){
        return reviewService.countCustomerReviews(body);
    }


    // count reviews of a product         // app
    @PostMapping("/v1/product/review/count")
    public OutLaw countProductReviews(@RequestBody HashMap<String, String> body){
        return reviewService.countProductReviews(body);
    }

    // find the customer with the highest written reviews


    //    TODO CREATE

    // true
    // create a review record
    @PostMapping("/v1/review")            // app
    public OutLaw createReview(@RequestBody HashMap<String, String> body){
        return reviewService.createReview(body);
    }

    // true
    // create multiple reviews
    @PostMapping("/v1/reviews")
    public String[] createReviews(@RequestBody List<Review> body){
        return reviewService.createReviews(body);
    }

    // like / dislike review
    @PostMapping("/v1/review/thumbs")
    public OutLaw thumbs(@RequestBody HashMap<String, String> body){
        return reviewService.thumbs(body);
    }



    // TODO DELETE

    // true
    // delete a review
    @PostMapping("/v1/review/delete")
    public OutLaw deleteReivewById(@RequestBody HashMap<String, String> body ){
        return reviewService.deleteReivewById(body);
    }

    // true
    // delete multiple reviews
    @PostMapping("/v1/review/deletes")
    public String[] deleteReivewsByIds(@RequestBody List<HashMap<String, String>> body){
        return reviewService.deleteReivewsByIds(body);
    }

    // delete review record of a customer
    @PostMapping("/v1/customer/review/delete")
    public OutLaw deleteCustomerReivewById(@RequestBody HashMap<String, String> body){
        return reviewService.deleteCustomerReivewById(body);
    }

    // delete all review records of a customer
    @PostMapping("/v1/customer/review/deletes")
    public OutLaw deleteAllOfCustomerReviews(@RequestBody HashMap<String, String> body){
        return reviewService.deleteAllOfCustomerReviews(body);
    }



    // true
    // delete all review records
    // only in production builds
    @GetMapping("/v1/review/del_all_admin")
    public String deleteAll(){
        return reviewService.deleteAll();
    }


    // TODO UPDATE

    // true
    // update a review
    @PostMapping("/v1/review/updateId")
    public OutLaw updateReviewById(@RequestBody HashMap<String, String> body){
        return reviewService.updateReviewById(body);
    }

    // true
    //update multiple reviews
    @PostMapping("/v1/review/updateIds")
    public String[] updateReviewsByIds(@RequestBody List<Review> body){
        return reviewService.updateReviewsByIds(body);
    }


    // TODO THIRD PARTY

    // true
    //  third party clients to communicate with our api
    // they can ask for users password and the action they want to perform
    @PostMapping("/v1/review/targetCustomer")
    public Stream<Review> reviewsOfTargetCustomer(@RequestBody List<HashMap<String, String>> newBody){
        return reviewService.reviewsOfTargetCustomer(newBody);
    }






}
