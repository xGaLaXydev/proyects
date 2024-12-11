package ud5;

import java.util.Scanner;

public class metodoSeparador {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Introduce cadena");
		String cadena=sc.nextLine();
		System.out.println("Introduce caracter separador");
		String separador=sc.nextLine();
		String[] almacenCadenas=separador(cadena, separador);
		for(int i=0;i<almacenCadenas.length;i++) {
			System.out.println("Cadena "+(i+1)+ " "+almacenCadenas[i]);
		}
		sc.close();
	}
	public static String [] separador(String cadena, String sep) {
		int contSep=0;
		for(int i=1;i<cadena.length();i++) {
			if (cadena.charAt(i)==sep.charAt(0)&&i!=cadena.length()-1) {
				contSep++;
			}
		}
		String[] cadenasDivididas=new String[contSep+1];
		for(int i=0;i<contSep+1;i++) {
			cadenasDivididas[i]="";
		}
		int j=0;
		for(int i=0;i<contSep+1;i++) {
			while(j<cadena.length()) {
				if(cadena.charAt(j)!='@') {
					cadenasDivididas[i]+=cadena.charAt(j);
				}
				if (cadena.charAt(j)=='@'&&j!=0) {
					break;
				}
				j++;
			}
			j++;
		}
		return cadenasDivididas;
	}

}
