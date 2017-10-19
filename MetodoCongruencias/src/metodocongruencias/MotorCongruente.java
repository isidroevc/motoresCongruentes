/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodocongruencias;

/**
 *
 * @author tharduz
 */
public abstract class MotorCongruente {
    
    public static enum Tipos {
        MULTIPLICATIVO,
        MIXTO
    };
    
    long n0;
    long n;
    long a;
    long c;
    long m;
    long h;
    long b;
    public long getN0() {
        return n0;
    }

    public void setN0(int n0) {
        this.n0 = n0;
    }

    public long getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public long getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public long getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public long getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public long getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public long getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }
    Tipos tipo;
    public long siguienteNumero(){
        long al = this.n;
        this.n = Math.abs((a *n + c) % m);
        return al;
    }
    
    public double siguienteNormal() {
        return (double)siguienteNumero() /m;
    }
    @Override
    public String toString(){
        return "n0: " + this.n0 + "\n"+
                "n: " + this.n + "\n"
                + "a: " + this. a + "\n"
                + "c: " + this.c + "\n"
                + "b: " + this.b + "\n"
                + "h: " + this.h + "\n"
                + "m: " + this.m; 
                
    }  
}
