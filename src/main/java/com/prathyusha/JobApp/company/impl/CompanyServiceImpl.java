package com.prathyusha.JobApp.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prathyusha.JobApp.company.Company;
import com.prathyusha.JobApp.company.CompanyRepository;
import com.prathyusha.JobApp.company.CompanyService;
import com.prathyusha.JobApp.job.Job;

@Service
public class CompanyServiceImpl implements CompanyService{

	private CompanyRepository companyRepo ;
	
	
	public CompanyServiceImpl(CompanyRepository companyRepo) {
		
		this.companyRepo = companyRepo;
	}


	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return companyRepo.findAll();
	}


	@Override
	public boolean updateCompany(Company company, Long id) {
		// TODO Auto-generated method stub
		Optional<Company> companyOptional = companyRepo.findById(id);
		if( companyOptional.isPresent()) {
			Company companyToUpdate = companyOptional.get();
			companyToUpdate.setDescription(company.getDescription());
			companyToUpdate.setName(company.getName());
			companyToUpdate.setJobs(company.getJobs());
			companyRepo.save(companyToUpdate);
        return true;
		}
		return false;
	}


	@Override
	public void createCompany(Company company) {
		// TODO Auto-generated method stub
		
		companyRepo.save(company);
		
	}


	@Override
	public boolean deleteCompany(Long id) {
		// TODO Auto-generated method stub
		if(companyRepo.existsById(id)) {
			companyRepo.deleteById(id);
			return true;
		}
		
		return false;
	}


	@Override
	public Company getCompanyById(Long id) {
		// TODO Auto-generated method stub
		return companyRepo.findById(id).orElse(null);

	}
}

