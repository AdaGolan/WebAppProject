package com.uep.photogallery.repository;

import com.uep.photogallery.model.SharedPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedPhotoRepository extends JpaRepository<SharedPhoto, Long> {
    List<SharedPhoto> findBySharedWithIdOrderBySharedAtDesc(Long userId);
    List<SharedPhoto> findBySharedByIdOrderBySharedAtDesc(Long userId);
    List<SharedPhoto> findByPhotoIdOrderBySharedAtDesc(Long photoId);
} 