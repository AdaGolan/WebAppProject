package com.uep.photogallery.model;

import javax.persistence.*;

@Entity
@Table(name = "photo_stats")
public class PhotoStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

    private int viewCount = 0;
    private int likeCount = 0;

    public PhotoStats() {
    }

    public PhotoStats(Photo photo) {
        this.photo = photo;
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

    public int getViewCount() {
        return viewCount;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }
} 