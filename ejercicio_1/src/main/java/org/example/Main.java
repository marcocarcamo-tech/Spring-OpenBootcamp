package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        //Application context
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //Obteniendo el bean y accediendo a su método
        Saludo saludo = (Saludo) context.getBean("saludo");

        saludo.ImprimirSaludo();
    }
}