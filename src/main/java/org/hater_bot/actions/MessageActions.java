package org.hater_bot.actions;

import org.hater_bot.texts.BotTexts;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Random;

public class MessageActions {

    //--------------------------//
    // OBJECTS OF OTHER CLASSES //
    //--------------------------//
    private final BotTexts botTexts = new BotTexts();
    private final Random random = new Random();



    //-------------------//
    // THIS CLASS VALUES //
    //-------------------//
    private String lastUsedText = "";
    private int messagesCount = 0;
    private int randomCount = 0;



    //--------------//
    // MAIN METHODS //
    //--------------//
    public SendMessage createMessage(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        return message;
    }


    public void sendMessage(long messageId, SendMessage message, String text){
        message.setText(text);
        message.setReplyToMessageId(Math.toIntExact(messageId));
    }

    public void sendMessageWithoutReply(SendMessage message, String text){

        System.out.println("im in sendMessageWithoutReply");

        message.setText(text);
    }


    public void fuckOffObject(long messageId, SendMessage message, int messageCounter){

        setRandomCount();

        if (isGoodMoment()){
            if (messageCounter==0){
                sendMessage(messageId, message, botTexts.getFirstActionText());
            } else if (messageCounter==1) {
                sendMessage(messageId, message, getNotUsedText(botTexts.secondActionTexts));
            }
            else{
                sendMessage(messageId, message, getNotUsedText(botTexts.threeOrMoreActionTexts));
            }
        }
    }


    public void sendIfBotMentioned(long messageId, SendMessage message){
        sendMessage(messageId, message, getNotUsedText(botTexts.botMentionedByObjectTexts));
    }


    public void sendDefendCreator(long messageId, SendMessage message){
        sendMessage(messageId, message, getNotUsedText(botTexts.defendCreatorTexts));
    }


    public void sendWhereIsObject(long messageId, SendMessage message){

        System.out.println("im in sendWhereIsObject");

        sendMessageWithoutReply(message, getNotUsedText(botTexts.objectDidNotSendMessagesForLong));
    }


    //---------------------------//
    // PRIVATE AUXILIARY METHODS //
    //---------------------------//
    private String getNotUsedText(String[] textsArray){
        String currentText = botTexts.getTextFromArray(textsArray);

        while (currentText.equals(lastUsedText)){
            currentText = botTexts.getTextFromArray(textsArray);
        }

        lastUsedText = currentText;

        return currentText;
    }


    private void setRandomCount(){
        if (randomCount==0){
            randomCount = random.nextInt(8);
        }
    }


    private boolean isGoodMoment(){

        System.out.println("randomCount = " + randomCount);
        System.out.println("messagesCount = " + messagesCount);

        if (randomCount==messagesCount){
            randomCount = 0;
            messagesCount = 0;

            return true;
        }
        else {
            messagesCount++;

            return  false;
        }
    }
}
