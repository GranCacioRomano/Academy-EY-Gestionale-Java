import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "Falconetti98";

    public static void main(String[] args) {
        creaTabelle();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Aggiungi studente");
            System.out.println("2. Visualizza studenti");
            System.out.println("3. Esci");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> aggiungiStudente();
                case 2 -> visualizzaStudenti();
                case 3 -> {
                    System.out.println("Arrivederci!");
                    return;
                }
                default -> System.out.println("Scelta non valida.");
            }
        }
    }

    private static Connection connessione() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void creaTabelle() {
        String creaStudenti = """
            CREATE TABLE IF NOT EXISTS studenti (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nome VARCHAR(100) NOT NULL
            );
        """;

        String creaVoti = """
            CREATE TABLE IF NOT EXISTS voti (
                id INT AUTO_INCREMENT PRIMARY KEY,
                studente_id INT NOT NULL,
                voto INT NOT NULL,
                FOREIGN KEY (studente_id) REFERENCES studenti(id) ON DELETE CASCADE
            );
        """;

        try (Connection conn = connessione();
             Statement stmt = conn.createStatement()) {
            stmt.execute(creaStudenti);
            stmt.execute(creaVoti);
        } catch (SQLException e) {
            System.out.println("Errore creazione tabelle: " + e.getMessage());
        }
    }

    private static void aggiungiStudente() {
        System.out.print("Nome studente: ");
        String nome = scanner.nextLine();

        List<Integer> voti = new ArrayList<>();
        System.out.println("Inserisci voti (0 per terminare): ");
        while (true) {
            int voto = scanner.nextInt();
            if (voto == 0) break;
            if (voto >= 1 && voto <= 10) voti.add(voto);
            else System.out.println("Voto non valido.");
        }
        scanner.nextLine();

        try (Connection conn = connessione()) {
            String insertStudente = "INSERT INTO studenti (nome) VALUES (?)";
            PreparedStatement psStudente = conn.prepareStatement(insertStudente, Statement.RETURN_GENERATED_KEYS);
            psStudente.setString(1, nome);
            psStudente.executeUpdate();

            ResultSet rs = psStudente.getGeneratedKeys();
            if (rs.next()) {
                int studenteId = rs.getInt(1);

                String insertVoto = "INSERT INTO voti (studente_id, voto) VALUES (?, ?)";
                PreparedStatement psVoto = conn.prepareStatement(insertVoto);
                for (int voto : voti) {
                    psVoto.setInt(1, studenteId);
                    psVoto.setInt(2, voto);
                    psVoto.addBatch();
                }
                psVoto.executeBatch();
            }
            System.out.println("Studente aggiunto con successo.");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    private static void visualizzaStudenti() {
        String query = """
            SELECT s.nome, AVG(v.voto) AS media
            FROM studenti s
            LEFT JOIN voti v ON s.id = v.studente_id
            GROUP BY s.id, s.nome
            ORDER BY s.nome
        """;

        try (Connection conn = connessione();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n-- Elenco Studenti --");
            while (rs.next()) {
                String nome = rs.getString("nome");
                double media = rs.getDouble("media");
                System.out.printf("%s - Media voti: %.2f%n", nome, media);
            }
        } catch (SQLException e) {
            System.out.println("Errore nella visualizzazione: " + e.getMessage());
        }
    }
}

