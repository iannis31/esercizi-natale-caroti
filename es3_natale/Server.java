package es3_natale;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
// server avviato e in ascolto sulla porta 12345
		try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server in ascolto sulla porta 12345...");

            while (true) {
            	
// una volta che il client si connette il server invia un messaggio
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuovo client connesso: " + clientSocket.getInetAddress());

// gestisce ogni client in modo indipendente con un thread separato che esegue la classe ClientHandler
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

// con il costruttore salvo il socket del client in un attributo
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            List<String> receivedStrings = new ArrayList<>();
            String inputLine;

// ricevo delle stringhe, se è FINE-INVIO allora inderrompo il ciclo 
            while ((inputLine = in.readLine()) != null) {
                if ("FINE-INVIO".equalsIgnoreCase(inputLine)) {
                    break;
                }
                System.out.println("Ricevuto: " + inputLine);
                receivedStrings.add(inputLine.toUpperCase());
            }

// invio delle stringhe dal sercer al client 
            for (String str : receivedStrings) {
                out.println(str);
            }
            System.out.println("Trasmissione al client completata.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

// chiudo il socket a comunicazione finita
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}






