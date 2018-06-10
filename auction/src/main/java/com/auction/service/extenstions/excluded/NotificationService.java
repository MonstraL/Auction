package com.auction.service.extenstions.excluded;

import com.auction.dao.extensions.excluded.NotificationDAO;
import com.auction.entity.extensions.Notification;

import java.util.List;

public class NotificationService {

    private NotificationDAO notificationDAO;

    public boolean create(String title, String content){
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setContent(content);
        try {
            notificationDAO.create(notification);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean delete(int id){
        try {
            notificationDAO.delete(notificationDAO.findById(id));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Notification> getAll(){
        return notificationDAO.findAll();
    }


}
