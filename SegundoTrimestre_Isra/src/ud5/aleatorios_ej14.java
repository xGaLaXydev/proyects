package ud5;

import java.util.Random;

/*. Realiza un generador de melodía con las siguientes condiciones:
a) Las notas deben estar generadas al azar. Las 7 notas son do, re, mi, fa, sol, la y si.
b) Una melodía está formada por un número aleatorio de notas mayor o igual a 4, menor o igual a
28 y siempre múltiplo de 4 (4, 8, 12…).
c) Cada grupo de 4 notas será un compás y estará separado del siguiente compás mediante la
barra vertical “|” (Alt + 1). El final de la melodía se marca con dos barras.
d) La última nota de la melodía debe coincidir con la primera.
Ejemplo 1:
do mi fa mi | si do sol fa | fa re si do | sol mi re do ||
Ejemplo 2:
la re mi sol | fa mi mi si | do la sol fa | fa re si sol | do sol mi re | fa la do la ||*/
public class aleatorios_ej14 {
	static Random random=new Random();
	public static void main(String[] args) {
		generarMelodía();

	}
	public static String generarCompas(String ultimaNota, boolean ultimoCompas) {
		String primeraNota="";
		String [] notas= {"do ","re ","mi ","fa ","sol ","la ","si "};
		int j;
		if(ultimoCompas==true) {
			j=3;
		} else {
			j=4;
		}
		for(int i=0;i<j;i++) {
			if (i==0) {
				primeraNota=notas[random.nextInt(7)];
				System.out.print(primeraNota);
			} else {
				System.out.print(notas[random.nextInt(7)]);
			}
		}
		if (ultimoCompas==true) {
			System.out.print(ultimaNota);
		}
		return primeraNota;
	}
	public static void generarMelodía(){
		int numeroCompases=random.nextInt(7)+1;
		String primeraNota="";
		for(int i=0;i<numeroCompases;i++) {
			if (i==0) {
				primeraNota=generarCompas(primeraNota, false);
				System.out.print("| ");
			} 
			if(i!=0&&i!=numeroCompases-1) {
				generarCompas(primeraNota, false);
				System.out.print("| ");
			}
			if (i==numeroCompases-1) {
				generarCompas(primeraNota, true);
				System.out.print("||");
			}
		}
	}
}
