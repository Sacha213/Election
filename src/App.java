import javax.sound.sampled.SourceDataLine;

import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import com.opencsv.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenu dans l'élection 2.0");

        Simulation simulation = new Simulation();

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        int choix;

        while (continuer) {
            // Affichage du menu
            System.out.println("Taper 1 pour : Initialisation");
            System.out.println("Taper 2 pour : Choisir un scrutin");
            System.out.println("Taper 3 pour : Effectuer une simulation");
            System.out.println("Taper 4 pour : Quitter");

            choix = scanner.nextInt();

            if (choix == 1) {
                // Initialisation

                try {
                    FileReader filereader = new FileReader("Election.csv");
                    CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

                    List<String[]> allData = csvReader.readAll();

                    // print Data
                    for (String[] row : allData) {
                        for (String cell : row) {
                            System.out.print(cell + "\t");
                        }
                        System.out.println();
                    }
                } catch (Exception e) {
                    System.out.println("Une erreur est survenue");
                }

            } else if (choix == 2) {
                // Choisir un scrutin
                System.out.println("Taper 1 pour : Majoritaire à un tour");
                System.out.println("Taper 2 pour : Majoritaire à deux tours");
                System.out.println("Taper 3 pour : Approbation");
                System.out.println("Taper 4 pour : Alternatif");
                System.out.println("Taper 5 pour : Borda");

                choix = scanner.nextInt();

                Scrutin scrutin = null;

                if (choix==1) {
                    scrutin = new Majoritaire1t();
                    
                }
                else if(choix==2){
                    scrutin = new Majoritaire2t();

                }
                else if(choix==3){
                    scrutin = new Approbation();
                }
                else if(choix==4){
                    scrutin = new Alternatif();
                }
                else if(choix==5){
                    scrutin = new Borda();
                }
                else {
                    System.out.println("Oups, veuillez entrer une valeur correcte");
                }

                simulation.setScrutin(scrutin);

            } else if (choix == 3) {
                // Effectuer une simulation

            } else if (choix == 4) {
                // Quitter
                System.out.println("Au revoir");
                continuer = false;
            } else {
                System.out.println("Oups, veuillez entrer une valeur correcte");
            }

        }

        scanner.close();

    }
}
