package com.uep.photogallery.repository;

import com.uep.photogallery.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Album> findByNameContainingIgnoreCase(String name);
} 