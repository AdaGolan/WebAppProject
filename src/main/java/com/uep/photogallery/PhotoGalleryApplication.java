package com.uep.photogallery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.uep.photogallery.model")
@EnableJpaRepositories("com.uep.photogallery.repository")
@ComponentScan(basePackages = "com.uep.photogallery")
public class PhotoGalleryApplication {
    private static final Logger logger = LoggerFactory.getLogger(PhotoGalleryApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Photo Gallery Application...");
        SpringApplication.run(PhotoGalleryApplication.class, args);
        logger.info("Photo Gallery Application started successfully!");
    }
} 