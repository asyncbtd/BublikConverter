package by.asyncbtd.bc;

import by.asyncbtd.bc.config.AppConfig;
import by.asyncbtd.bc.handlers.CommandHandler;
import by.asyncbtd.bc.handlers.VideoNoteHandler;
import by.asyncbtd.bc.util.Verifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final AppConfig appConfig;
    private final Verifier verifier;
    private final CommandHandler commandHandler;
    private final VideoNoteHandler videoNoteHandler;

    @Override
    public String getBotToken() {
        return appConfig.getToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        log.info("Get Update {}", update);
        if (verifier.updateIsCommand(update)) {
            commandHandler.handler(update);
        }
        if (verifier.updateIsVideoNote(update)) {
        }
    }
}
