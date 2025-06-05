package com.uep.photogallery.repository;

import com.uep.photogallery.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByTitleContainingIgnoreCase(String title);
    List<Photo> findByDescriptionContainingIgnoreCase(String description);
} 