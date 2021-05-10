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
			//menu
		System.out.print("	Entrada$:");
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
		String letra = "";

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
						letra = letra_cancion.toString();
						letra = letra.replace(";", " ");
						letra = letra.replace("--", "  ");

						imprimir("Canción: ");
						imprimir("-------- " + info_canciones[indice_cancion][ConsoleData.NOMBRE_CANCION] + " - " + info_canciones[indice_cancion][ConsoleData.AUTOR_CANCION] + " ---------");
						imprimir(letra);

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
						imprimir("Nombre: "+info_canciones[indice_cancion][ConsoleData.NOMBRE_CANCION]);
						imprimir("Autor: "+info_canciones[indice_cancion][ConsoleData.AUTOR_CANCION]);
						imprimir("Album: "+info_canciones[indice_cancion][ConsoleData.ALBUM]);
						imprimir("Año: "+info_canciones[indice_cancion][ConsoleData.FECHA]);
						imprimir("Archivo: "+info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);

						break;
					} 

					case "!HELP": {
						imprimir("----Detalles de los comandos-----");
						imprimir("---!PLAY, reproduce la cancion seleccionada, recuerde que esta no tiene voz");
						imprimir("---!STOP, detiene la cancion que se está reproduciendo");
						imprimir("---!AGAIN, vuelve a reproducir la cancion anterior y/o vuelve a empezar desde el inicio");
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
					case "!NP": {

						if (indice_cancion==900) {
							
							imprimir("No has reproducido ninguna canción :(, ingresa '!PLAY' para iniciar!");
						
						}else{

							System.out.println();
							imprimir("Actualmente estás escuchando ");
							System.out.println();
							imprimir("Nombre: "+info_canciones[indice_cancion][ConsoleData.NOMBRE_CANCION]);
							imprimir("Autor: "+info_canciones[indice_cancion][ConsoleData.AUTOR_CANCION]);
							imprimir("Album: "+info_canciones[indice_cancion][ConsoleData.ALBUM]);
							imprimir("Año: "+info_canciones[indice_cancion][ConsoleData.FECHA]);
							imprimir("Archivo: "+info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);

						}
						break;
					}
					case "!CL": {
						
						if (indice_cancion==900) {

							imprimir("No estas reproduciendo ninguna cancion :( usa el comando '!play' para empezar!");
						
						} else {

							inicio_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.INICIO_CANCION]);
							fin_letra = ConsoleInput.stringToInt(info_canciones[indice_cancion][ConsoleData.FIN_CANCION]);
							
							letra_cancion = obtenerLetraCancion(inicio_letra,fin_letra,canciones);
							letra = letra_cancion.toString();
							letra = letra.replace(";", " ");
							letra = letra.replace("--", "  ");

							imprimir("Canción: ");
							imprimir("-------- " + info_canciones[indice_cancion][ConsoleData.NOMBRE_CANCION] + " - " + info_canciones[indice_cancion][ConsoleData.AUTOR_CANCION] + " ---------");
							imprimir(letra);
						}
						break;
					}
					case "!AGAIN": {

						if (indice_cancion==900) {
							imprimir("No has reproducido ninguna cancion :( usa el comando '!play' para empezar!");
						}else{
							audio.detener();
							imprimir("Tu última canción fue: "+info_canciones[indice_cancion][ConsoleData.NOMBRE_CANCION] + " - " + info_canciones[indice_cancion][ConsoleData.AUTOR_CANCION]);
							imprimir("Disfrutala de nuevo!");
							audio.seleccionarCancion(info_canciones[indice_cancion][ConsoleData.RUTA_CANCION]);
							audio.reproducir(); 			
						}
						break;
					}
					case "!EXIT": {
						System.out.println("Espero que nos encontremos pronto, hasta luego!");
						salida = 1; 
						break;
					}
					default:
						imprimir("Esto no es un comando, usa !HELP para tener una lista detallada de ellos!");
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