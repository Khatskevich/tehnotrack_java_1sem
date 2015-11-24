package ru.mail.track.protocol;

import org.junit.Before;
import org.junit.Test;
import ru.mail.track.storage.Message;
import ru.mail.track.protocol.ObjectBytesConverter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Junit4ProtocolTest {


    @Before
    public void setUpData() {

    }
    @Test
    public void testBasicClassesToBytes() throws IOException, ClassNotFoundException {
        Message myMessage;
        byte[] messageBytes;
        Message tmpMessage;
        myMessage = new Message("Hello");
        myMessage.setSenderId(6l);
        myMessage.setDialogId(4l);
        messageBytes=ObjectBytesConverter.toBytes(myMessage);
        tmpMessage = (Message) ObjectBytesConverter.toObject(messageBytes);
        assertEquals(tmpMessage.equals(myMessage), true);
        myMessage = new Message("Valerasdasdas09urj023jcm   \\\\\\\\\\\\\\\\\\dasdsadas\nvfd");
        myMessage.setSenderId(Long.MAX_VALUE);
        myMessage.setDialogId(Long.MIN_VALUE);
        messageBytes=ObjectBytesConverter.toBytes(myMessage);
        tmpMessage = (Message) ObjectBytesConverter.toObject(messageBytes);
        assertEquals(tmpMessage.equals(myMessage), true);
        myMessage = new Message("Valerasa3894444441029393dasdas09urj023jcm   \\\\\\\\\\\\\\\\\\dasdsadas\nvfd");
        myMessage.setSenderId(Long.MIN_VALUE);
        myMessage.setDialogId(Long.MAX_VALUE);
        messageBytes=ObjectBytesConverter.toBytes(myMessage);
        tmpMessage = (Message) ObjectBytesConverter.toObject(messageBytes);
        assertEquals(tmpMessage.equals(myMessage), true);
    }
}
