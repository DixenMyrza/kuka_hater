package org.hater_bot.texts;

import java.util.ArrayList;
import java.util.Random;

public class BotTexts {

    private Random random = new Random();


    private String firstActionText = "Пашол наху";


    public String[] secondActionTexts = {"Пашол наху дим", "Пашол наху дим заебал", "Одного мало? Пашол наху"};


    public String[] threeOrMoreActionTexts = {"Эй зб, пашол наху", "Жапшы болды зб аузыңды", "Да ты заебал",
            "Ауызы жабылмайды екен еба", "Уже ауыз шаршап кетті нахулай беріп", "Койчы братан, койчы, пашол наху"};


    public String[] defendCreatorTexts = {"Не керек саған Диксидан?", "Тебе по ебалу дать?", "@DixenMyrza, его уебать, создатель?",
            "Сасық аузыңа алма бұл есімді", "Бля пашол наху тиіспей оған"};


    public String[] botMentionedByObjectTexts = {"Че надо, гнида?", "Кімсің мені тегать ететіндей наху?",
            "Не дисң, не дисң заебал?"};


    public String[] objectDidNotSendMessagesForLong = {"Үндемей қалыпты ғой, әшейінде ауызы жабылмаушы еді @KReX46",
            "Тишину словил, черт @KReX46", "Неужели получилось его заткнуть? @KReX46"};


    public String[] creatorNames = {"дикс", "диас", "дихш", "dixenmyrza", "diks", "dias", "dixi"};


    public String getFirstActionText(){
        return  firstActionText;
    }


    public String getTextFromArray(String[] array){
        int randomIndex = random.nextInt(array.length);

        return array[randomIndex];
    }
}
