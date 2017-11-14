/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Montecarlo;

public class IntegracionMontecarlo {

    public static void main(String[] args) {
        IntegralMontecarlo[] integrales = new IntegralMontecarlo[7];
        int iteraciones = 15000;
        int n = 3000;
        double media = 0;
        String[] mensajes = new String[8];
        String resultados = "";
        //(1-x^2)^1.5
        integrales[0] = new IntegralMontecarlo(0, 1, iteraciones, new Funcion() {
            @Override
            public double eval(double x) {
                x = x * x;
                return Math.pow(1 - x, 1.5);
            }
        });
        mensajes[0] = "(1-x^2)^1.5 desde 0 hasta 1";

        //e^(e^x)
        integrales[1] = new IntegralMontecarlo(0, 1, iteraciones, new Funcion() {

            @Override
            public double eval(double x) {
                return Math.exp(Math.exp(x));
            }
        });

        mensajes[1] = "e^(e^x) desde 0 hasta 1";

        //e^(x + x^2)
        integrales[2] = new IntegralMontecarlo(-2, 2, iteraciones, new Funcion() {
            @Override
            public double eval(double x) {
                return Math.exp(x + x * x);
            }
        });
        mensajes[2] = "e^(x + x^2) desde -2 hasta 2";

        //e^(-x^2)
        integrales[3] = new IntegralMontecarlo(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, iteraciones,
                new Funcion() {
                @Override
                public double eval(double x) {
                    x = x * x * -1;
                    return Math.exp(x);
                }
        });
        mensajes[3] = "e^(-x^2) desde -infinito hasta infinito";

        integrales[4] = new IntegralMontecarlo(0,
                Double.POSITIVE_INFINITY, iteraciones, 
                new Funcion() {
                    @Override
                     public double eval(double x) {
                        x = x * x * -1;
                        return Math.exp(x);
                    }
                });
        mensajes[4] = "e^(-x^2) desde 0 hasta infinito";

        //x*(1 + x^2)^-2
        
        integrales[5] = new IntegralMontecarlo(0, Double.POSITIVE_INFINITY,
                 iteraciones, 
                new Funcion() {
                    @Override
                    public double eval(double x) {
                        return x * Math.pow(1 + x * x, -2);
                    }
        });
        mensajes[5] = "x*(1 + x^2)^-2 desde 0 hasta infinito";

        //sen(x)/x
        

        integrales[6] = new IntegralMontecarlo(-100, 100, iteraciones,
                new Funcion() {
                    @Override
                    public double eval(double x) {
                        return Math.sin(x) / x;
                    }
        });
        mensajes[6] = "sen(x)/x desde -100 hasta 100";

        
        
        // hacer n veces cada integral.
        int j = 0;
        for (IntegralMontecarlo integral : integrales) {
            media = 0;
            for (int i = 0; i < n; i++) {
                media += integral.integrar();
            }
            media /= n;
            resultados += mensajes[j] + ": " + media + "\n";
            j++;
            System.out.println(j);
        }
        System.out.println(resultados);
    }

}
