package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationTaskService {
    NotificationTaskRepository notificationTaskRepository;
@Autowired
    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;

    }

    public NotificationTask sendNotification(NotificationTask notificationTask) {
    Pattern pattern = Pattern.compile([0-9\.\:\s]{16})(\s)([\W+]+);
        Matcher matcher = pattern.matcher(String);
        if (matcher.matches()) {
            LocalDateTime dateTime = LocalDateTime.parse("01.01.2022 20:00", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            String notification = matcher.group("Сделать домашнюю работу");
        }
        return notificationTaskRepository.save(notificationTask);
    }
}
