package es2_natale;
import java.util.Random;


public class Produttore implements Runnable{

	private Buffer b; 
	private Random random = new Random();
	
	
	public Produttore(Buffer buffer) {
	        this.b = buffer;
	    }
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			int num = random.nextInt(10) + 1;
			try {
				b.inserisci(num);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
            
	}

}
