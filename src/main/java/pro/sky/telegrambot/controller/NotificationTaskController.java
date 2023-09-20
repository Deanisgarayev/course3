package pro.sky.telegrambot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.service.NotificationTaskService;

@RestController
@RequestMapping("/notification_task")
public class NotificationTaskController {
    NotificationTaskService notificationTaskService;
@Autowired
    public NotificationTaskController(NotificationTaskService notificationTaskService) {
        this.notificationTaskService = notificationTaskService;
    }
    @GetMapping
    public NotificationTask notification(NotificationTask notificationTask) {
        return notificationTaskService.sendNotification(notificationTask);
    }
}
