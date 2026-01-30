import java.io.*;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        System.out.println("JAVA: Démarrage de l'interface...");

        Path inputFile = Paths.get("data/input.txt");
        Path outputFile = Paths.get("backend/reponse.txt");
        
        try {
            if (inputFile.getParent() != null) {
                Files.createDirectories(inputFile.getParent());
            }

            String demande = "DEPOT;12345;500.00\n";
            
            Files.writeString(inputFile, demande);
            System.out.println("JAVA: Demande envoyée -> " + demande.trim());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            System.out.println("JAVA: Lancement du module COBOL...");
            
            Files.deleteIfExists(outputFile);

            File dossierBackend = new File("backend");
            File fichierExe = new File(dossierBackend, "traitement.exe");
            
            if (!fichierExe.exists()) {
                System.out.println("ERREUR: Fichier introuvable -> " + fichierExe.getAbsolutePath());
                return;
            }

            ProcessBuilder pb = new ProcessBuilder(fichierExe.getAbsolutePath());
            pb.directory(dossierBackend); 
            pb.redirectErrorStream(true); 

            Process process = pb.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   [LOG COBOL] " + line);
            }

            process.waitFor(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (Files.exists(outputFile)) {
                String reponse = Files.readString(outputFile);
                System.out.println("JAVA: Réponse lue -> " + reponse);
            } else {
                System.out.println("JAVA: Erreur, pas de fichier de réponse généré !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}