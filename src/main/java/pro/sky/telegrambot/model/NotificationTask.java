package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {
    public NotificationTask() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String notification;
    private LocalDateTime dataAndTime;
    public NotificationTask(long id,  String notification, LocalDateTime dataAndTime) {
        this.id = id;
        this.notification = notification;
        this.dataAndTime = dataAndTime;
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
        return dataAndTime;
    }

    public void setDataAndTime(LocalDateTime dataAndTime) {
        this.dataAndTime = dataAndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && Objects.equals(notification, that.notification) && Objects.equals(dataAndTime, that.dataAndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notification, dataAndTime);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", notification='" + notification + '\'' +
                ", dataAndTime=" + dataAndTime +
                '}';
    }
}
