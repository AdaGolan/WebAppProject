package com.uep.photogallery.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shared_photos")
public class SharedPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shared_by_user_id", nullable = false)
    private User sharedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shared_with_user_id", nullable = false)
    private User sharedWith;

    @Column(nullable = false)
    private LocalDateTime sharedAt;

    @Column
    private String message;

    public SharedPhoto() {
    }

    public SharedPhoto(Photo photo, User sharedBy, User sharedWith, String message) {
        this.photo = photo;
        this.sharedBy = sharedBy;
        this.sharedWith = sharedWith;
        this.message = message;
        this.sharedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public User getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(User sharedBy) {
        this.sharedBy = sharedBy;
    }

    public User getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(User sharedWith) {
        this.sharedWith = sharedWith;
    }

    public LocalDateTime getSharedAt() {
        return sharedAt;
    }

    public void setSharedAt(LocalDateTime sharedAt) {
        this.sharedAt = sharedAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
} 