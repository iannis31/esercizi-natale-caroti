package es1_natale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ThreadCounter implements Runnable {

	private int n; 
	private Counter c; 
	private int valore;
	

    public ThreadCounter(Counter c, int n) {
        this.c = c;
        this.n = n;
    }
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random random = new Random();
        valore = random.nextInt(n / 2, n + 1);
        for (int i = 0; i < valore; i++) {
            c.next();
        }
	}
	
	public int getRandomValore() {
		return valore; 
	}

}

























