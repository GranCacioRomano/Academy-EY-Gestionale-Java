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
        System.out.println("Inserisci il nome dello studente: ");
        String nome = scanner.next();

        if (RegistroStudentiVoti.containsKey(nome)) {
            System.out.println("Lo studente " + nome + " è già presente nel registro!");
        } else {
            System.out.println("Vuoi inserire subito i voti di " + nome + "? (s/n)");
            scanner.nextLine();
            String risposta = scanner.nextLine().toLowerCase();

            ArrayList<Integer> voti;

            if (risposta.equals("s")) {
                voti = inserisciVoti();
            } else {
                voti = new ArrayList<>();
            }
            RegistroStudentiVoti.put(nome, voti);
            System.out.println("Lo studente " + nome + " è stato aggiunto al registro!");
        }
    }

    private static void modificaStudente() {


    }

    private static void eliminaStudente() {

    }

    private static void visualizzaStudenti() {
        if (RegistroStudentiVoti.isEmpty()) {
        System.out.println("Nessuno studente registrato.");
        return;
        }
        System.out.println("\n--- Elenco Studenti ---");
        for (String nome : RegistroStudentiVoti.keySet()) {
        ArrayList<Integer> voti = RegistroStudentiVoti.get(nome);
        System.out.printf("%s - Media voti: %.2f%n", nome, calcolaMedia(voti));
        }
    }

    private static ArrayList<Integer> inserisciVoti() { // concatenare gli if.
        ArrayList<Integer> voti = new ArrayList<>();
        while (true) {
            System.out.println("Inserisci un voto (0 per terminare): ");
            int voto = scanner.nextInt();
            if (voto == 0) {
                break;
            }
            if (voto > 0 && voto < 11) {
                voti.add(voto);
            } else {
                System.out.println("Voto non valido!");
            }
        }
        return voti;
    }

    private static double calcolaMedia(ArrayList<Integer> voti) {
        if (voti.isEmpty()) {
            return 0;
        }
        int somma = 0;
        for (int voto : voti) {
            somma += voto;
        }
        return (double) somma / voti.size();
    }
}
