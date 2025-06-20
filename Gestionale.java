import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Gestionale {
    private static HashMap<String, ArrayList<Integer>> RegistroStudentiVoti = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continua = true;

        while (continua) {
            System.out.println("MENU GESTIONALE");
            System.out.println("1. AGGIUNGI STUDENTE");
            System.out.println("2. MODIFICA STUDENTE O VOTO");
            System.out.println("3. ELIMINA STUDENTE");
            System.out.println("4. VISUALIZZA STUDENTI");
            System.out.println("5. ESCI");
            System.out.println("SCEGLI UNA OPZIONE: ");

            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1 -> aggiungiStudente();
                case 2 -> modificaStudente();
                case 3 -> eliminaStudente();
                case 4 -> visualizzaStudenti();
                case 5 -> {
                    continua = false;
                    System.out.println("Arrivederci!");
                }
                default -> System.out.println("Opzione non valida!");
            }     
        }
    }
    private static void aggiungiStudente() {
        
    }

    private static void modificaStudente() {
        
    }

    private static void eliminaStudente() {
        
    }

    private static void visualizzaStudenti() {
        
    }

    private static ArrayList<Integer> inserisciVoti() {
        return null;
    }

    private static double calcolaMedia(ArrayList<Integer> voti) {
        return 0;
    }
}
