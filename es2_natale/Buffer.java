package es2_natale;

public class Buffer {

	private int num;
	private boolean pieno = false; 
	
	public synchronized void inserisci(int n) throws InterruptedException {
// se pieno = true, il thread produttore rilascia la risorsa con la wait()
		while(pieno) {
			try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } 
		}
		
// se pieno = false, inserisco il numero nel buffer e risveglio i thread in attesa 
		num = n; 
		pieno=true; 
		System.out.println("Inserito: " + num);
        notifyAll();
		
	}	
	
	public synchronized int consuma(boolean consumatore15) throws InterruptedException {

// se pieno = true, il thread produttore rilascia la risorsa con la wait()
				while(pieno) {
					pieno = false;

// gestisco i due consumatori con un boleano settato true al momento che creo l'istanza del consumatore nel main 
					if((num>1 || num<5) && consumatore15) {
						System.out.println("Il primo consumatore ha consumato: " + num);
						notifyAll(); 
						return num; 
					} else {
						System.out.println("Il secondo consumatore ha consumato: " + num);
						notifyAll(); 
						return num;
					}
				}
				wait();
				return 0; 	
			}	
}
