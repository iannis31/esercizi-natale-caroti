package es1_natale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Scanner scanner = new Scanner(System.in);

	        
	        System.out.print("Inserisci il numero di thread (M): ");
	        int M = scanner.nextInt();

	        System.out.print("Inserisci il valore massimo (N): ");
	        int N = scanner.nextInt();

	        
	        Counter counter = new Counter();

	        ThreadCounter[] threadcounter = new ThreadCounter[M]; 
	        for (int i = 0; i < M; i++) {
	            ThreadCounter threadCounter = new ThreadCounter(counter, N);
	            Thread thread = new Thread(threadCounter);
		        threadCounter.getRandomValore();
	            thread.start();
	        }
	        

	    }
	}


