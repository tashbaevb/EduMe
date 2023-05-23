package com.example.prototip.repo;

import com.example.prototip.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM Post as p  WHERE p.title LIKE %:query% or p.full_text LIKE %:query%",
            nativeQuery = true)
    List<Post> findByTitleOrContentLike(@Param("query") String query);
}
