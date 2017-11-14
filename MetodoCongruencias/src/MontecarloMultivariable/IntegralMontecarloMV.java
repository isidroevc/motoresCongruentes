/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MontecarloMultivariable;

import Montecarlo.*;
import metodocongruencias.MotorCongruente;
import metodocongruencias.MotorCongruenteBinario;

/**
 *
 * @author tharduz
 */
public class IntegralMontecarloMV {

    private double[] limInf;
    private double[] limSup;
    private boolean evitarCero = false;
    FuncionMV f;
    FuncionMV entrada;
    
    MotorCongruenteBinario[] motores;
    
    private int n;
    private double result;
    
    
    public IntegralMontecarloMV(double[] limInf, double[] limSup, int n, FuncionMV entrada) {
        this.n = n;
        this.limInf = limInf;
        this.limSup = limSup;
        
        
        if (true) {
            this.f = entrada;
        }  else if(/*limInf == 0 && limSup == Double.POSITIVE_INFINITY*/ false) {
            this.f = new FuncionMV() {
                @Override
                public double eval(double[] x) {
                    double result = 0;
                    for(double xi : x) {
                        xi = 1/xi - 1;
                        result += entrada.eval(x)/(xi*xi);
                    }
                    return result;
                }
            };
        } else if(/*limInf == Double.NEGATIVE_INFINITY && limSup == Double.POSITIVE_INFINITY*/ false) {
            evitarCero = true;
            this.f = new FuncionMV() {
                @Override
                public double eval(double[] x) {
                    double result = 0;
                    for(double xi : x) {
                        xi = Math.log( xi / (1- xi));
                        result += entrada.eval(x)/(xi*xi * (1/(xi*(1-xi))));
                    }
                    return result;
                }
            };
        } else { // Con esa condición ya no es de 0 a 1
            // Hace substitiución para el caso de entre a y b
            this.f = new FuncionMV() {
                @Override
                public double eval(double[] x) {
                    /*double a = limInf;
                    double b = limSup;
                    double result = 0;
                    for(double xi : x) {
                        xi = xi * (b - a) + a ;
                        result += entrada.eval(x)/(xi*xi * (1/(xi*(1-xi))));
                    }
                    return result;*/
                    return  /** (b - a)*/0 ;
                }
            };
        }
    }
    
    public double integrar() {
        double suma = 0;
        double aux;
        double factor = 0;
        double[] r = new double[limInf.length];
        boolean hayCero = false;
        motores = new MotorCongruenteBinario[r.length];
        
        for(int i = 0, c = r.length; i <c; i++ ){
            motores[i] = new MotorCongruenteBinario(n , MotorCongruente.Tipos.MIXTO);
        }
            
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < r.length; j++){
                r[j] = motores[j].siguienteNormal();
                if(r[j] == 0)
                   hayCero = true;
            }
            //System.out.println(r);
            if(hayCero && evitarCero)
                continue;
            aux = this.f.eval(r);
            //System.out.println(aux);
            if(!Double.isNaN(aux)) {
                suma += aux;
            }
        }
        return result =  suma /(double) n;
    }
    
    public void agregarFuncion(FuncionMV entrada) {
        this.entrada = f;
    }
}
