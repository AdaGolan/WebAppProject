package com.uep.photogallery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String filePath;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime uploadDate;

    @Column(nullable = false)
    private String fileType;

    private Long fileSize;

    @Column(nullable = false)
    private boolean isPublic = true;

    private Integer width;
    private Integer height;
    private String location;
    private String cameraModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "photo_tags",
        joinColumns = @JoinColumn(name = "photo_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "photo_categories",
        joinColumns = @JoinColumn(name = "photo_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();

    @Lob
    @Column(length = 1000000)
    private byte[] data;

    private String fileName;

    public Photo() {
    }

    public Photo(Long id, String title, String filePath, String description, LocalDateTime uploadDate, 
                String fileType, Long fileSize, Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.description = description;
        this.uploadDate = uploadDate;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.tags = tags != null ? tags : new HashSet<>();
    }

    @PrePersist
    protected void onCreate() {
        uploadDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags != null ? tags : new HashSet<>();
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", filePath='" + filePath + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }
} 