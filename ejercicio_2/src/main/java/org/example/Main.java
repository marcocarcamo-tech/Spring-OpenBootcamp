package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // Obteniendo los beans escaneados
        NotificationService notification = (NotificationService) context.getBean("notificationService");
        UserService user = (UserService) context.getBean("userService");


        //Accediendo a un bean dentro de otro bean para acceder a sus métodos
        user.notification.imprimirSaludo(); //Imprimer el saludo en consola.
    }
}