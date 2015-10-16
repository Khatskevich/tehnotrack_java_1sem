package ru.mail.track;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by lesah_000 on 10/13/2015.
 */
public class MessageService {

    MessageStorage msgStorage = new MessageStorageStatic();
    PrintStream out = System.out;
    Scanner in = new Scanner(System.in);
    User user;
    private MessageService(){};
    MessageService( User user){
        this.user = user;
    }

    private void sendMessage(Message msg) {
    }
    private void saveMessage(Message msg){
        msgStorage.setNewMessage( msg );
    }

    private Message readMessage() {
        if ( user.getNickName() != null ){
            out.print(user.getNickName());
        }
        out.print(":");
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
                    setNickname();
                    break;
                case "\\help":
                    showHelp();
                    break;
                case "\\find":
                    showHistoryWithRegex( msg );
                    break;
                default:
                    break;
            }
            this.saveMessage(msg);
        }else{
            this.sendMessage(msg);
            this.saveMessage(msg);
        }
    }

    void runMessageLoop() {
        while (true) {
            Message msg = readMessage();
            processMessage( msg );
        }
    }
    void showHistory( Message msg){
        int number;
        String [] arguments = msg.text.split(" ");
        if ( arguments.length > 1){
            number = Integer.getInteger(arguments[1]);
        }else {
          number = Integer.MAX_VALUE;
        }
        for( Message message : msgStorage.getLastMessagesWithRegex(number, null) ){
            out.println(message.getText());
        }
    }
    void showHistoryWithRegex( Message msg ){
        int number = Integer.MAX_VALUE;
        String regex = msg.getText().replaceFirst("\\find ", "");
        out.println("Looking for" + regex);
        for( Message message : msgStorage.getLastMessagesWithRegex(number, regex) ){
            out.println(message.getText());
        }
    }
    void setNickname(){
        out.println("Enter your nick please ( without spaces):");
        String nick = in.next();
        if ( nick.split(" ").length == 1){
            user.setNickName(nick);
            UserStore msgStorage = new UserStoreStatic();
            try {
                msgStorage.editUser(user);
            } catch (Exception e){
                out.println("Internal error: "+ e.getMessage() );
            }
        }else {
            out.println("Wrong nick");
        }
    }
    void showHelp(){
        out.println(
                "\\help - show this message\n" +
                        "\\user - change user nick name\n" +
                        "\\find - find messages in history using regex\n" +
                        "\\history - return last messages [count]\n"
        );
    }
}
