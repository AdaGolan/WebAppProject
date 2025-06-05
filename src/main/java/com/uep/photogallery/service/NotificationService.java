package com.uep.photogallery.service;

import com.uep.photogallery.model.Notification;
import com.uep.photogallery.model.User;
import com.uep.photogallery.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Notification createNotification(User user, String message, String type, Long referenceId) {
        Notification notification = new Notification(user, message, type, referenceId);
        return notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndReadFalseOrderByCreatedAtDesc(userId);
    }

    public long getUnreadNotificationCount(Long userId) {
        return notificationRepository.countByUserIdAndReadFalse(userId);
    }

    @Transactional
    public Notification markAsRead(Long id) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notification.setRead(true);
                    return notificationRepository.save(notification);
                })
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
    }

    @Transactional
    public void markAllAsRead(Long userId) {
        List<Notification> unreadNotifications = getUnreadNotifications(userId);
        unreadNotifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(unreadNotifications);
    }

    @Transactional
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
} 