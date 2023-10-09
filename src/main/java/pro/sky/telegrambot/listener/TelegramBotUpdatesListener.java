package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import liquibase.pro.packaged.L;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    NotificationTaskRepository notificationTaskRepository;
    @Autowired
    public TelegramBotUpdatesListener(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            if (update.message().text().equals("/start")) {
                SendMessage message = new SendMessage(update.message().chat().id(), "Hi");
                telegramBot.execute(message);
            }
                extraProcess(updates);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

//        public int extraProcess(List<Update> updates) {
//        updates.forEach(update -> {
//            logger.info("Processing update: {}", update);
//            Pattern dateAndTime = Pattern.compile("[0-9\.\:\s]{16})");
//            Pattern note = Pattern.compile("(\s)([\W+]+");
//            Matcher matcherTime = dateAndTime.matcher(update.message().text());
//            Matcher matcherNote = note.matcher(update.message().text());
//                // Process your updates here
//            if (matcherTime.matches() && matcherNote.matches()) {
//                String time = matcherTime.group(1);
//                LocalDateTime times = LocalDateTime.parse(time,
//                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
//                String notes = matcherNote.group(2);
//                NotificationTask notificationTask = new NotificationTask(update.message()
//                        .messageId(), notes, times);
//                notificationTaskRepository.save(notificationTask);
//            }
//        });
//        return UpdatesListener.CONFIRMED_UPDATES_ALL;
//    }
    public List<NotificationTask>notifications(LocalDateTime time) {
        List<NotificationTask> notificationTaskList = notificationTaskRepository.findAll();
        notificationTaskRepository.findAll().forEach(times->{
            if (time.equals(times)) {
                notificationTaskList.add(times);
            }
        });
        return notificationTaskList;
    }
    @Scheduled(cron = "0 0/1 * * * *")
//    @Scheduled(cron = "0 12 12 *1 MON")
    public void scheduleNotification() {
        List<NotificationTask>notificationTaskList=this.notifications(LocalDateTime.now()
                .truncatedTo(ChronoUnit.MINUTES));
        this.scheduleNotificationMassage(notificationTaskList);
    }
    public void scheduleNotificationMassage(List<NotificationTask>notificationTaskList){
        notificationTaskList.forEach(notificationTask -> {
            SendMessage sendMessage = new SendMessage(notificationTask.getId(),
                    notificationTask.getNotification());
            telegramBot.execute(sendMessage);
        });
    }


    public int extraProcess(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Pattern pattern = Pattern.compile("([\\d.:\\s]{16})\\s+(.+)");
            Matcher matcher = pattern.matcher(update.message().text());
            // Process your updates here
            if (matcher.matches()) {
                String time = matcher.group(1);
                LocalDateTime times = LocalDateTime.parse(time,
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                String notes = matcher.group(2);
                NotificationTask notificationTask = new NotificationTask(update
                        .message()
                        .chat().id(), notes, times);
                notificationTaskRepository.save(notificationTask);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}