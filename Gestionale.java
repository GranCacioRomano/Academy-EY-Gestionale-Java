import java.util.*;

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
                case 1 -> {
                    scanner.nextLine();
                    aggiungiStudente(scanner);
                }
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
    private static void aggiungiStudente(Scanner scanner) {

        System.out.println("Inserire il nome e cognome dello studente");
        String nome = scanner.nextLine();
        System.out.println("Quanti voti vuoi inserire?");
        int scelta = scanner.nextInt();
        ArrayList<Integer> voti = new ArrayList<>();
        
        if(scelta != 0 && scelta>0)
            voti = inserisciVoti(scanner, scelta);
        else
            RegistroStudentiVoti.put(nome, new ArrayList<>());

        RegistroStudentiVoti.put(nome, voti);

    }

    private static void modificaStudente() {
        
    }

    private static void eliminaStudente() {
        
    }

    private static void visualizzaStudenti() {
        
    }

    private static ArrayList<Integer> inserisciVoti(Scanner scanner, int scelta) {
        ArrayList<Integer> voti = new ArrayList<>(); 
        
        for (int i = 0; i<scelta; i++) {
            System.out.println("Inserisci un voto da 1 a 10");
            int voto = Integer.parseInt(scanner.nextLine());
            if (voto == -1) break;
            if (voto >= 1 && voto <= 10) 
                voti.add(scanner.nextInt());
            else System.out.println("Voto non valido");

        }

        return voti;
    }

    private static double calcolaMedia(ArrayList<Integer> voti) {
        return 0;
    }
}
