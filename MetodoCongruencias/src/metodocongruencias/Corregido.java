/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodocongruencias;

import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author tharduz
 */
public class Corregido {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion, opcion2, cantidad;
        MotorCongruente.Tipos tipo;
        FileOutputStream stream = null;
        String mensaje = "", aux = "", txtMotor = "", txtTipo;
        try {
            stream = new FileOutputStream(System.currentTimeMillis() + "Corrida.csv");
        } catch (Exception ex) {
            escribir("Error Abriendo archivo");
            System.exit(0);
        }
        MotorCongruente motor;
        escribir("Con que motor se van a generar?");
        escribir("1.-Binario");
        escribir("2.-Decimal");
        escribir("Su eleccion: ");
        opcion = Integer.parseInt(leer());
        escribir("Escriba cuantos numeros se van a generar:");
        cantidad = Integer.parseInt(leer());
        escribir("Elija el método:");
        escribir("1.-Multiplicativo");
        escribir("2.-Mixto");
        opcion2 = Integer.parseInt(leer());
        if (opcion2 == 1) {
            tipo = MotorCongruente.Tipos.MULTIPLICATIVO;
            txtTipo = "Multiplicativo";
        } else {
            tipo = MotorCongruente.Tipos.MIXTO;
            txtTipo = "Mixto";
        }
        if (opcion == 1) {
            motor = new MotorCongruenteBinario(cantidad, tipo);
            txtMotor = "Binatio";
        } else {
            motor = new MotorCongruenteDecimal(cantidad, tipo);
            txtMotor = "Decimal";
        }
        escribir("Datos del motor.");
        escribir("Motor: " + txtMotor + " Método: " + txtTipo);
        escribir(motor.toString());
        escribir("Corrida.");
        try {
            aux = "Datos del motor: \n";
            aux += "Motor: " + txtMotor + " Metodo: " + txtTipo;
            aux += "\n" + motor.toString() + "\n";
            aux += "\nCorrida.\n h,n \n";
            stream.write(aux.getBytes());
            for (int i = 0; i < cantidad + 1; i++) {
                aux = i + ", " + motor.siguienteNormal()+ "\n";
                escribir(aux);
                stream.write(aux.getBytes());
            }
            stream.close();
        } catch (Exception ex) {
            escribir("Error escribiendo en archivo");
            System.exit(0);
        }

    }

    public static void escribir(String s) {
        System.out.println(s);
    }

    public static String leer() {
        System.out.print(">>");
        return scan.nextLine();
    }
}
