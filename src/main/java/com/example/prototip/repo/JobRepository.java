package com.example.prototip.repo;

import com.example.prototip.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(value = "SELECT * FROM job as p WHERE (p.title LIKE %:query% OR p.full_text LIKE %:query%) AND p.price > :minPrice", nativeQuery = true)
    Iterable<Job> findByTitleOrContentLike(@Param("query") String query, @Param("minPrice") int minPrice);

}
