package es3_natale;
import java.io.*;
import java.net.*;



public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

// client si connette alla porta del server 12345
		try (Socket socket = new Socket("localhost", 12345);
			
// leggo dal server
	             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	             
// output verso il server
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	            
				BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

	            System.out.println("Connesso al server. Inizia a inviare stringhe. Digita 'FINE-INVIO' per terminare.");

	            String inputLine;
	            while ((inputLine = userInput.readLine()) != null) {
	                out.println(inputLine);
	                if ("FINE-INVIO".equalsIgnoreCase(inputLine)) {
	                    break;
	                }
	            }

	            System.out.println("Risposte dal server:");
	            String response;
	            while ((response = in.readLine()) != null) {
	                System.out.println(response);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
}	
	