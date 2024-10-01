package com.prathyusha.JobApp.Review.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prathyusha.JobApp.Review.Review;
import com.prathyusha.JobApp.Review.ReviewRepository;
import com.prathyusha.JobApp.Review.ReviewService;
import com.prathyusha.JobApp.company.Company;
import com.prathyusha.JobApp.company.CompanyService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewRepository reviewRepo ;
	
	private CompanyService companyService;

	public ReviewServiceImpl(ReviewRepository reviewRepo, CompanyService companyService) {
		
		this.reviewRepo = reviewRepo;
		this.companyService = companyService;;
	}


	@Override
	public List<Review> getAllReviews(Long companyId) {
		// TODO Auto-generated method stub
		List<Review> reviews = reviewRepo.findByCompanyId(companyId);
		return reviews;
	}


	@Override
	public boolean createReview(Long companyId,Review review) {
		
		Company company = companyService.getCompanyById(companyId);
		if(company != null) {
			
			review.setCompany(company);
			reviewRepo.save(review);
			return true;
		}
		return false;
		
	}


	@Override
	public Review getReview(Long companyId, Long reviewId) {
		// TODO Auto-generated method stub
		List<Review> reviews = reviewRepo.findByCompanyId(companyId);
		
		return reviews.stream()
				.filter(review-> review.getId().equals(reviewId))
				.findFirst()
				.orElse(null);
	}


	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
		// TODO Auto-generated method stub
		if(companyService.getCompanyById(companyId) != null && reviewRepo.existsById(reviewId) ) {
			updatedReview.setCompany(companyService.getCompanyById(companyId));
			updatedReview.setId(reviewId);
			reviewRepo.save(updatedReview);
			return true;
		}else {
			return false;
		}
		
	}


	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
		// TODO Auto-generated method stub
		
		if(companyService.getCompanyById(companyId) != null && reviewRepo.existsById(reviewId)) {
		 Review review = reviewRepo.findById(reviewId).orElse(null);
		 Company company = review.getCompany();
		 company.getReviews().remove(review);
		 review.setCompany(null);
			reviewRepo.deleteById(reviewId);
			return true;
		}else {
			return false;
		}
		
	}


	

}
