package org.example;

import org.springframework.stereotype.Component;
@Component
public class NotificationService {

    public NotificationService(){};

    public void imprimirSaludo (){
        System.out.println("Saludos desde la clase notification service");
    }
}
