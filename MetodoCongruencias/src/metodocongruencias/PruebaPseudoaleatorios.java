package metodocongruencias;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class PruebaPseudoaleatorios {
	MotorCongruenteBinario binario;
	MotorCongruenteDecimal decimal;
	FileOutputStream fos;
	DataOutputStream dos;
	Scanner sc = new Scanner(System.in);
	int menu[] = new int[3];
        long desplegar; 
	String aux = "";
	
	void inicio() {
		System.out.println("Programa para generar numeros pseudoaleatorios por el"
				+ " metodo de congruencias");
	}
	
	void datos() {
		System.out.print("Cuantos Numeros quiere generar: \n");
		menu[0] = sc.nextInt();
		System.out.print("Modo \n1.- Binario\n2.- Decimal: ");
		menu[1] = sc.nextInt();
		System.out.print("Metodo \n1.- Multiplicativo\n2.- Mixto: \n");
		menu[2] = sc.nextInt();
	}
	
	void resultados() {
		switch (menu[1]) {
			case 1:
				switch (menu[2]) {
					case 1:
						aux = "Tipo de prueba, Binario, Multiplicativo\n";
						binario = new MotorCongruenteBinario(menu[0], MotorCongruente.Tipos.MULTIPLICATIVO);
						aux += binario.toString() + "\nN,Numeros pseudoaleatorios\n";
                                                desplegar = binario.getH();
						for(int i  = 0; i < desplegar+5; i++) {
							aux += (i + 1) + ".- ," + binario.siguienteNumero() + "\n";   
				        }
						System.out.print(aux);
						break;
					case 2:
						aux = "Tipo de prueba, Binario, Mixto\n";
						binario = new MotorCongruenteBinario(menu[0], MotorCongruente.Tipos.MIXTO);
						aux += binario.toString() + "\nN,Numeros pseudoaleatorios\n"; 
                                                desplegar = binario.getH();
						for(int i  = 0; i < desplegar+5; i++) {
							aux += (i + 1) + ".- ," + binario.siguienteNumero() + "\n";   
				        }
						System.out.print(aux);
						break;
				}
				guardarArchivo();
				break;
			case 2:
				switch (menu[2]) {
					case 1:
						aux = "Tipo de prueba, Decimal, Multiplicativo\n";
						decimal = new MotorCongruenteDecimal(menu[0], MotorCongruente.Tipos.MULTIPLICATIVO);
						aux += decimal.toString() + "\nh,Numeros pseudoaleatorios\n";
                                                desplegar = decimal.getH();
						for(int i  = 0; i < desplegar+5; i++) {
							aux += (i + 641) + " ," + decimal.siguienteNumero() + "\n";   
				        }
						System.out.print(aux);
						break;
					case 2:
						aux = "Tipo de prueba, DECIMAL, Mixto\n";
						decimal = new MotorCongruenteDecimal(menu[0], MotorCongruente.Tipos.MIXTO);
						aux += decimal.toString() + "\nh,Numeros pseudoaleatorios\n";
						for(int i  = 0; i < menu[0]+5; i++) {
							aux += (i + 1) + " ," + decimal.siguienteNumero() + "\n";   
				        }
						System.out.print(aux);
						break;
				}
				guardarArchivo();
				break;

		}
	}
	
	void guardarArchivo() {
		try {
			fos = new FileOutputStream(new File( System.currentTimeMillis() + "-Datos.csv"));
			dos = new DataOutputStream(fos);
			dos.writeUTF(aux);
		} catch (FileNotFoundException e) {} catch (IOException e) {}
		finally {
			if(dos!= null)
				try {
					dos.close();
					if(fos!= null) fos.close();
				} catch (IOException e) {}
			
		}
		
	}
	
	public static void main(String[] args) {
		PruebaPseudoaleatorios obj = new PruebaPseudoaleatorios();
		obj.inicio();
		obj.datos();
		obj.resultados();
	}
	
}
