package ru.mail.track;


public class Main {

    public static void main(String[] args) {

        UserStoreStatic userStore = new UserStoreStatic();
        AuthorizationService authServ = new AuthorizationService(userStore);

        authServ.startAuthorization();


    }
}
