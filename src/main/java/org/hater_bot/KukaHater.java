package org.hater_bot;

import org.hater_bot.actions.MessageActions;
import org.hater_bot.config.PropertiesConfig;
import org.hater_bot.texts.BotTexts;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KukaHater extends TelegramLongPollingBot {


    //--------------------------//
    // OBJECTS OF OTHER CLASSES //
    //--------------------------//
    private PropertiesConfig propertiesConfig = new PropertiesConfig();
    private MessageActions messageActions = new MessageActions();
    private BotTexts botTexts = new BotTexts();


    //-------------------//
    // THIS CLASS VALUES //
    //-------------------//
    private int messageCountInShortTime = 0;
    private int lastFormattedDate = 0, currentFormattedDate, othersMessagesLastDate;
    private int lastProcessedUpdateId = 0;



    //--------------------------------//
    // MAIN METHOD THAT HANDLE UPDATE //
    //--------------------------------//
    @Override
    public void onUpdateReceived(Update update) {

        if (lastFormattedDate == 0){
            setLastFormattedDate();
        }

        int updateId = update.getUpdateId();

        if (updateId > lastProcessedUpdateId) {

            lastProcessedUpdateId = updateId;

            if (update.hasMessage()) {

                Message receivedMessage = update.getMessage();

                long chatId = receivedMessage.getChatId();
                long userId = receivedMessage.getFrom().getId();
                long messageId = receivedMessage.getMessageId();

                String userName = receivedMessage.getFrom().getUserName();

                SendMessage message = messageActions.createMessage(chatId);

                if (userId == propertiesConfig.getObjectId()) {

                    setCurrentFormattedDate();
                    countsOfMessages();

                    String messageText = update.getMessage().getText();
                    boolean isBotMentioned = messageText.contains(getBotUsername());

                    if (isBotMentioned){
                        messageActions.sendIfBotMentioned(messageId, message);
                    }

                    else if(isObjectMentionCreator(messageText)){
                        messageActions.sendDefendCreator(messageId, message);
                    }

                    else{
                        messageActions.fuckOffObject(messageId, message, messageCountInShortTime);
                    }

                    lastFormattedDate = currentFormattedDate;
                }

                else {

                    setOthersMessagesLastDate();

                    if (isNoMessagesForLong()){

                        System.out.println("Im in main IF");

                        messageActions.sendWhereIsObject(messageId, message);

                        lastFormattedDate = othersMessagesLastDate;
                    }
                }

                executeMessage(message);

            }
        }
    }



    //-------------------//
    // AUXILIARY METHODS //
    //-------------------//
    private void executeMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private int getFormattedDate(){
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddHHmm");

        String formattedDate = dateFormat.format(currentDate);

        return Integer.parseInt(formattedDate);
    }


    private void setLastFormattedDate(){

        currentFormattedDate = getFormattedDate();
    }


    private void setCurrentFormattedDate(){

        currentFormattedDate = getFormattedDate();
    }


    private void setOthersMessagesLastDate(){

        othersMessagesLastDate = getFormattedDate();
    }


    private boolean hasMessageReceivedRecently(){
        int diff = currentFormattedDate - lastFormattedDate;

        return diff<5;
    }


    private boolean isNoMessagesForLong(){
        int diff = othersMessagesLastDate - lastFormattedDate;

        System.out.println("Difference: " + diff);

        return diff>10000;
    }


    private void countsOfMessages(){
        if (hasMessageReceivedRecently())
            messageCountInShortTime++;
        else
            messageCountInShortTime=0;
    }


    private boolean isObjectMentionCreator(String messageText){
        boolean mention = false;

        for (String name: botTexts.creatorNames){
            if (messageText.toLowerCase().contains(name)){
                mention = true;

                break;
            }
        }

        return mention;
    }



    //---------//
    // GETTERS //
    //---------//
    @Override
    public String getBotUsername() {
        return propertiesConfig.getUserName();
    }

    @Override
    public String getBotToken() {
        return propertiesConfig.getToken();
    }
}