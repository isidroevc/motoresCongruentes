package metodocongruencias;

import java.util.Random;

/**
 *
 * @author tharduz
 */
public class MotorCongruenteDecimal extends MotorCongruente {

	private static long[] PRIMOS_MIENTRAS = {3,11,13,19,21,29,63,61,77,83,91};

	public MotorCongruenteDecimal(int h, Tipos tipo) {
		this.tipo = tipo;
		this.b = this.calcularD(h);
		this.m = (int) Math.pow(10, this.b);
		this.a = this.calcularA();
		if (this.tipo == Tipos.MULTIPLICATIVO) {
			this.h = 5 * (long) Math.pow(10, b - 2);
			this.c = 0;
			this.n = calcularC() % m;
                        this.n0 = n;
		} else {
			this.h = this.m;
			this.c = this.calcularC();
			this.n = new Random().nextInt();
			this.n = Math.abs(this.n) % m;
                        this.n0 = n;
		}
	}

	private int calcularD(int h) {
		double aprox;
		int bresult;
		if (this.tipo == Tipos.MULTIPLICATIVO) {
			aprox = (Math.log10(h) - Math.log10(5) + 2);
			if (Math.floor(aprox) != aprox) {
				bresult = (int) aprox + 1;
			} else {
				bresult = (int) aprox;
			}
			if (bresult < 4) {
				bresult = 4;
			}
		} else {
			aprox = Math.log10(h);
			if (Math.floor(aprox) != aprox) {
				bresult = (int) aprox + 1;
			} else {
				bresult = (int) aprox;
			}
			if (bresult < 3) {
				bresult = 3;
			}
		}
		System.out.println(aprox);

		return bresult;
	}

	private long calcularA() 
	{
		Random rand = new Random();
		long t, i;
		long result;
		if (this.tipo == Tipos.MULTIPLICATIVO) {
			t = Math.abs(rand.nextInt());
			i = t % PRIMOS_MIENTRAS.length;
			result = PRIMOS_MIENTRAS[(int) i] + 200 * t % 200;
		} else {
			if (this.b % 2 == 0) {
				t = this.b;
			} else {
				t = this.b - 1;
			}
			result = (long) Math.pow(10, t) + 1;
			if (this.b == 3) {
				result = 101;
			}
		}
		return result;
	}

	private long calcularC() {
		Random rand = new Random();
		int factor = rand.nextInt();
                int result = 2 * factor + 1;
                if(result % 5 == 0){
                    result += 2;
                }
		return Math.abs(result);
	}
}
