/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodocongruencias;

import java.util.ArrayList;

/**
 *
 * @author tharduz
 */
public class MetodoCongruencias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*MotorCongruenteBinario bin = new MotorCongruenteBinario(4, MotorCongruente.Tipos.MIXTO);
        System.out.println(bin.toString());
        for(int i  = 0; i < 100; i++) {
            System.out.println(bin.siguienteNumero());
        }*/
        long aux;
        long[] list = new long[1005];
        MotorCongruente dec = new MotorCongruenteBinario(128, MotorCongruente.Tipos.MULTIPLICATIVO);
        System.out.println(dec.toString());
        for(int i  = 0; i < 1005; i++) {
            list[i] = dec.siguienteNumero();
            System.out.println((i + 1) + ".-" + list[i]);
        }
        for(int i = 0, c = list.length; i < c; i++ ) {
            for(int j = 0; j < c -1; j++){
                if(list[j] > list[j + 1]){
                    aux = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = aux;
                } 
            }
        }
        /*for(int i = 0; i < 1005; i++) {
            System.out.println((i + 1) + ".-" + list[i]);
        }*/
    }
    
}
