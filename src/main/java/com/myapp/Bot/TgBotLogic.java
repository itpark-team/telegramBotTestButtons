package com.myapp.Bot;

import com.myapp.Db.PhonesManager;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TgBotLogic {
    private PhonesManager phonesManager;

    public TgBotLogic() throws Exception {
        phonesManager = new PhonesManager();
        phonesManager.loadPhonesFromTxtFile("phones.txt");
    }

    private void sendPlainTextToUser(TgBotManager tgBotManager, String chatId, String responseText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(responseText);

        try {
            tgBotManager.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendStartMessage(TgBotManager tgBotManager, String chatId) {
        String responseText = "программа для показа телефонов, напишите /menu";

        sendPlainTextToUser(tgBotManager, chatId, responseText);
    }

    private void sendMenuMessage(TgBotManager tgBotManager, String chatId) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("1. Вывести все телефоны");
        keyboard.add(row);

        row = new KeyboardRow();
        row.add("0. Убить бота");
        keyboard.add(row);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("выберите пункт меню");
        message.setReplyMarkup(replyKeyboardMarkup);//добавленние кнопок к сообщению

        try {
            tgBotManager.execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendDefaultMessage(TgBotManager tgBotManager, String chatId) {
        String responseText = "команда не распознана";

        sendPlainTextToUser(tgBotManager, chatId, responseText);
    }

    private void sendListMessage(TgBotManager tgBotManager, String chatId) {
        String responseText = phonesManager.getListPhonesAsString();
        sendPlainTextToUser(tgBotManager, chatId, responseText);
    }

    public void routeMessage(TgBotManager tgBotManager, String chatId, String recieveText) {
        switch (recieveText) {
            case "/start":
                sendStartMessage(tgBotManager, chatId);
                break;

            case "/menu":
                sendMenuMessage(tgBotManager, chatId);
                break;

            case "/list":
                sendListMessage(tgBotManager, chatId);
                break;

            default:
                sendDefaultMessage(tgBotManager, chatId);
                break;
        }
    }

}
