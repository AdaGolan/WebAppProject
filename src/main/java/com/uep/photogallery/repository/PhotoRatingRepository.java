package com.uep.photogallery.repository;

import com.uep.photogallery.model.PhotoRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRatingRepository extends JpaRepository<PhotoRating, Long> {
    List<PhotoRating> findByPhotoId(Long photoId);
    Optional<PhotoRating> findByPhotoIdAndUserId(Long photoId, Long userId);
    
    @Query("SELECT AVG(r.rating) FROM PhotoRating r WHERE r.photo.id = ?1")
    Double getAverageRatingForPhoto(Long photoId);
    
    @Query("SELECT COUNT(r) FROM PhotoRating r WHERE r.photo.id = ?1")
    Long getRatingCountForPhoto(Long photoId);
} 