package com.myapp;

import com.myapp.Bot.TgBotManager;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args)  {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TgBotManager());

            System.out.println("bot started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
