package com.quest.buyout.service;

import com.quest.buyout.dao.CustomerRepository;
import com.quest.buyout.dao.ProductRepository;
import com.quest.buyout.dao.ReviewRepository;
import com.quest.buyout.model.Customer;
import com.quest.buyout.model.OutLaw;
import com.quest.buyout.model.Product;
import com.quest.buyout.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class ReviewService {


    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Review> index(){ return reviewRepository.findAll();  }

    public Optional<Review> findReviewById( HashMap<String, String> body){
        String _id = body.get("_id");
        return reviewRepository.findById(_id);
    }

    public ArrayList<Review> findReviewsByIds(List<HashMap<String, String>> body){
        ArrayList<Review> findList = new ArrayList<>();

        for(HashMap<String, String> newBody : body){
            Review review = reviewRepository.findById(newBody.get("_id")).orElse(null);
            findList.add(review);
        }
        return findList;
    }


    public Stream<Review> reviewsByCustomer(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        return reviewRepository
                .findAll()
                .stream()
                .filter(review -> review.getCustomerId().equals(body.get("customerId")));
    }

    public Stream<Review> productReviews(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        return reviewRepository
                .findAll()
                .stream()
                .filter(review -> review.getProductId().equals(body.get("productId")));
    }


    public Stream<Review> sellerReviews(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);
        return reviewRepository.findAll().stream()
                .filter(review -> review.getSellerId().equals(body.get("sellerId")));

    }

    public OutLaw averageProductStar(HashMap<String, String> body){
        String productId = body.get("productId");

        // get the sum of all the stars of a particular product
        double starSum = 0.0;
        double countRating = 0.0;
        for(int z=0; z<reviewRepository.count(); z++){
            if(reviewRepository.findAll().get(z).getProductId().equals(productId)){
                starSum += Double.parseDouble(reviewRepository.findAll().get(z).getStars());
                countRating += 1;
            }
        }
        // find the average star
        double avg = starSum / countRating;

        // if the rating of the product is less than 3,
        // show stars of 3 for the product on the client side
        return new OutLaw(String.format(Locale.US, "%.2f", avg), String.valueOf(countRating));
    }


    public String countReviews(){ return String.valueOf(reviewRepository.count()); }

    public OutLaw countCustomerReviews(HashMap<String, String> body){

        String customerId = body.get("customerId");
        String ms = String.valueOf(reviewRepository
                .findAll()
                .stream()
                .filter(review -> review.getCustomerId().equals(customerId)).count());

        return new OutLaw(ms);
    }

    public OutLaw countProductReviews(HashMap<String, String> body){

        String ms = String.valueOf(reviewRepository
                .findAll()
                .stream()
                .filter(review -> review.getProductId().equals(body.get("productId"))).count());

        return new OutLaw(ms);
    }

    // find the customer with the highest written reviews


    public OutLaw createReview(HashMap<String, String> body){
        reviewRepository.save(mkReview(body));
        return new OutLaw("Review created");
    }


    public String[] createReviews(List<Review> body){
        for(Review review : body){
            reviewRepository.save(review);
        }
        return new String[]{"multiple reviews created"};
    }

    public OutLaw thumbs(HashMap<String, String> body){
        String sub = body.get("sub");
        String subId = body.get("subId");
        String msg = "Couldn't update review";

        Review review = reviewRepository.findById(subId).orElse(null);

        if(review != null){
            String strU = review.getThumbs()[0];
            String strD = review.getThumbs()[1];

            switch (sub){
                case "thumbsUp":
                    int tU = strU.isEmpty() ? 0 : Integer.parseInt(strU);
                    tU += 1;
                    strU = String.valueOf(tU);
                    String[] thumbsUp = {strU, strD};
                    review.setThumbs(thumbsUp);
                    msg = strU;
                    break;
                case "thumbsDown":
                    int tD = strD.isEmpty() ? 0 : Integer.parseInt(strD);
                    tD += 1;
                    strD = String.valueOf(tD);
                    String[] thumbsDown = {strU, strD};
                    review.setThumbs(thumbsDown);
                    msg = strD;
                    break;
                default:
                    // do nothing
                    break;
            }
            reviewRepository.save(review);
        }
        return new OutLaw(msg);
    }


    public OutLaw deleteReivewById(HashMap<String, String> body ){

        String _id = body.get("_id");
        reviewRepository.deleteById(_id);
        return new OutLaw("Review record deleted");
    }


    public String[] deleteReivewsByIds(List<HashMap<String, String>> body){
        for(HashMap<String, String> newBody : body){
            reviewRepository.deleteById(newBody.get("_id"));
        }
        return new String[] {"Multiple review records deleted"};
    }

    public OutLaw deleteCustomerReivewById(HashMap<String, String> body){

        String customerId = body.get("customerId");
        String reviewId = body.get("reviewId");

        Review rev = reviewRepository
                .findAll()
                .stream()
                .filter(review -> review.getCustomerId().equals(customerId) &&
                        review.getProductId().equals(reviewId))
                .findFirst().orElse(null);

        reviewRepository.delete(rev);
        return new OutLaw("Review record deleted");
    }


    public OutLaw deleteAllOfCustomerReviews(HashMap<String, String> body){
        String customerId = body.get("customerId");
        for(int j=0; j<reviewRepository.count(); j++){
            Review review = reviewRepository.findAll().get(j);
            if(review.getCustomerId().equals(customerId)){
                reviewRepository.delete(review);
            }
        }
        return new OutLaw("All of customer reviews are deleted");
    }


    public String deleteAll(){
        reviewRepository.deleteAll();
        return "All review records deleted";
    }


    public OutLaw updateReviewById(HashMap<String, String> body){

        // use the _id to get the old review
        Review oldReview = reviewRepository.findById(body.get("_id")).orElse(null);

        // create a review of the incoming properties
        Review newReview = mkReview(body);

        // compare old to new and update old review accordingly
        Review updatedOldReview = compareReview(oldReview, newReview);

        reviewRepository.save(updatedOldReview);    // save updated old review
        return new OutLaw("Review updated");
    }


    public String[] updateReviewsByIds(List<Review> body){
        for(Review rv : body){
            // use the _id to get the old review
            Review oldReview = reviewRepository.findById(rv.get_id()).orElse(null);
            if(oldReview != null){
                // compare old to new and update old review accordingly
                Review updateOldReview = compareReview(oldReview, rv);
                reviewRepository.save(updateOldReview);    // save new review
            }

        }
        return new String[]{"Multiple reviews updated"};
    }


    private Review mkReview(HashMap<String, String> body){
        Review review = new Review(
                body.get("productId"),
                body.get("customerId"),
                body.get("msg"),
                body.get("stars")
        );
        review.setTime(timeConverter());

        Product product = productRepository.findById(body.get("productId")).orElse(null);
        Customer customer = customerRepository.findById(body.get("customerId")).orElse(null);

        if(product != null && customer != null){
            review.setSellerId(product.getSellerId());
            review.setCustomerName(String.format("%s %s", customer.getFirstName(), customer.getLastName()));
        }
        return review;
    }

    private String timeConverter(){
        Date date = new Date();
        long millis = date.getTime();

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        return String.format("%s-%s-%s", day, month, year);
    }

    private Review compareReview(Review oldReview, Review newReview){
        oldReview.setStars((newReview.getStars() != null && newReview.getStars() != "")
                ? newReview.getStars() : oldReview.getStars());

        oldReview.setMsg((newReview.getMsg() != null && newReview.getMsg() != "")
                ? newReview.getMsg() : oldReview.getMsg());

        return oldReview;
    }


    public Stream<Review> reviewsOfTargetCustomer(List<HashMap<String, String>> newBody){
        HashMap<String, String> body = newBody.get(0);

        String pwd = body.get("pwd");
        String action = body.get("action");

        Customer theCus = customerRepository.findAll()
                .stream()
                .filter( customer -> customer.getPassword().equals(pwd))
                .findFirst()
                .orElse(null);

        if(theCus != null && action.equals("readReview")) {
            String customerId = theCus.get_id();
            // return customer reviews
            return reviewRepository.findAll()
                    .stream()
                    .filter(review1 -> review1.getCustomerId().equals(customerId));
        }

        return null;
    }




}
