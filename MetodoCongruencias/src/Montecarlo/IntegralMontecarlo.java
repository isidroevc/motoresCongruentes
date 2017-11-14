/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Montecarlo;

import metodocongruencias.MotorCongruente;
import metodocongruencias.MotorCongruenteBinario;

/**
 *
 * @author tharduz
 */
public class IntegralMontecarlo {

    private double limInf;
    private double limSup;
    private boolean evitarCero = false;
    Funcion f;
    Funcion entrada;
    
    MotorCongruenteBinario motor;
    
    private int n;
    private double result;
    
    
    public IntegralMontecarlo(double limInf, double limSup, int n, Funcion entrada) {
        this.n = n;
        this.limInf = limInf;
        this.limSup = limSup;
        
        
        if (limInf == 0 && limSup == 1) {
            this.f = entrada;
        }  else if(limInf == 0 && limSup == Double.POSITIVE_INFINITY) {
            this.f = new Funcion() {
                @Override
                public double eval(double x) {
                    return entrada.eval(1/x - 1)/(x*x);
                }
            };
        } else if(limInf == Double.NEGATIVE_INFINITY && limSup == Double.POSITIVE_INFINITY) {
            evitarCero = true;
            this.f = new Funcion() {
                @Override
                public double eval(double x) {
                    //System.out.println(Math.log( x / (1- x)));
                    return entrada.eval(Math.log( x / (1- x))) * (1/(x*(1-x)));
                }
            };
        } else { // Con esa condición ya no es de 0 a 1
            // Hace substitiución para el caso de entre a y b
            this.f = new Funcion() {
                @Override
                public double eval(double x) {
                    double a = limInf;
                    double b = limSup;
                    return entrada.eval(x * (b - a) + a ) * (b - a);
                }
            };
        }
    }
    
    public double integrar() {
        double suma = 0;
        double aux;
        double factor = 0;
        double r;
        /*if(limSup != 1)
           factor = limSup - limInf;*/
        motor = new MotorCongruenteBinario(n, MotorCongruente.Tipos.MIXTO);
        for(int i = 0; i < n; i++) {
            r = motor.siguienteNormal();
            //System.out.println(r);
            if(r == 0 && evitarCero)
                continue;
            aux = this.f.eval(r);
            //System.out.println(aux);
            if(!Double.isNaN(aux)) {
                suma += aux;
            }
        }
        return result =  suma /(double) n;
    }
    
    public void agregarFuncion(Funcion entrada) {
        this.entrada = f;
    }
}
