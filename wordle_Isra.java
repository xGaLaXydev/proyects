package ejercicios;
import java.util.Random;
import java.util.Scanner;

public class wordle_Isra {
	static String palabraSecreta;
	static int numIntConsumidos=5;
	static int numLetrasAdivinadas=0;
	static Scanner sc=new Scanner(System.in);
	static Random random=new Random();
	public static void main(String[] args) {
		char opcion='p';
		int maquina=0;
		int persona=0;
		String palabraIntroducida="";
		String palabraAdivinada="";
		palabraSecreta=generaPalabra();
		System.out.println("Bienvenid@ al juego de Wordle.\n"
				+ "El objetivo es descubrir la palabra oculta de 5\n"
				+ "letras.");
		//Inicia el ciclo de partida.
		do {
			System.out.println("Introduce una palabra de 5 letras:");
			palabraIntroducida=sc.nextLine();
			if (comprobaciones(palabraIntroducida)) {
				palabraAdivinada=compruebaLetrasAcertadas(palabraIntroducida);
				mostrarPalabraAdivinada(palabraAdivinada);
			}
			if (haGanadoJugador()) {
				System.out.println("Has ganado la partida");
				persona++;
				System.out.println("Tú: "+persona+" puntos vs Máquina: "+maquina+" puntos\n"
						+ "Total de partidas: "+(persona+maquina));
				opcion=controlarRespuesta();
			} 
			if (haTerminadoJuego()) {
				System.out.println("Has perdido la partida, la palabra secreta era: "+palabraSecreta);
				maquina++;
				System.out.println("Tú: "+persona+" puntos vs Máquina: "+maquina+" puntos\n"
						+ "Total de partidas: "+(persona+maquina));
				opcion=controlarRespuesta();
			}
			numLetrasAdivinadas=0;
			numIntConsumidos--;
			if (opcion=='s'||opcion=='S') {
				resetearVariablesGlobales();
				opcion='p';
			}
		} while (opcion!='n'&&opcion!='N');
		System.out.println("Ha salido con exito!");
	}
	//Comprueba si ha ganado el jugador(Palabra secreta acertada).
	public static boolean haGanadoJugador() {
		if (numLetrasAdivinadas==5) {
			return true;
		}
		return false;
	}
	//Comprueba que queden intentos.
	public static boolean haTerminadoJuego() {
		if (numIntConsumidos==0) {
			System.out.println("Te has quedado sin intentos");
			return true;
		}
		return false;
	}
	//Comprueba letra por letra si la palabra secreta la contiene y si es la misma posición o no.
	public static String compruebaLetrasAcertadas(String palabra) {
		String adivinada="";
		for(int i=0;i<palabra.length();i++) {
			if (palabra.toUpperCase().charAt(i)==palabraSecreta.toUpperCase().charAt(i)) {
				adivinada+=palabra.toUpperCase().charAt(i);
				numLetrasAdivinadas++;
				if (adivinada.length()==5) {
					break;
				}
			} else {
				for(int j=0;j<palabra.length();j++) {
					if (palabra.toUpperCase().charAt(i)==palabraSecreta.toUpperCase().charAt(j)) {
						adivinada+=palabra.toLowerCase().charAt(i);
						break;
					}
				}
				if (adivinada.length()!=i+1) {
					adivinada+="*";
				}
			}
		}
		return adivinada;
	}
	//Elige una palabra aleatoria de entre 20 posibles.
	public static String generaPalabra() {
		String [] palabras = {"Amigo", "Lucha", "Cebra", "Santa", "Barco", 
	            "Silla", "Rango", "Llave", "Tinto", "Lanza", 
	            "Canto", "Verde", "Ciego", "Trago", "Salto", 
	            "Fuego", "Peces", "Risas", "Mente", "Borde"}; 
		return palabras[random.nextInt(20)];
	}
	//Unimos todas las comprobaciones en una única función.
	private static boolean comprobaciones(String palabra) {
		if (longitud(palabra)&&contenido(palabra)&&vocales(palabra)&&consonantes(palabra)&&letraTerminada(palabra)&&vocalesSeguidas(palabra)) {
			return true;
		}
		numIntConsumidos++;
		return false;
	}
	//Se comprueba que la longitud de nuestra palabra es de 5 letras.
	private static boolean longitud(String palabra) {
		if (palabra.length()!=5) {
			System.out.println("Introduce una palabra de exactamente 5 letras!!");
			return false;
		}
		return true;
	}
	//Se comprueba que la palabra solo contenga letras.
	private static boolean contenido(String palabra) {
		if (palabra.substring(0,5).matches("[A-Z]*")||palabra.substring(0,5).matches("[a-z]*")||palabra.substring(0,5).matches("ñ")) {
			return true;
		}
		System.out.println("Introduce solamente letras!!");
		return false;
	}
	//Se comprueba que la palabra tenga 2 o 3 vocales.
	private static boolean vocales(String palabra) {
		int contadorVocales=0;
		for(int i=0;i<palabra.length();i++) {
			if (palabra.charAt(i)=='a'
				||palabra.charAt(i)=='A'
				||palabra.charAt(i)=='e'
				||palabra.charAt(i)=='E'
				||palabra.charAt(i)=='i'
				||palabra.charAt(i)=='I'
				||palabra.charAt(i)=='o'
				||palabra.charAt(i)=='O'
				||palabra.charAt(i)=='u'
				||palabra.charAt(i)=='U') {
				contadorVocales++;
			}
		}
		if (contadorVocales!=2&&contadorVocales!=3) {
			System.out.println("La palabra debe contener entre 2 y 3 vocales!!");
			return false;
		}
		return true;
	}
	//Se comprueba que la palabra no contenga mas de 3 consonantes seguidas.
	private static boolean consonantes(String palabra) {
		int contadorConsonantes=0;
		for(int i=0;i<palabra.length();i++) {
			if (palabra.charAt(i)!='a'
				&&palabra.charAt(i)!='A'
				&&palabra.charAt(i)!='e'
				&&palabra.charAt(i)!='E'
				&&palabra.charAt(i)!='i'
				&&palabra.charAt(i)!='I'
				&&palabra.charAt(i)!='o'
				&&palabra.charAt(i)!='O'
				&&palabra.charAt(i)!='u'
				&&palabra.charAt(i)!='U') {
				contadorConsonantes++;
			}
			if (palabra.charAt(i)=='a'
				||palabra.charAt(i)=='A'
				||palabra.charAt(i)=='e'
				||palabra.charAt(i)=='E'
				||palabra.charAt(i)=='i'	
				||palabra.charAt(i)=='I'
				||palabra.charAt(i)=='o'
				||palabra.charAt(i)=='O'
				||palabra.charAt(i)=='u'
				||palabra.charAt(i)=='U') {
				contadorConsonantes=0;
			}
			if (contadorConsonantes>3) {
				System.out.println("No puedes introducir mas de 3 consonantes seguidas!!");
				return false;
			}
		}
		return true;
	}
	//Se comprueba que la palabra no termina en q, w o x. 
	private static boolean letraTerminada(String palabra) {
		if (palabra.charAt(palabra.length()-1)=='q'||palabra.charAt(palabra.length()-1)=='w'||palabra.charAt(palabra.length()-1)=='x') {
			System.out.println("La palabra no puede terminar ni en 'q', ni en 'w' ni en 'x'!!");
			return false;
		}
		return true;
	}
	//Se comprueba que no haya dos vocales seguidas en la palabra que introduce el usuario.
	private static boolean vocalesSeguidas(String palabra) {
		if(palabra.contains("aa")||palabra.contains("ee")||palabra.contains("ii")||palabra.contains("oo")||palabra.contains("uu")) {
			System.out.println("No puedes introducir dos vocales seguidas!!");
			return false;
		}
		return true;
	}
	//Se comprueba que la respuesta sea si o no.
	private static char controlarRespuesta() {
		System.out.println("¿Deseas jugar otra partida?");
		char respuesta=sc.nextLine().charAt(0);
		while (respuesta!='n'&&respuesta!='N'&&respuesta!='s'&&respuesta!='S') {
			System.out.println("Introduce una opción válida(S/N)");
			respuesta=sc.nextLine().charAt(0);
		}
		return respuesta;
	}
	//Resetea todas las variables globales para evitar errores.
	private static void resetearVariablesGlobales() {
		palabraSecreta=generaPalabra();
		numIntConsumidos=5;
		numLetrasAdivinadas=0;
	}
	private static void mostrarPalabraAdivinada(String palabra) {
		System.out.println("-----------");
		for(int i=0;i<5;i++) {
			System.out.print("|"+palabra.charAt(i));
		}
		System.out.print("|\n");
		System.out.println("-----------");
	}
}