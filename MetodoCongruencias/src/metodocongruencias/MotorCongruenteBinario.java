/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodocongruencias;

import java.util.Random;

/**
 *
 * @author tharduz
 */
public class MotorCongruenteBinario extends MotorCongruente{
    public MotorCongruenteBinario(int h, Tipos tipo) {
        Random rnd = new Random();
        this.tipo = tipo;
        this.b = this.calcularB(h);
        this.m = (int)Math.pow(2, this.b);
        this.a = this.calcularA(m);
        this.n = Math.abs(rnd.nextInt()) % m;
        if(this.tipo == Tipos.MULTIPLICATIVO) {
            this.h = (long)Math.pow(2, this.b - 2);
            this.c = 0;
            this.n0=this.n;
        } else {
            this.h = (int)Math.pow(2, this.b);
            this.c = this.calcularC() % m;
            this.n0=this.n;
        }
    }
    // -Caucula el parámetro B
    private int calcularB(int h) {
        double aprox;
        int bresult;
        // -Bifurcación de casos.
        if(this.tipo == Tipos.MULTIPLICATIVO) {
            aprox = Math.log(h)/Math.log(2) + 2;
        } else {
            aprox = Math.log(h)/Math.log(2);
        }
        // Redondear y sumar 1
        if(Math.floor(aprox) != aprox)
            bresult = (int)aprox + 1;
        else 
            bresult = (int)aprox;
        if(bresult < 3)
            bresult = 3;
        return bresult;
    }   
    
    private long calcularA(long m){
        double aprox = Math.sqrt(m);
        long a1, a2, aux;
        int t;
        if(this.tipo == Tipos.MULTIPLICATIVO) {
            t = (int)aprox/8;
            a1 = 8 * t + 3;
            a2 = 8 * t - 3;
            if(Math.abs(a1 - aprox) < Math.abs(a2 - aprox)){
                return a1;
            } else {
                return a2;
            }
        } else {
            if(b % 2 == 0){
                aux = b;
            } else {
                aux = b -1;
            }
            return  (int)Math.pow(2, aux/2) + 1;
        }
    }
    
    private long calcularC() {
        Random random = new Random();
        
        int nrand = random.nextInt();
        
        return Math.abs(2 * nrand + 1);
    }
}
