package es1_natale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Counter {
	
	private int v; 
	
	public Counter(){
		this.v=0; 
	}
	
/* synchronized mi tutela dalla race-conditions, quindi se tolto da metodo/i condiviso/i, posso riscontrarla 
 * perchè l'accesso dei thread non è più sincronizzato
*/
	public /*synchronized*/ void next() {
	        v++;
	    }
	 
	 public /*synchronized*/ int getCount() {
	        return v;
	    }
}
