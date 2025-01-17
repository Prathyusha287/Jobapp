package com.prathyusha.JobApp.company;

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

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	private CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	
	@GetMapping
	public List<Company> getAllCompanies(){
		
		return companyService.getAllCompanies();
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company){
		
		companyService.updateCompany(company,id);
		return new ResponseEntity<>("company updated successfully",HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody Company company){
		
		companyService.createCompany(company);
		return new ResponseEntity<String>("Company added successfully",HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id){
		boolean deleted = companyService.deleteCompany(id);
		if(deleted) {
			return new ResponseEntity<String>("Company deleted successfully",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Company Not found",HttpStatus.NOT_FOUND);		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
		
		Company company = companyService.getCompanyById(id);
		if(company != null) {
			return new ResponseEntity<>(company,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	} 

	
}
