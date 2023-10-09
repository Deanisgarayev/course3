package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "notification_tasks")
public class NotificationTask {
    public NotificationTask() {
    }

    @Id
    private long id;
    private String notification;
    private LocalDateTime dateAndTime;
    public NotificationTask(long id,  String notification, LocalDateTime dateAndTime) {
        this.id = id;
        this.notification = notification;
        this.dateAndTime = dateAndTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public LocalDateTime getDataAndTime() {
        return dateAndTime;
    }

    public void setDataAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && Objects.equals(notification, that.notification) && Objects.equals(dateAndTime, that.dateAndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notification, dateAndTime);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", notification='" + notification + '\'' +
                ", dateAndTime=" + dateAndTime +
                '}';
    }
}
