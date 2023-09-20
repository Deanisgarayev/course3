package pro.sky.telegrambot.controller;

import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;

import java.util.List;

@RestController
@RequestMapping("/telegram_bot")
public class TelegramBotController {
    TelegramBotUpdatesListener telegramBotUpdatesListener;
    @Autowired

    public TelegramBotController(TelegramBotUpdatesListener telegramBotUpdatesListener) {
        this.telegramBotUpdatesListener = telegramBotUpdatesListener;
    }
    @GetMapping
    public int process(List<Update> updates) {
        return telegramBotUpdatesListener.process(updates);
    }
}
