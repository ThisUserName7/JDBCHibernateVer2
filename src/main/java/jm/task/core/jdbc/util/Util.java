package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "Zz16141996";
    private static SessionFactory sessionFactory;


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL,USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties set = new Properties();
                set.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
                set.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/test?useSSL=false");
                set.put(AvailableSettings.USER, USER);
                set.put(AvailableSettings.PASS, PASSWORD);
                set.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                set.put(AvailableSettings.SHOW_SQL, "true");
                set.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,"thread");
                set.put(AvailableSettings.HBM2DDL_AUTO,"");

                configuration.addAnnotatedClass(User.class);
                configuration.setProperties(set);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}