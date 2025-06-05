package com.uep.photogallery.controller;

import com.uep.photogallery.model.Photo;
import com.uep.photogallery.model.User;
import com.uep.photogallery.service.PhotoService;
import com.uep.photogallery.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(origins = "*")
public class PhotoController {
    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    private final PhotoService photoService;
    private final UserRepository userRepository;

    @Autowired
    public PhotoController(PhotoService photoService, UserRepository userRepository) {
        this.photoService = photoService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tags", required = false) String tags,
            @RequestParam("username") String username) {
        logger.info("Received photo upload request - title: {}, file size: {}", title, file.getSize());
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Photo photo = photoService.savePhoto(file, title, description, tags, user);
            logger.info("Successfully saved photo with id: {}", photo.getId());
            return ResponseEntity.ok(photo);
        } catch (Exception e) {
            logger.error("Error uploading photo", e);
            return ResponseEntity.badRequest().body("Error uploading photo: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Photo>> getAllPhotos() {
        logger.info("Fetching all photos");
        return ResponseEntity.ok(photoService.getAllPhotos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable Long id) {
        logger.info("Fetching photo with id: {}", id);
        return photoService.getPhoto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Photo>> searchPhotos(@RequestParam String query) {
        logger.info("Searching photos with query: {}", query);
        return ResponseEntity.ok(photoService.searchPhotos(query));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long id) {
        logger.info("Deleting photo with id: {}", id);
        try {
            photoService.deletePhoto(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting photo: " + e.getMessage());
        }
    }
} 