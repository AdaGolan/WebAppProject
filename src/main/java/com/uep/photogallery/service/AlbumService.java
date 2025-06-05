package com.uep.photogallery.service;

import com.uep.photogallery.model.Album;
import com.uep.photogallery.model.Photo;
import com.uep.photogallery.model.User;
import com.uep.photogallery.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Transactional
    public Album createAlbum(String name, String description, User user) {
        Album album = new Album(name, description, user);
        return albumRepository.save(album);
    }

    public List<Album> getUserAlbums(Long userId) {
        return albumRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Album> searchAlbums(String name) {
        return albumRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public Album addPhotoToAlbum(Album album, Photo photo) {
        album.addPhoto(photo);
        return albumRepository.save(album);
    }

    @Transactional
    public Album removePhotoFromAlbum(Album album, Photo photo) {
        album.removePhoto(photo);
        return albumRepository.save(album);
    }

    @Transactional
    public void deleteAlbum(Long albumId) {
        albumRepository.deleteById(albumId);
    }
} 