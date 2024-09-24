/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej1;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author juand
 */
public class Cronometro {
 private static int horas = 0;
    private static int minutos = 0;
    private static int segundos = 0;
    //timer se usaria como objeto si se declarara en un archivo aparte, sin enmbargo ya que esta en el mismo archovo no es necesario usar un objeto 
    private static Timer timer = new Timer();
    private static boolean alarmaActiva = false;
    private static boolean runn = true;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Configura el tiempo de la alarma en minutos:\n(para detener el cronometro escriba 1 en consola)\n ");
        int tiempoAlarma = scanner.nextInt();
        

        iniciarCronometro(tiempoAlarma);
        while (runn) {
            String input = scanner.next();
            if (input.equals("1")) {
                pararCronometro();
            }
        }
        scanner.close();
    }
    private static void iniciarCronometro(int tiempoAlarma) {
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                //sumatoria para cada seguno, minuto y hora cuando se resetea en 60 
                segundos++;
                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                }
                if (minutos == 60) {
                    minutos = 0;
                    horas++;
                }
                System.out.print("Hora actual:  "+horas+":"+minutos+":"+segundos+"\n");
                
                if (!alarmaActiva && minutos >= tiempoAlarma) {
                    activarAlarma();
                }
            }
        };
        // un contador que lleva cada segundo pasado, el 1000 sirve para saber que es un segundo, ya que el timer no esta en segundos
        timer.scheduleAtFixedRate(tarea, 0, 1000);
    } private static void activarAlarma() {
        alarmaActiva = true;
        System.out.println("Alarma ");

        TimerTask alarmaTask = new TimerTask() {
            //como se declararon variables estaticas en la clase principal (lab32) se usa el Override para sobreescribirlo, o sea darle un valor diferente 
            @Override
            public void run() {
                System.out.println("ALERTA DE ALARMA  ");
            }
        };
        // Iniciar la alarma cada 10 segundos precisamente con una de las librerias de TimerTask
        timer.scheduleAtFixedRate(alarmaTask, 0, 10000);
    }
         private static void pararCronometro() {
        timer.cancel(); // Detener el timer 
        System.out.print("se a detenido el tiempo ");
        
    }
}