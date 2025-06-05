package com.uep.photogallery.service;

import com.uep.photogallery.model.Photo;
import com.uep.photogallery.model.Tag;
import com.uep.photogallery.model.User;
import com.uep.photogallery.repository.PhotoRepository;
import com.uep.photogallery.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final TagRepository tagRepository;
    private final Path uploadDir = Paths.get("uploads");

    @Autowired
    public PhotoService(PhotoRepository photoRepository, TagRepository tagRepository) {
        this.photoRepository = photoRepository;
        this.tagRepository = tagRepository;
        createUploadDirectory();
    }

    private void createUploadDirectory() {
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    @Transactional
    public Photo savePhoto(MultipartFile file, String title, String description, String tags, User user) throws IOException {
        Photo photo = new Photo();
        photo.setTitle(title);
        photo.setDescription(description);
        photo.setFileName(file.getOriginalFilename());
        photo.setFileType(file.getContentType());
        photo.setFileSize(file.getSize());
        photo.setData(file.getBytes());
        photo.setUploadDate(LocalDateTime.now());
        photo.setUser(user);
        
        // Set file path
        String filePath = uploadDir.resolve(file.getOriginalFilename()).toString();
        photo.setFilePath(filePath);

        if (tags != null && !tags.isEmpty()) {
            Set<Tag> tagSet = Arrays.stream(tags.split(","))
                    .map(String::trim)
                    .map(tagName -> tagRepository.findByName(tagName)
                            .orElseGet(() -> {
                                Tag newTag = new Tag();
                                newTag.setName(tagName);
                                return tagRepository.save(newTag);
                            }))
                    .collect(Collectors.toSet());
            photo.setTags(tagSet);
        }

        return photoRepository.save(photo);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Optional<Photo> getPhoto(Long id) {
        return photoRepository.findById(id);
    }

    public List<Photo> searchPhotos(String query) {
        List<Photo> byTitle = photoRepository.findByTitleContainingIgnoreCase(query);
        List<Photo> byDescription = photoRepository.findByDescriptionContainingIgnoreCase(query);
        
        return byTitle.stream()
            .filter(photo -> !byDescription.contains(photo))
            .collect(Collectors.toList());
    }

    @Transactional
    public void deletePhoto(Long id) {
        Photo photo = getPhotoById(id);
        try {
            Files.deleteIfExists(Paths.get(photo.getFilePath()));
            photoRepository.delete(photo);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file", e);
        }
    }

    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Photo not found with id: " + id));
    }
} 