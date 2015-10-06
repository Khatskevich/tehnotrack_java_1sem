package ru.mail.track;


import java.io.PrintStream;
import java.util.Scanner;

public class AuthorizationService {

    PrintStream out = System.out;
    Scanner in = new Scanner(System.in);

    private UserStore userStore;

    public AuthorizationService(UserStore userStore) {
        this.userStore = userStore;
    }

    void startAuthorization() {
        User user = null;
        if (isLogin()) {
            out.println("You are already loggined in!"); // + user);
        }else {
            out.println("Do you want to:");
            out.println("1 : Log in ");
            out.println("2 : register ?");
            String answer = in.nextLine();
            switch (answer){
                case "1":
                    out.println("Logging in...");
                    if ((user = login()) != null) {
                        out.println("Success!");
                    } else {
                        out.println("Wrong name or password!");
                    }
                    break;
                case "2":
                    out.println("Registration...");
                    if ((user = creatUser()) != null) {
                        out.println("New user created!");
                    } else {
                        out.println("Error occured!");
                    }
                    break;
                default:
                    out.println("Wrong choise!");
                    break;
            }
        }
    }

    User login() {
        out.println("Enter your name please");
        String name = in.nextLine();
        out.println("Enter your password please");
        String password = in.nextLine();
        return userStore.getUser(name, password);
    }

    User creatUser() {
        out.println("Enter your name please");
        String name = in.nextLine();
        out.println("Enter your password please");
        String password = in.nextLine();
        User user = new User(name, password);
        userStore.addUser(user);
        return user;
    }

    boolean isLogin() {
        return false;
    }
}
