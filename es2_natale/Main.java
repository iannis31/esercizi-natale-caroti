package es2_natale;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Buffer buffer = new Buffer();

	        Thread prod = new Thread(new Produttore(buffer));
	        
// setto a true il secondo parametro del consumatore 1 per far consumare i valori da 1 a 5
	        Thread cons1 = new Thread(new Consumatore(buffer, true));
	        
// setto a false il secondo parametro del consumatore 1 per far consumare i valori da 6 a 10
	        Thread cons2 = new Thread(new Consumatore(buffer, false));

	        prod.start();
	        cons1.start();
	        cons2.start();
	}

}
