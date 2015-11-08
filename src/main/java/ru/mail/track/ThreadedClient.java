package ru.mail.track;

import ru.mail.track.comands.CommandsData;
import ru.mail.track.comands.UserBaseCommand;
import ru.mail.track.comands.UserCommandsDecoder;
import ru.mail.track.connection.ConnectionHandler;
import ru.mail.track.connection.MessageListener;
import ru.mail.track.connection.SocketConnectionHandler;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.ControlMessage;
import ru.mail.track.storage.Message;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Клиентская часть
 */
public class ThreadedClient implements MessageListener {
    private final static Logger LOGGER = Logger.getLogger(ThreadedClient.class.getName());
    public static final int PORT = 19000;
    public static final String HOST = "localhost";
    PrintStream out = System.out;
    Scanner in = new Scanner(System.in);
    Thread socketHandler = null;

    ConnectionHandler handler;

    public ThreadedClient() {
        LOGGER.setLevel(Level.WARNING);
        try {
            Socket socket = new Socket(HOST, PORT);
            handler = new SocketConnectionHandler(socket, LOGGER);
            handler.addListener(this);
            socketHandler = new Thread(handler);
            socketHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Получено сообщение из handler, как обрабатывать
     */
    @Override
    public void onMessage(Serializable object) {
        try {
            if (object instanceof Message) {
                Message msg = (Message) object;
                System.out.println("---------------");
                System.out.println("got message:");
                System.out.println("senderId = " + msg.getSenderId() + "\nchatId = " + msg.getDialogId() + "\nText:\n" + msg.getText());
                System.out.println("---------------");
            } else if (object instanceof InfoMessage) {
                InfoMessage msg = (InfoMessage) object;
                System.out.println("---------------");
                System.out.println("got infoMessage:\n" + msg);
                System.out.println("---------------");
            } else if (object instanceof ControlMessage) {
                ControlMessage msg = (ControlMessage) object;
                if (msg.status == msg.LASTMESSAGE) {
                    //socketHandler.interrupt();
                    System.out.println("Ok");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readConsole() {
        try {
            out.print(":");
            String newMessage = in.nextLine();
            if (newMessage == null) {
                return null;
            }
            return newMessage;
        } catch (Exception e) {
            System.out.println("Can not obtain next message");
        }
        return null;
    }

    public boolean isItCommand(String input) {
        return (input.startsWith("\\"));
    }

    public void messageLoop() throws Exception {
        LOGGER.info("Client");
        while (true) {
            String input = readConsole();
            if (input == null) {
                break;
            }
            if (isItCommand(input)) {
                CommandsData commandsData = new CommandsData();
                commandsData.setText(input);
                UserBaseCommand command = UserCommandsDecoder.getCommand(commandsData);
                command.perform(handler, commandsData);
            }
        }
    }

}