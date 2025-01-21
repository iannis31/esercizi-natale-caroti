package es3_natale; // Il pacchetto che contiene la classe.

import java.io.*; // Importa le classi per le operazioni di input/output.
import java.net.*; // Importa le classi per la gestione delle connessioni di rete.

public class Client {

    public static void main(String[] args) {
        // Blocco principale per l'avvio del client.
        try (
            // Creazione di un socket che si connette al server in ascolto su localhost (indirizzo locale) alla porta 12345.
            Socket socket = new Socket("localhost", 12345);

            // Creazione di un oggetto BufferedReader per leggere i dati inviati dal server.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Creazione di un oggetto PrintWriter per inviare dati al server.
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Creazione di un oggetto BufferedReader per leggere l'input dell'utente dalla console.
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            // Messaggio per l'utente che indica che il client è connesso e pronto a inviare dati.
            System.out.println("Connesso al server. Inizia a inviare stringhe. Digita 'FINE-INVIO' per terminare.");

            String inputLine;
            // Ciclo per leggere l'input dell'utente e inviarlo al server.
            while ((inputLine = userInput.readLine()) != null) {
                // Invio della stringa al server.
                out.println(inputLine);

                // Se l'utente digita "FINE-INVIO", il ciclo si interrompe.
                if ("FINE-INVIO".equalsIgnoreCase(inputLine)) {
                    break;
                }
            }

            // Messaggio per indicare che il client sta ricevendo le risposte dal server.
            System.out.println("Risposte dal server:");

            String response;
            // Ciclo per leggere e stampare le risposte inviate dal server.
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException e) {
            // Stampa dello stack trace in caso di eccezione.
            e.printStackTrace();
        }
    }
}
