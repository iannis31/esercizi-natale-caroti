package es2_natale;

public class Consumatore implements Runnable{

	
	private final Buffer buffer;
    private final boolean consumatore15;

    public Consumatore(Buffer buffer, boolean consumatore15) {
        this.buffer = buffer;
        this.consumatore15 = consumatore15;
    }

    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 while (true) {
	            try {
					buffer.consuma(consumatore15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	        }
	}

}
