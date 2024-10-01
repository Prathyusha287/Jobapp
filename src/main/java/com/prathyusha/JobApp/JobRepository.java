package com.prathyusha.JobApp;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prathyusha.JobApp.job.Job;

public interface JobRepository extends JpaRepository<Job,Long> {

}
