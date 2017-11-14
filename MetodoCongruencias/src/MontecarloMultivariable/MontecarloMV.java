/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MontecarloMultivariable;

/**
 *
 * @author tharduz
 */
public class MontecarloMV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[] li ={0,0};
        double[] lf ={1,1};
        double media = 0;
        IntegralMontecarloMV integral = new IntegralMontecarloMV( li ,lf, 15000, new FuncionMV() {
            @Override
            public double eval(double[] x) {
               return Math.exp(Math.pow(x[0] + x[1], 2));
            }
        });
        for(int i = 0; i < 3000; i++)
            media += integral.integrar();
        media/=3000;
        System.out.println("integral e^(x^2 + y^2) desde 0 hasta 1: "  + media);
    }
    
}
