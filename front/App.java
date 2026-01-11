import java.io.*;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        System.out.println("JAVA: Démarrage de l'interface...");

        Path inputFile = Paths.get("data/input.txt");
        Path outputFile = Paths.get("data/output.txt");
        
        // 1. JAVA ÉCRIT LA DEMANDE
        try {
            String demande = "ELON MUSK";
            Files.writeString(inputFile, demande);
            System.out.println("JAVA: Demande écrite dans input.txt -> " + demande);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

// 2. JAVA LANCE LE COBOL
        try {
            System.out.println("JAVA: Lancement du module COBOL...");
            
            File dossierBackend = new File("backend");
            File fichierExe = new File(dossierBackend, "traitement.exe");
            
            if (!fichierExe.exists()) {
                System.out.println("ERREUR CRITIQUE: Le fichier " + fichierExe.getAbsolutePath() + " n'existe pas !");
                return; // On arrête tout
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

            boolean finished = process.waitFor(5, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("JAVA: COBOL a terminé son travail.");
            } else {
                System.out.println("JAVA: Timeout ! COBOL a planté.");
                process.destroy();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 3. JAVA LIT LA RÉPONSE
        try {
            if (Files.exists(outputFile)) {
                String reponse = Files.readString(outputFile);
                System.out.println("JAVA: Réponse lue dans output.txt -> " + reponse);
            } else {
                System.out.println("JAVA: Erreur, pas de fichier de réponse !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}