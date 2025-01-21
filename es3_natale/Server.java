package es3_natale; // Il pacchetto che contiene la classe.

import java.io.*; // Importa le classi per le operazioni di input/output.
import java.net.*; // Importa le classi per la gestione delle connessioni di rete.
import java.util.ArrayList; // Importa la classe per gestire le liste.
import java.util.List; // Importa l'interfaccia List.

public class Server {

    public static void main(String[] args) {
        // Avvia il server e lo mette in ascolto.
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            // Messaggio per indicare che il server è in ascolto sulla porta 12345.
            System.out.println("Server in ascolto sulla porta 12345...");

            while (true) {
                // Accetta una connessione da un client.
                Socket clientSocket = serverSocket.accept();

                // Stampa l'indirizzo del client connesso.
                System.out.println("Nuovo client connesso: " + clientSocket.getInetAddress());

                // Crea un thread per gestire il client, utilizzando la classe ClientHandler.
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            // Stampa dello stack trace in caso di eccezione.
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket; // Socket che rappresenta la connessione con il client.

    public ClientHandler(Socket clientSocket) {
        // Salva il socket del client passato nel costruttore.
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // Gestisce la comunicazione con il client.
        try (
            // Oggetto per leggere i dati inviati dal client.
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Oggetto per inviare dati al client.
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            List<String> receivedStrings = new ArrayList<>(); // Lista per memorizzare le stringhe ricevute dal client.
            String inputLine;

            // Ciclo per ricevere le stringhe dal client.
            while ((inputLine = in.readLine()) != null) {
                // Se il client invia "FINE-INVIO", il ciclo si interrompe.
                if ("FINE-INVIO".equalsIgnoreCase(inputLine)) {
                    break;
                }

                // Stampa la stringa ricevuta dal client.
                System.out.println("Ricevuto: " + inputLine);

                // Trasforma la stringa in maiuscolo e la aggiunge alla lista.
                receivedStrings.add(inputLine.toUpperCase());
            }

            // Ciclo per inviare le stringhe trasformate al client.
            for (String str : receivedStrings) {
                out.println(str); // Invia la stringa al client.
            }

            // Messaggio che indica la fine della trasmissione.
            System.out.println("Trasmissione al client completata.");
        } catch (IOException e) {
            // Stampa dello stack trace in caso di eccezione.
            e.printStackTrace();
        } finally {
            // Blocca che viene eseguito sempre per chiudere il socket del client.
            try {
                clientSocket.close(); // Chiude il socket.
            } catch (IOException e) {
                e.printStackTrace(); // Stampa eventuali errori durante la chiusura.
            }
        }
    }
}
