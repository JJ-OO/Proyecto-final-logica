public class Proyecto {
	{
	//1mero: necesitamos una funcion que nos de el nombre de todas las canciones
	//si son muchas canciones, necesitariamos una funcion que busque la cancion por el nombre, en general de ser el caso deberiamos cambiar parte de la estructura
	}
	public static void menu(){
		int cancion_elegida;
		String[] canciones = canciones_funcion();  //aca se deberia estar llamando a la funcion que 
												   //nos obtenga los nombres de las canciones en un array
		do{
			System.out.println("------------------------------");
			System.out.println("----Bienvenido a la rockola---");
			System.out.println("-------Elija una cancion------");
			System.out.println("------------------------------");

			for (int i = 0; i < canciones.length; i++){			//este for complementaria el menu dando la lista de canciones
				System.out.println(i + ". " + canciones[i]);
			}
		
		cancion_elegida = 0; //acá el se deberia leer el comando del usuario con la cancion (1,2,3...?)

		}while(cancion_elegida != canciones.length);

		System.out.println("------------------------------"); //Esto cierra el menu 

	}

	public static void main(String[] args) {
		menu();

		canciones[cancion_elegida].play()//esto NO es una funcion real, este es el nombre de la cancion que se va a reproducir
										 //el profesor nor dará una funcion para eso

		//también, necesitariamos TODO el apartado de la funcion de mostrar la letra.
		//Pero, de nuevo, eso nos lo iba a enseñar el profe. 

		//ESTE CODIGO NO COMPILA tiene funciones y cosas que generan multiples errores
		//y que basicamente faltan la mitad de las funciones
	}

}