package com.mycompany.election;

import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import com.opencsv.*;
import java.io.File;

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

            switch (choix) {
                case 1:
                    // Initialisation
                    
                    try {
                        File file = new File("Election.csv");
                        FileReader filereader = new FileReader(file);
                        CSVReader csvReader = new CSVReader(filereader);
                        
                        List<String[]> allData = csvReader.readAll();
                        
                        allData.remove(0);
                        Electeur[] electeurs = new Electeur[allData.size()-1];
                        for (int j=0;j<allData.size()-1; j++) {
                            String name = allData.get(j)[0];
                            Double[] opinions = new Double[allData.get(j).length-1];
                            for(int i=1;i<allData.get(j).length;i++){
                                opinions[i-1]=Double.valueOf(allData.get(j)[i]);
                            }
                            Electeur electeur = new Electeur(name, null,opinions);
                            electeurs[j]=electeur;
                        }
                        simulation.setElecteurs(electeurs);
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("Une erreur est survenue");
                    }   break;
                case 2:
                    // Choisir un scrutin
                    System.out.println("Taper 1 pour : Majoritaire à un tour");
                    System.out.println("Taper 2 pour : Majoritaire à deux tours");
                    System.out.println("Taper 3 pour : Approbation");
                    System.out.println("Taper 4 pour : Alternatif");
                    System.out.println("Taper 5 pour : Borda");
                    choix = scanner.nextInt();
                    Scrutin scrutin = null;
                    switch (choix) {
                        case 1:
                            scrutin = new Majoritaire1t();
                            break;
                        case 2:
                            scrutin = new Majoritaire2t();
                            break;
                        case 3:
                            scrutin = new Approbation();
                            break;
                        case 4:
                            scrutin = new Alternatif();
                            break;
                        case 5:
                            scrutin = new Borda();
                            break;
                        default:
                            System.out.println("Oups, veuillez entrer une valeur correcte");
                            break;
                    }   simulation.setScrutin(scrutin);
                    break;
            // Effectuer une simulation
                case 3:
                    break;
                case 4:
                    // Quitter
                    System.out.println("Au revoir");
                    continuer = false;
                    break;
                default:
                    System.out.println("Oups, veuillez entrer une valeur correcte");
                    break;
            }

        }

        scanner.close();

    }
}
