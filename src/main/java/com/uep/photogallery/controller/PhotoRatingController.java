package com.uep.photogallery.controller;

import com.uep.photogallery.model.PhotoRating;
import com.uep.photogallery.service.PhotoRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/photos")
public class PhotoRatingController {

    private final PhotoRatingService photoRatingService;

    @Autowired
    public PhotoRatingController(PhotoRatingService photoRatingService) {
        this.photoRatingService = photoRatingService;
    }

    @PostMapping("/{photoId}/ratings")
    public ResponseEntity<PhotoRating> ratePhoto(
            @PathVariable Long photoId,
            @RequestBody Map<String, Object> payload) {
        Long userId = Long.valueOf(payload.get("userId").toString());
        Integer rating = Integer.valueOf(payload.get("rating").toString());
        PhotoRating createdRating = photoRatingService.ratePhoto(photoId, userId, rating);
        return ResponseEntity.ok(createdRating);
    }

    @GetMapping("/{photoId}/ratings")
    public ResponseEntity<List<PhotoRating>> getPhotoRatings(@PathVariable Long photoId) {
        List<PhotoRating> ratings = photoRatingService.getPhotoRatings(photoId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{photoId}/ratings/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long photoId) {
        Double averageRating = photoRatingService.getAverageRating(photoId);
        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/{photoId}/ratings/count")
    public ResponseEntity<Long> getRatingCount(@PathVariable Long photoId) {
        Long ratingCount = photoRatingService.getRatingCount(photoId);
        return ResponseEntity.ok(ratingCount);
    }

    @DeleteMapping("/ratings/{ratingId}")
    public ResponseEntity<Void> removeRating(@PathVariable Long ratingId) {
        photoRatingService.removeRating(ratingId);
        return ResponseEntity.ok().build();
    }
} 