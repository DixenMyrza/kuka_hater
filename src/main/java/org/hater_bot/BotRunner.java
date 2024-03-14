package org.hater_bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotRunner {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            KukaHater bot = new KukaHater();
            botsApi.registerBot(bot);
            System.out.println("Bot started successfully!");

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}