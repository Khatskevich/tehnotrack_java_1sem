package ru.mail.track.protocol;

import java.io.*;

public class ObjectBytesConverter {
    public static byte[] toBytes(Serializable object) throws IOException {
        ByteArrayOutputStream serializatorBAIS = new ByteArrayOutputStream();
        (new ObjectOutputStream(serializatorBAIS)).writeObject(object);
        byte[] bytesArray = serializatorBAIS.toByteArray();
        return bytesArray;
    }

    public static Serializable toObject(byte[] bytes) throws ClassNotFoundException, IOException {
        ByteArrayInputStream diserializatorBAIS = new ByteArrayInputStream(bytes);
        ObjectInput disearilizatorOI = new ObjectInputStream(diserializatorBAIS);
        return (Serializable) disearilizatorOI.readObject();
    }
}
