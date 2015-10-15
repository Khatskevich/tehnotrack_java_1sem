package ru.mail.track;


public class Main {

    public static void main(String[] args) {

        UserStore userStore = new UserStore();
        AuthorizationService authServ = new AuthorizationService(userStore);

        authServ.startAuthorization();


    }
}
