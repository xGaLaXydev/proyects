package ud4;

import java.util.Scanner;

public class CuatroEnRaya {
	static Scanner in = new Scanner(System.in);
	static char[][] tablero = new char[7][6];
	
	public static void main(String[] args) {
		int [][][] recrearPartida = new int [42][7][6];
		int victoriasJugador1=0;
		int victoriasJugador2=0;
		int empates=0;
		int opcion=0;
		while (opcion!= 4) {
			opciones();
			EsUnNumero();
			opcion=opcionValida(in.nextInt());
			if (opcion==1) {
				iniciar();
				while (quedanCasillas()==true) {
					quedanCasillas();
					System.out.println("Introduzca movimiento (Jugador 1): ");
					EsUnNumero();
					mueveJugador1(in.nextInt(), recrearPartida);
					if (ganaJugador1()==true) {
						System.out.println("Ha Ganado el Jugador 1!");
						victoriasJugador1++;
						break;
					}
					if (quedanCasillas()==false) {
						System.out.println("La partida ha terminado en empate!");
						empates++;
						break;
					}
					System.out.println("Introduzca movimiento (Jugador 2): ");
					EsUnNumero();
					mueveJugador2(in.nextInt(), recrearPartida);
					if (ganaJugador2()==true) {
						System.out.println("Ha Ganado el Jugador 2!");
						victoriasJugador2++;
						break;
					}
					if (quedanCasillas()==false) {
						System.out.println("La partida ha terminado en empate!");
						empates++;
						break;
					}
				}
				
			}
			if (opcion==2) {
				System.out.println("------------\nESTADÍSTICAS\n------------");
				System.out.println("Victorias Jugador 1: "+victoriasJugador1+"\nVictorias Jugador 2: "
								+victoriasJugador2+"\nPartidas Empatadas : "+empates+"\nPartidas Jugadas : "
										+(victoriasJugador1+victoriasJugador2+empates));
				System.out.println();
				
			}
			if (opcion==3) {
				for(int j=0;j<42;j++) {
					if ((char)recrearPartida[j][0][0]!='X'&&
						(char)recrearPartida[j][0][0]!='O'&&
						(char)recrearPartida[j][0][0]!=' ') {
							break;
					}
					dibujaTableroRecreado(j, recrearPartida);
					recrearPartida[j][0][0]=00;
				}
			}
		}
		System.out.println("Ha salido con exito del juego!");
	}
	public static void dibujaTablero() {
		System.out.println("-----------------------------");
		System.out.println("| "+tablero[0][5]+" | "+tablero[1][5]+" | "+tablero[2][5]+" | "+tablero[3][5]+" | "+tablero[4][5]+" | "+tablero[5][5]+" | "+tablero[6][5]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+tablero[0][4]+" | "+tablero[1][4]+" | "+tablero[2][4]+" | "+tablero[3][4]+" | "+tablero[4][4]+" | "+tablero[5][4]+" | "+tablero[6][4]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+tablero[0][3]+" | "+tablero[1][3]+" | "+tablero[2][3]+" | "+tablero[3][3]+" | "+tablero[4][3]+" | "+tablero[5][3]+" | "+tablero[6][3]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+tablero[0][2]+" | "+tablero[1][2]+" | "+tablero[2][2]+" | "+tablero[3][2]+" | "+tablero[4][2]+" | "+tablero[5][2]+" | "+tablero[6][2]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+tablero[0][1]+" | "+tablero[1][1]+" | "+tablero[2][1]+" | "+tablero[3][1]+" | "+tablero[4][1]+" | "+tablero[5][1]+" | "+tablero[6][1]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+tablero[0][0]+" | "+tablero[1][0]+" | "+tablero[2][0]+" | "+tablero[3][0]+" | "+tablero[4][0]+" | "+tablero[5][0]+" | "+tablero[6][0]+" |");
		System.out.println("-----------------------------");
	}
	public static void iniciar() {
		for(char i=49;i<56;i++) {
			for(char j=0;j<6;j++) {
				tablero[i-49][j]=i;
			}
		}
		dibujaTablero();
		for(int i=0;i<7;i++) {
			for(int j=0;j<6;j++) {
				tablero[i][j]=' ';
			}
		}
	}
	public static boolean quedanCasillas() {
		for(int i=0;i<7;i++) {
			for(int j=0;j<6;j++) {
				if (tablero[i][j]==' ') {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean ganaJugador1() {
		if (comprobarHorizontal1()==true) {
			return true;
		}
		if (comprobarVertical1()==true) {
			return true;
		}
		if (comprobarDiagonal1()==true) {
			return true;
		}
		return false;
	}
	public static boolean comprobarHorizontal1() {
		int piezasSeguidas=1;
		for(int i=0;i<6;i++) {
			for(int j=1;j<7;j++) {
				if (tablero[j][i]=='X'&&tablero[j-1][i]=='X') {
					piezasSeguidas++;
				}  else {
					piezasSeguidas=1; 
				}
				if (piezasSeguidas==4) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean comprobarVertical1() {
		int piezasSeguidas=1;
		for(int i=0;i<7;i++) {
			for(int j=1;j<6;j++) {
				if (tablero[i][j]=='X'&&tablero[i][j-1]=='X') {
					piezasSeguidas++;
				}  else {
					piezasSeguidas=1; 
				}
				if (piezasSeguidas==4) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean comprobarDiagonal1() {
		for(int i=3;i<6;i++) {  
            for(int j=0;j<4;j++) {
            	if (j+3<7&&i-3>=0) {
            		 if(tablero[j][i]=='X'&&
                         tablero[j+1][i-1]=='X'&&
                         tablero[j+2][i-2]=='X'&&
                         tablero[j+3][i-3]=='X') {
                         return true; 
                     }
				}
            }
        }
	    for(int i=0;i<3;i++) {  
            for(int j=0;j<4;j++) { 
            	if (j+3<7&&i+3<6) {
            		 if(tablero[j][i]=='X'&&
                         tablero[j+1][i+1]=='X'&&
                         tablero[j+2][i+2]=='X'&&
                         tablero[j+3][i+3]=='X') {
                         return true;  
                     }
				}
            }
        }
	    return false;
	}
	public static boolean ganaJugador2() {
		if (comprobarHorizontal2()==true) {
			return true;
		}
		if (comprobarVertical1()==true) {
			return true;
		}
		if (comprobarDiagonal2()==true) {
			return true;
		}
		return false;
	}
	public static boolean comprobarHorizontal2() {
		int piezasSeguidas=1;
		for(int i=0;i<6;i++) {
			for(int j=1;j<7;j++) {
				if (tablero[j][i]=='O'&&tablero[j-1][i]=='O') {
					piezasSeguidas++;
				}  else {
					piezasSeguidas=1; 
				}
				if (piezasSeguidas==4) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean comprobarVertical2() {
		int piezasSeguidas=1;
		for(int i=0;i<7;i++) {
			for(int j=1;j<6;j++) {
				if (tablero[i][j]=='O'&&tablero[i][j-1]=='O') {
					piezasSeguidas++;
				}  else {
					piezasSeguidas=1; 
				}
				if (piezasSeguidas==4) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean comprobarDiagonal2() {
		for(int i=3;i<6;i++) {  
            for(int j=0;j<4;j++) {
            	if (j+3<7&&i-3>=0) {
            		 if(tablero[j][i]=='O'&&
                         tablero[j+1][i-1]=='O'&&
                         tablero[j+2][i-2]=='O'&&
                         tablero[j+3][i-3]=='O') {
                         return true; 
                     }
				}
            }
        }
	    for(int i=0;i<3;i++) {  
            for(int j=0;j<4;j++) { 
            	if (j+3<7&&i+3<6) {
            		 if(tablero[j][i]=='O'&&
                         tablero[j+1][i+1]=='O'&&
                         tablero[j+2][i+2]=='O'&&
                         tablero[j+3][i+3]=='O') {
                         return true;  
                     }
				}
            }
        }
	    return false;
	}
	public static void mueveJugador1(int pos, int [][][] recrearPartida) {
		while (movimientoValido(pos-1)==false) {
			System.out.println("Introduce un movimiento válido!");
			dibujaTablero();
			EsUnNumero();
			pos=in.nextInt();
		}
		for(int i=0;i<6;i++) {
			if (tablero[pos-1][i]==' ') {
				tablero[pos-1][i]='X';
				recrearPartida(recrearPartida);
				break;
			}
		}
		dibujaTablero();
	}
	public static void mueveJugador2(int pos, int [][][] recrearPartida) {
		while (movimientoValido(pos-1)==false) {
			System.out.println("Introduce un movimiento válido!");
			dibujaTablero();
			EsUnNumero();
			pos=in.nextInt();
		}
		for(int i=0;i<6;i++) {
			if (tablero[pos-1][i]==' ') {
				tablero[pos-1][i]='O';
				recrearPartida(recrearPartida);
				break;
			}
		}
		dibujaTablero();
	}
	public static boolean movimientoValido(int pos) {
		if(pos<0||pos>6||tablero[pos][5]!=' ') {
			return false;
		}
		return true;
	}
	public static void EsUnNumero() {
		while (!in.hasNextInt()) {
			System.out.println("Introduzca un numero válido: ");
			in.next();
		}
	}
	public static void opciones() {
		System.out.println("1. Jugar una partida\n"
				+ "2. Mostrar estadísticas\n"
				+ "3. Recrear última partida\n"
				+ "4. Salir\n"
				+ "Seleccione una opción:");
	}
	public static int opcionValida(int op) {
		while (op!=1&&op!=2&&op!=3&&op!=4) {
			System.out.println("Introduce una opción válida!");
			EsUnNumero();
			op=in.nextInt();
		}
		return op;
	}
	public static void recrearPartida(int [][][] recrearPartida) {
		int cont=0;
		for(int i=0;i<7;i++) {
			for(int j=0;j<6;j++) {
				if (tablero[i][j]=='X'||tablero[i][j]=='O') {
					cont++;
				}
			}
		}
		for(int i=0;i<7;i++) {
			for(int j=0;j<6;j++) {
				recrearPartida[cont-1][i][j]=tablero[i][j];
			}
		}
	}
	public static void dibujaTableroRecreado(int i,int [][][] recrearPartida) {
		System.out.println();
		System.out.println("Turno "+(i+1)+":");
		System.out.println("-----------------------------");
		System.out.println("| "+(char)recrearPartida[i][0][5]+" | "+(char)recrearPartida[i][1][5]+" | "+(char)recrearPartida[i][2][5]+" | "+(char)recrearPartida[i][3][5]+" | "+(char)recrearPartida[i][4][5]+" | "+(char)recrearPartida[i][5][5]+" | "+(char)recrearPartida[i][6][5]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+(char)recrearPartida[i][0][4]+" | "+(char)recrearPartida[i][1][4]+" | "+(char)recrearPartida[i][2][4]+" | "+(char)recrearPartida[i][3][4]+" | "+(char)recrearPartida[i][4][4]+" | "+(char)recrearPartida[i][5][4]+" | "+(char)recrearPartida[i][6][4]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+(char)recrearPartida[i][0][3]+" | "+(char)recrearPartida[i][1][3]+" | "+(char)recrearPartida[i][2][3]+" | "+(char)recrearPartida[i][3][3]+" | "+(char)recrearPartida[i][4][3]+" | "+(char)recrearPartida[i][5][3]+" | "+(char)recrearPartida[i][6][3]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+(char)recrearPartida[i][0][2]+" | "+(char)recrearPartida[i][1][2]+" | "+(char)recrearPartida[i][2][2]+" | "+(char)recrearPartida[i][3][2]+" | "+(char)recrearPartida[i][4][2]+" | "+tablero[5][2]+" | "+tablero[6][2]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+(char)recrearPartida[i][0][1]+" | "+(char)recrearPartida[i][1][1]+" | "+(char)recrearPartida[i][2][1]+" | "+(char)recrearPartida[i][3][1]+" | "+(char)recrearPartida[i][4][1]+" | "+(char)recrearPartida[i][5][1]+" | "+(char)recrearPartida[i][6][1]+" |");
		System.out.println("-----------------------------");
		System.out.println("| "+(char)recrearPartida[i][0][0]+" | "+(char)recrearPartida[i][1][0]+" | "+(char)recrearPartida[i][2][0]+" | "+(char)recrearPartida[i][3][0]+" | "+(char)recrearPartida[i][4][0]+" | "+(char)recrearPartida[i][5][0]+" | "+(char)recrearPartida[i][6][0]+" |");
		System.out.println("-----------------------------");
	}
}
