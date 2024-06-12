package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Иван","Зубков", (byte)27);
        userService.saveUser("Александр","Пушкин", (byte)37);
        userService.saveUser("Лев","Толстой", (byte)82);
        userService.saveUser("Герман","Севостьянов", (byte)27);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
