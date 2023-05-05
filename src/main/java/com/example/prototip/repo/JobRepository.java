package com.example.prototip.repo;

import com.example.prototip.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Iterable<Job> findByPriceGreaterThanEqual(int price);
}
