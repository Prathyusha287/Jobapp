package com.prathyusha.JobApp.Review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prathyusha.JobApp.company.Company;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
	
	
	private static final int ResponseEntity = 0;
	private ReviewService  reviewService  ;

	public ReviewController(ReviewService reviewService) {
	
		this.reviewService = reviewService;
	}
	
    @GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllreviews( Long companyId){
		
    	return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
	}

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId ,@RequestBody Review review){
		
    	if(reviewService.createReview(companyId,review)) {
    	return new ResponseEntity<String>("Review added successfully",HttpStatus.CREATED); 
    	
    	}
    	return new ResponseEntity<>("Review  failed to add ",HttpStatus.NOT_FOUND); 
    	
    }
    
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId ,@PathVariable Long reviewId){
    	
    	return new ResponseEntity<Review>(reviewService.getReview(companyId, reviewId),HttpStatus.OK);
    }
    
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review updatedReview){
    	
    	boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, updatedReview);
    	if (isReviewUpdated) {
    		return new ResponseEntity<String>("Review updated successfully",HttpStatus.OK);
    	}
    	return new ResponseEntity<String>("Review not updated",HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
    
    	boolean isReviewDeleted= reviewService.deleteReview(companyId, reviewId);
    	if (isReviewDeleted) {
    		return new ResponseEntity<String>("Review deleted successfully",HttpStatus.OK);
    	}
    	return new ResponseEntity<String>("Review not deleted",HttpStatus.NOT_FOUND);
    }
    
} 
