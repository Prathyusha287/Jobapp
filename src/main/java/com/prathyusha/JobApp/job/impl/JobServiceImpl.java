package com.prathyusha.JobApp.job.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prathyusha.JobApp.JobRepository;
import com.prathyusha.JobApp.job.Job;
import com.prathyusha.JobApp.job.JobService;

@Service
public class JobServiceImpl implements JobService{
	//private List<Job> jobs = new ArrayList<>();
	
	JobRepository jobRepo;
	
	
	
	public JobServiceImpl(JobRepository jobRepo) {
		
		this.jobRepo = jobRepo;
	}

	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return jobRepo.findAll();
	}

	@Override
	public void createJob(Job job) {
		// TODO Auto-generated method stub
		
		
		jobRepo.save(job) ;
		
	}
	
	@Override
	public Job getJobById(Long id) {
		
		/*for (Job job : jobs) {
			
			if(job.getId().equals(id)) {
				return job;
			}
		}
		return null;*/
		
	return	jobRepo.findById(id).orElse(null);
		
	}

	@Override
	public boolean deleteJobById(Long id) {
		// TODO Auto-generated method stub
		/*Iterator<Job> iterator = jobs.iterator();
		while(iterator.hasNext()) {
			Job job = iterator.next();
			if(job.getId().equals(id)) {
				iterator.remove();
				return true;
			}
		}*/
		
		
	/*for (Job job : jobs) {
			
			if(job.getId().equals(id)) {
				System.out.println(job);
				jobs.remove(job);
				return true;
				
			}
		}
		return false;*/
		try {

			jobRepo.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean updateJob(Long id, Job updatedJob) {
		// TODO Auto-generated method stub
		/*for(Job job : jobs) {
			if(job.getId().equals(id)) {
				job.setTitle(updatedJob.getTitle());
				job.setDescription(updatedJob.getDescription());
				job.setMinSalary(updatedJob.getMinSalary());
				job.setMaxSalary(updatedJob.getMaxSalary());
				job.setLocation(updatedJob.getLocation());
				return true;
			}
		}
		return false;*/
		
		Optional<Job> joboptional = jobRepo.findById(id);
		if(joboptional.isPresent()) {
			Job job = joboptional.get();
			job.setTitle(updatedJob.getTitle());
			job.setDescription(updatedJob.getDescription());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setLocation(updatedJob.getLocation());
			jobRepo.save(job);
        return true;
			
		}
		return false;
		
	}

}
