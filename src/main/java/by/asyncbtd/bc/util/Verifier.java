package by.asyncbtd.bc.util;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Verifier {

    public boolean updateIsCommand(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return false;
        }
        return update.getMessage().getText().startsWith("/");
    }

    public boolean updateIsVideoNote(Update update) {
        return update.hasMessage() && update.getMessage().hasVideoNote();
    }
}
