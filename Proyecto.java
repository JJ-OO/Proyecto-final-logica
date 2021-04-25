public class Proyecto {

	public static int aleatorio(n){
		return Math.floor(Math.random()*n)+1;
	}

	public static void menu(){
		int opc;
		do{


		System.out.println("Ingrese una opción así");
		System.out.println("1. sumar");
		System.out.println("2. restar");
		System.out.println("3. Multiplicar");
		System.out.println("4. Salir");
		opc = 3;
	}while(opc!=4)

	System.out.println(aleatorio(4));

	}
}