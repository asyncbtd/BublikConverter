package by.asyncbtd.bc.handlers;

import by.asyncbtd.bc.config.AppConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class CommandHandler {

    private final String defaultText = """
            Я тебе не розумію. Напиши комманду /help чтобы узнать что я умею.
            """;

    private final TelegramClient telegramClient;

    public CommandHandler(AppConfig appConfig) {
        this.telegramClient = new OkHttpTelegramClient(appConfig.getToken());
    }

    public void handler(Update update) {
        String command = update.getMessage().getText();
        switch (command) {
            case "help" -> helpCommand(update);
            default -> defaultCommand(update);
        }
    }

    private void helpCommand(Update update) {

    }

    private void defaultCommand(Update update) {
        try {
            telegramClient.execute(SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text(defaultText)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
