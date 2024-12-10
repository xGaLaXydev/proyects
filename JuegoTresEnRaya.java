package Examen3EnRaya;

import java.util.Scanner;

public class JuegoTresEnRayaISRAEL {
	static Scanner in = new Scanner(System.in);
	static char[] tablero = new char[9];
	
	public static void main(String[] args) {
		int [][] recrearPartida = new int[9][9];
		int victoriasJugador1=0;
		int victoriasJugador2=0;
		int empates=0;
		int opcion=0;
		char jugador1;
		char jugador2;
		/*Ejecuta el codigo mientras la opción 4 no sea seleccionada, en ese caso, se termina el programa.*/
		while (opcion!= 4) {
			opciones();
			EsUnNumero();
			opcion=opcionValida(in.nextInt());
			/*Empieza la partida*/
			if (opcion==1) {
				jugador1=eligeFicha();
				if (jugador1=='X') {
					jugador2='O';
				} else {
					jugador2='X';
				}
				iniciar(recrearPartida);
				/*Se ejecuta el ciclo de la partida(Mueve jugador 1, 
				 se comprueba si gana, mueve jugador 2, se comprueba si gana y se comprueba si ha habido empate)*/
				while (quedanCasillas()==true) {
					quedanCasillas();
					System.out.println("Introduzca movimiento (Jugador 1): ");
					EsUnNumero();
					mueveJugador1(jugador1,in.nextInt(),recrearPartida);
					muestraResultado(jugador1, jugador2);
					if (ganaJugador1(jugador1)==true) {
						victoriasJugador1++;
						break;
					}
					if (esEmpate(jugador1,jugador2)==true) {
						empates++;
						break;
					}
					System.out.println("Introduzca movimiento (Jugador 2): ");
					EsUnNumero();
					mueveJugador2(jugador2,in.nextInt(),recrearPartida);
					muestraResultado(jugador1, jugador2);
					if (ganaJugador2(jugador2)==true) {
						victoriasJugador2++;
						break;
					}
					if (esEmpate(jugador1,jugador2)==true) {
						empates++;
						break;
					}
				}
				
			}
			/*Muestra las estadísticas de la partida*/
			if (opcion==2) {
				int partidasJugadas=(victoriasJugador1+victoriasJugador2+empates);
				System.out.println("------------\nESTADÍSTICAS\n------------");
				System.out.println("Victorias Jugador 1: "+victoriasJugador1+"\nVictorias Jugador 2: "
								+victoriasJugador2+"\nPartidas Empatadas : "+empates+"\nPartidas Jugadas : "
								+partidasJugadas);
				System.out.println();
				
			}
			/*Muestra la partida anterior recreada movimiento por movimiento.*/
			if (opcion==3) {
				int j=0;
				for(j=0;j<9;j++) {
					if ((char)recrearPartida[j][0]!='X'&&
						(char)recrearPartida[j][0]!='O'&&
						(char)recrearPartida[j][0]!=' ') {
							break;
					}
				}
				avanzarRetroceder(j,recrearPartida);
				recrearPartida[j][0]=00;
			}
		}
		System.out.println("Ha salido con exito del juego!");
	}
	
	/*Muestra un mensaje de error en caso de que el movimiento del jugador 1
	  no sea válido y si es válido resgistra el movimiento*/
	
	public static void mueveJugador1(char simbolo, int pos,int [][] recrearPartida) {
		while (movimientoValido(pos-1)==false) {
			System.out.println("Introduce un movimiento válido!");
			dibujaTablero();
			EsUnNumero();
			pos=in.nextInt();
		}
		if (movimientoValido(pos-1)==true) {
			tablero[pos-1]=simbolo;
			recrearPartida(recrearPartida);
		}
		dibujaTablero();
	}
	
	/*Muestra un mensaje de error en caso de que el movimiento del jugador 2
	  no sea válido y si es válido resgistra el movimiento*/
	
	public static void mueveJugador2(char simbolo,int pos,int [][] recrearPartida) {
		while (movimientoValido(pos-1)==false) {
			System.out.println("Introduce un movimiento válido!");
			dibujaTablero();
			EsUnNumero();
			pos=in.nextInt();
		}
		if (movimientoValido(pos-1)==true) {
			tablero[pos-1]=simbolo;
			recrearPartida(recrearPartida);
		}
		dibujaTablero();
	}
	
	//Comprueba que el valor introducido por teclado es un numero entre 1 y 9, y por lo tanto, un movimiento válido
	
	public static boolean movimientoValido(int pos) {
		if(pos<0||pos>8||tablero[pos]!=' ') {
			return false;
		}
		return true;
	}
	
	/*Iniciamos el tablero, mostrando los numeros del 1 al 9 en las celdas 
	para que los jugadores sepan la posicion de las mismas. Después lo rellenamos de espacios vacios.*/
	
	public static void iniciar(int [][] recrearPartida) {
		for(int k=0;k<9;k++) {
			recrearPartida[k][0]=00;
		}
		int p=0;
		for(char i=49;i<58;i++) {
			tablero[p]=i;
			p++;
		}
		dibujaTablero();
		for(int i=0;i<9;i++) {
			tablero[i]=' ';
		}
	}
	
	//Comprueba que queden movimientos posibles.
	
	public static boolean quedanCasillas() {
		for(int i=0;i<9;i++) {
			if (tablero[i]==' ') {
				return true;
			}
		}
		return false;
	}
	
	//Dibuja el tablero
	
	public static void dibujaTablero() {
	System.out.println();
	System.out.println("-------------");
	System.out.println("| "+tablero[0]+" | "+tablero[1]+" | "+tablero[2]+" |");
	System.out.println("-------------");
	System.out.println("| "+tablero[3]+" | "+tablero[4]+" | "+tablero[5]+" |");
	System.out.println("-------------");
	System.out.println("| "+tablero[6]+" | "+tablero[7]+" | "+tablero[8]+" |");
	System.out.println("-------------");
	}
	
	//Comprueba todas las opciones posibles en las que el jugador 1 ganaría la partida.
	
	public static boolean ganaJugador1(char simbolo) {
		if (tablero[0]==simbolo&&tablero[1]==simbolo&&tablero[2]==simbolo||
			tablero[3]==simbolo&&tablero[4]==simbolo&&tablero[5]==simbolo||
			tablero[6]==simbolo&&tablero[7]==simbolo&&tablero[8]==simbolo||
			tablero[0]==simbolo&&tablero[4]==simbolo&&tablero[8]==simbolo||
			tablero[0]==simbolo&&tablero[3]==simbolo&&tablero[6]==simbolo||
			tablero[1]==simbolo&&tablero[4]==simbolo&&tablero[7]==simbolo||
			tablero[2]==simbolo&&tablero[5]==simbolo&&tablero[8]==simbolo||
			tablero[2]==simbolo&&tablero[4]==simbolo&&tablero[6]==simbolo) {
			
			return true;
		}
		return false;
	}
	
	//Comprueba todas las opciones posibles en las que el jugador 2 ganaría la partida.
	
	public static boolean ganaJugador2(char simbolo) {
		if (tablero[0]==simbolo&&tablero[1]==simbolo&&tablero[2]==simbolo||
			tablero[3]==simbolo&&tablero[4]==simbolo&&tablero[5]==simbolo||
			tablero[6]==simbolo&&tablero[7]==simbolo&&tablero[8]==simbolo||
			tablero[0]==simbolo&&tablero[4]==simbolo&&tablero[8]==simbolo||
			tablero[0]==simbolo&&tablero[3]==simbolo&&tablero[6]==simbolo||
			tablero[1]==simbolo&&tablero[4]==simbolo&&tablero[7]==simbolo||
			tablero[2]==simbolo&&tablero[5]==simbolo&&tablero[8]==simbolo||
			tablero[2]==simbolo&&tablero[4]==simbolo&&tablero[6]==simbolo) {
				
			return true;
		}
		return false;
	}
	
	//Comprueba que el valor introducido por teclado es un número y muestra un mensaje de error en caso contrario
	
	public static void EsUnNumero() {
		while (!in.hasNextInt()) {
			System.out.println("Introduzca un numero válido: ");
			in.next();
		}
	}
	
	//Muestra el menú de opciones
	
	public static void opciones() {
		System.out.println("1. Jugar una partida\n"
				+ "2. Mostrar estadísticas\n"
				+ "3. Recrear última partida\n"
				+ "4. Salir\n"
				+ "Seleccione una opción:");
	}
	
	/*Comprueba que el valor introducido por teclado es una opción válida del menú y 
	        muestra un mensaje de error en caso contrario*/
	
	public static int opcionValida(int op) {
		while (op!=1&&op!=2&&op!=3&&op!=4) {
			System.out.println("Introduce una opción válida!");
			EsUnNumero();
			op=in.nextInt();
		}
		return op;
	}
	
	/*Comprueba en que turno estamos segun el numero de X y O que hay dibujados
	Rellena el array bidimensional con el numero de movimiento y los valores de ese movimiento.*/
	
	public static void recrearPartida(int [][] recrearPartida) {
		int cont=0;
		for(int i=0;i<9;i++) {
			if (tablero[i]=='X'||tablero[i]=='O') {
				cont++;
			}
		}
		for(int i=0;i<9;i++) {
			recrearPartida[cont-1][i]=tablero[i];
		}
	}
	
	//Dibuja el tablero recreado
	
	public static void dibujaTableroRecreado(int i,int [][] recrearPartida) {
		System.out.println();
		System.out.println("Turno "+(i+1)+":");
		System.out.println("-------------");
		System.out.println("| "+(char)recrearPartida[i][0]+" | "+(char)recrearPartida[i][1]+" | "+(char)recrearPartida[i][2]+" |");
		System.out.println("-------------");
		System.out.println("| "+(char)recrearPartida[i][3]+" | "+(char)recrearPartida[i][4]+" | "+(char)recrearPartida[i][5]+" |");
		System.out.println("-------------");
		System.out.println("| "+(char)recrearPartida[i][6]+" | "+(char)recrearPartida[i][7]+" | "+(char)recrearPartida[i][8]+" |");
		System.out.println("-------------");
	}
	public static char eligeFicha() {
		char fichaJugador1;
		int opcion;
		System.out.println("Elige ficha Jugador 1: \n"
				+ "1- X \n"
				+ "2- O ");
		EsUnNumero();
		opcion=in.nextInt();
		if (opcion==1) {
			fichaJugador1='X';
		} else {
			fichaJugador1='O';
		}
		return fichaJugador1;
	}
	public static void avanzarRetroceder(int i, int [][] recrearPartida) {
		int j=0;
		 while(j<i) {
			 int opciones=3;
			 if (j==0) {
				 dibujaTableroRecreado(j, recrearPartida);
				 System.out.println("Avanzar Turno: \n"
				 		+ "1-Avanzar");
				 EsUnNumero();
				 opciones=in.nextInt();
				 if (opciones==1) {
					 j++;
					
				}
			} else if(j!=0&&j!=(i-1)){
				dibujaTableroRecreado(j, recrearPartida);
			 System.out.println("Avanzar Turno: \n"
				 		+ "1-Avanzar\n"
				 		+ "2-Retroceder");
			 EsUnNumero();
			 opciones=in.nextInt();
			 if (opciones==1) {
				 j++;
			}
			 if (opciones==2) {
				j--;
			}
			} else if(j==(i-1)) {
				System.out.println("\nUltimo turno.");
				dibujaTableroRecreado(j, recrearPartida);
				 System.out.println("Avanzar Turno: \n"
					 		+ "1-Acabar Recreacion\n"
					 		+ "2-Retroceder");
				 EsUnNumero();
				 opciones=in.nextInt();
				 if (opciones==1) {
					 System.out.println("Recreacion Acabada");
					 break;
				}
				 if (opciones==2) {
					j--;
				 }
			}
	
		 }
		 
	}
	public static boolean esEmpate(char jugador1, char jugador2) {
		if (quedanCasillas()==false&&ganaJugador1(jugador1)==false&&ganaJugador2(jugador2)==false) {
			return true;
		}
		return false;
	}
	public static void muestraResultado(char jugador1, char jugador2) {
		if (ganaJugador1(jugador1)==true) {
			System.out.println("Ha ganado el jugador 1");
		} 
		if (ganaJugador2(jugador2)==true) {
			System.out.println("Ha ganado el jugador 2");
		}
		if (esEmpate(jugador1,jugador2)==true) {
			System.out.println("La partida ha terminado en empate");
		}
	}
}

