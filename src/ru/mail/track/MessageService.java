package ru.mail.track;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by lesah_000 on 10/13/2015.
 */
public class MessageService {

    MessageStorage msgStorage = new MessageStorage();
    PrintStream out = System.out;
    Scanner in = new Scanner(System.in);

    private void storeMessage(String message) {

    }

    private Message readMessage() {
        return new Message(in.nextLine() );
    }

    private boolean isItCommand(Message msg){
        return msg.text.startsWith("\\");
    }

    void processMessage(Message msg ) {
        if ( isItCommand(msg) ){
            String [] msg_words = msg.text.split(" ");
            switch (msg_words[0]){
                case "\\history":
                    showHistory( msg);
                    break;
                case "\\user":
                    setNickhame();
                    break;
                case "\\help":
                    showHelp();
                    break;
                case "\\find":
                    showHistoryWithRegex();
                    break;
                default:
                    break;
            }
        }else{
            msgStorage.setNewMessage( msg );
        }
    }

    void runMessageLoop() {
        while (true) {
            Message msg = readMessage();
            processMessage( msg );
        }
    }
    void showHistory( Message msg){
        int number = Integer.getInteger( msg.text.split(" ")[1] );
        for( Message message : msgStorage.getLastMessages(number) ){
            out.println(message.getText());
        }
    }
    void showHistoryWithRegex(){

    }
    void setNickhame(){

    }
    void showHelp(){
        out.println(
                "\\help - show this message" +
                        "\\user" +
                        "\\find" +
                        "\\history"
        );
    }
}
