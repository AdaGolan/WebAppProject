package com.uep.photogallery.service;

import com.uep.photogallery.model.Photo;
import com.uep.photogallery.model.SharedPhoto;
import com.uep.photogallery.model.User;
import com.uep.photogallery.repository.SharedPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SharedPhotoService {
    private final SharedPhotoRepository sharedPhotoRepository;

    @Autowired
    public SharedPhotoService(SharedPhotoRepository sharedPhotoRepository) {
        this.sharedPhotoRepository = sharedPhotoRepository;
    }

    @Transactional
    public SharedPhoto sharePhoto(Photo photo, User sharedBy, User sharedWith, String message) {
        SharedPhoto sharedPhoto = new SharedPhoto(photo, sharedBy, sharedWith, message);
        return sharedPhotoRepository.save(sharedPhoto);
    }

    public List<SharedPhoto> getPhotosSharedWithUser(Long userId) {
        return sharedPhotoRepository.findBySharedWithIdOrderBySharedAtDesc(userId);
    }

    public List<SharedPhoto> getPhotosSharedByUser(Long userId) {
        return sharedPhotoRepository.findBySharedByIdOrderBySharedAtDesc(userId);
    }

    public List<SharedPhoto> getPhotoSharingHistory(Long photoId) {
        return sharedPhotoRepository.findByPhotoIdOrderBySharedAtDesc(photoId);
    }

    @Transactional
    public void removeShare(Long shareId) {
        sharedPhotoRepository.deleteById(shareId);
    }
} 