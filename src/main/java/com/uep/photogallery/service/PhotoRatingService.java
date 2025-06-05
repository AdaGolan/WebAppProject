package com.uep.photogallery.service;

import com.uep.photogallery.model.Photo;
import com.uep.photogallery.model.PhotoRating;
import com.uep.photogallery.model.User;
import com.uep.photogallery.repository.PhotoRatingRepository;
import com.uep.photogallery.repository.PhotoRepository;
import com.uep.photogallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhotoRatingService {
    private final PhotoRatingRepository photoRatingRepository;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    @Autowired
    public PhotoRatingService(PhotoRatingRepository photoRatingRepository, 
                            PhotoRepository photoRepository,
                            UserRepository userRepository) {
        this.photoRatingRepository = photoRatingRepository;
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PhotoRating ratePhoto(Long photoId, Long userId, Integer rating) {
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Photo not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        PhotoRating photoRating = new PhotoRating(photo, user, rating);
        return photoRatingRepository.save(photoRating);
    }

    public List<PhotoRating> getPhotoRatings(Long photoId) {
        return photoRatingRepository.findByPhotoId(photoId);
    }

    public Double getAverageRating(Long photoId) {
        return photoRatingRepository.getAverageRatingForPhoto(photoId);
    }

    public Long getRatingCount(Long photoId) {
        return photoRatingRepository.getRatingCountForPhoto(photoId);
    }

    @Transactional
    public void removeRating(Long ratingId) {
        photoRatingRepository.deleteById(ratingId);
    }
} 