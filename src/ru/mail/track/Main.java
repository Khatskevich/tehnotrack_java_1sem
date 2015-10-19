package ru.mail.track;

//FIXME(arhangeldim): unused imports
import ru.mail.track.comands.*;
import ru.mail.track.storage.UserStoreStatic;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        MessageService messageService = new MessageService( );
        messageService.runMessageLoop();
    }
}
