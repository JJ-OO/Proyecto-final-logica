import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
import java.util.Scanner;


public class Programa{

	public static String convertirUnicode(String letra,String cadena){
		
		StringBuilder str = new StringBuilder(cadena);
		int indice = -1;
		char caracter = 0;
		indice = str.lastIndexOf(letra);
		
		if(indice>=0)
		{
			switch(letra){
				case "Á": caracter = '\u00C1';
						  break;
				case "É": caracter = '\u00C9';
						  break;
				case "Í": caracter = '\u00CD';
						  break;
				case "Ó": caracter = '\u00D3';
						  break;
				case "Ú": caracter = '\u00DA';
						  break;
				case "á": caracter = '\u00E1';
					      break;
				case "é": caracter = '\u00E9';
					      break;
				case "í": caracter = '\u00ED';
					      break;
		        case "ó": caracter = '\u00F3';
					      break;
			    case "ú": caracter = '\u00FA';
					      break;
				case "ñ": caracter = '\u00F1';
					      break;
			}
			// System.out.println("\\u" + Integer.toHexString('÷' | 0x10000).substring(1));
			str.replace(indice,indice+1,""+caracter);
		}

		return str.toString();
	}

	public static void imprimir(String cadena)
	{
		String str; 
		str = convertirUnicode("Á",cadena);
		str = convertirUnicode("É",cadena);
		str = convertirUnicode("Í",cadena);
		str = convertirUnicode("Ó",cadena);
		str = convertirUnicode("Ú",cadena);
		str = convertirUnicode("á",str);
		str = convertirUnicode("é",str);
		str = convertirUnicode("í",str);
		str = convertirUnicode("ó",str);
		str = convertirUnicode("ú",str);
		str = convertirUnicode("ñ",str);

		System.out.println(str);
	}

	public static StringBuilder obtenerLetraCancion(int inicio,int fin, String[]data)
	{
		StringBuilder str = new StringBuilder();

		for(int i = inicio; i<=fin; i++)
		{
			str.append(data[i]+"\n");
		}

		return str;
	}

	public static void menu(){
		System.out.println("	 __________________________________________________________________");
		System.out.println("	|                              _     _                             |");
		System.out.println("	|                             ( \\---/ )                            |");
		System.out.println("	|                              ) . . (                             |");
		System.out.println("	|________________________,--._(___Y___)_,--.____________________hjw|");
		System.out.println("	|                        `--'           `--'                       |");
		System.out.println("	|		                  _   _  __                        |");
		System.out.println("	|		                 | | (_)/ _|                       |");
		System.out.println("	|		  ___ _ __   ___ | |_ _| |_ _   _                  |");
		System.out.println("	|		 / __| '_ \\ / _ \\| __| |  _| | | |                 |");
		System.out.println("	|		 \\__ \\ |_) | (_) | |_| | | | |_| |                 |");
		System.out.println("	|		 |___/ .__/ \\___/ \\__|_|_|  \\__, |                 |");
		System.out.println("	|		     | |                     __/ |                 |");
		System.out.println("	|		     |_|                    |___/                  |");
				  imprimir("	|                                                          	   |");
				  imprimir("	|                    Ingrese una opción así:		 	   |");
				  imprimir("	|                    1. Buscar canción			 	   |");
				  imprimir("	|                    2. Reproducir canción			   |");
				  imprimir("	|                    3. Mostrar Letra				   |");
				  imprimir("	|                    4. Detener Canción				   |");
				  imprimir("	|                    5. Imprimir lista de Canciones		   |");
				  imprimir("	|                    6. Salir					   |");
		System.out.println("	|__________________________________________________________________|");
		System.out.print("	Spotify$:");
	}

	public static void main(String[] args) {
		
		//AnsiConsole.systemInstall();
		
		Audio audio = new Audio();
		String centinela = "";
		int salida = 0;	
		int indice_cancion = 900;
		int inicio_letra = 0, fin_letra = 0;
		String [] canciones;
		String [][] info_canciones;
		StringBuilder letra_cancion;

		canciones = ConsoleFile.readBigFile("recursos/letras.csv");
		info_canciones = ConsoleData.dataList(canciones);

		try{
				//TODO: Ojo falta validar la entrada de datos
				//TODO: Recuerde usar el helper ConsoleInput y validar
    		do{	
				System.out.println();
				menu();

       			centinela = ConsoleInput.getString();

				switch(centinela) {
					case "!PLAY": {
						imprimir("Ingrese indice de la cancion, entre 0 y "+(info_canciones.length-1));
						indice_cancion = ConsoleInput.getInt();
						audio.seleccionarCancion(info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);
						audio.reproducir(); 
						break;
					} 

					case "!LYRICS": {

						imprimir("Ingrese indice de la cancion, entre 0 y "+(info_canciones.length-1));
						indice_cancion = ConsoleInput.getInt();

						inicio_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.INICIO_CANCION]);
						fin_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.FIN_CANCION]);
						
						letra_cancion = obtenerLetraCancion(inicio_letra,fin_letra,canciones);

						imprimir(letra_cancion.toString());	

						break;
					}

					case "!STOP": {
						audio.detener();
						break;
					} 

					case "!INFO": {
						imprimir("Ingrese indice de la cancion, entre 0 y "+(info_canciones.length-1));
						indice_cancion = ConsoleInput.getInt();

						inicio_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.INICIO_CANCION]);
						fin_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.FIN_CANCION]);

						System.out.println();
						imprimir("Inicio letra "+inicio_letra);
						imprimir("Fin letra "+fin_letra);
						imprimir("Nombre: "+info_canciones[indice_cancion][ConsoleData.NOMBRE_CANCION]);
						imprimir("Autor: "+info_canciones[indice_cancion][ConsoleData.AUTOR_CANCION]);
						imprimir("Archivo: "+info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);

						imprimir("Primera estrofa: "+canciones[inicio_letra]);
						imprimir("Última estrofa: "+canciones[fin_letra]);
						} break;

					case "!HELP": {
						imprimir("----Detalles de los comandos-----");
						imprimir("---!PLAY, reproduce la cancion seleccionada, recuerde que esta no tiene voz");
						imprimir("---!STOP, detiene la cancion que se está reproduciendo");
						imprimir("---!AGAIN, vuelve a reproducir la cancion anterior");
						imprimir("---!RANDOM, reproduce un segmento aleatorio de la canción");   
						imprimir("---!NP,(Now Playing) imprime la informacion de la cancion que se está reproduciendo junto a su minuto");
						imprimir("---!LYRICS, muestra la letra de una canción seleccionada");
						imprimir("---!CL, (Current Lyrics) muesta la letra de la canción que se está reproduciendo");
						imprimir("---!INFO, muestra la informacion de la canción");
						imprimir("---!LIST, muestra la lista de canciones");
						imprimir("---!EXIT, sales de *inserte el nombre del bot* :(");
						imprimir("---!HELP, imprime una lista de los comandos existentes");
						break;
					}  
					case "!CL": {
						
						if (indice_cancion==900) {
							imprimir("No estas reproduciendo ninguna cancion :( usa el comando '!play' para empezar!");
						
						} else {

							inicio_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.INICIO_CANCION]);
							fin_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.FIN_CANCION]);
							letra_cancion = obtenerLetraCancion(inicio_letra,fin_letra,canciones);

							imprimir(letra_cancion.toString());	
						}
						break;
					}
					case "!EXIT": {
						System.out.println("Adios!");
						salida = 1; 
						break;
					}
					default:
						imprimir("Eso no es un comando");
				}
			}while(salida != 1);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			audio.detener();
		}
	}
}