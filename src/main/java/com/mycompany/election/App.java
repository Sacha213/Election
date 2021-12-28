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

            if (choix == 1) {
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
                        Float[] opinions = new Float[allData.get(j).length-1];
                        for(int i=1;i<allData.get(j).length;i++){
                            opinions[i-1]=Float.valueOf(allData.get(j)[i]);
                        }
                        Electeur electeur = new Electeur(name, null,opinions);
                        electeurs[j]=electeur;
                    }
                    simulation.setElecteurs(electeurs);
                } catch (Exception e) {
                    System.out.println(e);
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
