package com.mycompany.election;

import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import com.opencsv.*;
import java.io.File;

public class App {
    
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans l'élection 2.0");
        
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
                    System.out.println("Taper 1 pour : Valeurs fournies");
                    System.out.println("Taper 2 pour : Valeurs aléatoires");
                    
                    choix = scanner.nextInt();
                    
                    Candidat[] candidats = null;
                    Electeur[] electeurs = null;
                    
                    switch (choix) {
                        case 1:
                            //Instruction
                            System.out.println("Renommer vos fichiers Electeurs.csv et Candidtas.csv");
                            System.out.println("Puis placer les à la racine du projet");
                            System.out.println("Taper 1 pour : Continuer");
                            choix = scanner.nextInt();
                            
                            electeurs = getElecteursFromCsv("Electeurs.csv");
                            candidats = getCandidatsFromCsv("Candidats.csv");
                            
                            break;
                        case 2:
                            //Valeurs aléatoires
                            System.out.println("Entrer un nombre de candidats");
                            int nbCandidats = scanner.nextInt();
                            System.out.println("Entrer un nombre d'électeurs");
                            int nbElecteurs = scanner.nextInt();
                            System.out.println("Entrer un nombre de critères");
                            int nbCriteres = scanner.nextInt();
                            
                            electeurs = getElecteursFromAleatoire(nbElecteurs, nbCriteres);
                            candidats = getCandidatsFromAleatoire(nbCandidats, nbCriteres);
                            
                            break;
                        default:
                            System.out.println("Oups, veuillez entrer une valeur correcte");
                            break;
                    }
                    
                    simulation.setCandidats(candidats);
                    simulation.setElecteurs(electeurs);
                    break;
                
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
                            System.out.println("Entrez un critère d'approbation : ");
                            int critere = scanner.nextInt();
                            scrutin = new Approbation(critere);
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
                    }
                    simulation.setScrutin(scrutin);
                    break;
                // Effectuer une simulation
                case 3:
                    
                    System.out.println("Taper 1 pour : Réaliser une élection");
                    System.out.println("Taper 2 pour : Réaliser un sondage");
                    System.out.println("Taper 3 pour : Réaliser une évolution");
                    choix = scanner.nextInt();
                    
                switch (choix) {
                    case 1:
                        simulation.election();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Oups, veuillez entrer une valeur correcte");
                        break;
                }
                    
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
    
    public static Electeur[] getElecteursFromCsv(String path) {
        Electeur[] electeurs = null;
        try {
            File file = new File(path);
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            
            List<String[]> allData = csvReader.readAll();
            
            allData.remove(0);
            electeurs = new Electeur[allData.size() - 1];
            for (int j = 0; j < allData.size() - 1; j++) {
                String name = allData.get(j)[0];
                Double[] opinions = new Double[allData.get(j).length - 1];
                for (int i = 1; i < allData.get(j).length; i++) {
                    opinions[i - 1] = Double.valueOf(allData.get(j)[i]);
                }
                Electeur electeur = new Electeur(name, null, opinions);
                electeurs[j] = electeur;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return electeurs;
    }
    
    public static Candidat[] getCandidatsFromCsv(String path) {
        Candidat[] candidats = null;
        try {
            File file = new File(path);
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            
            List<String[]> allData = csvReader.readAll();
            
            allData.remove(0);
            candidats = new Candidat[allData.size() - 1];
            for (int j = 0; j < allData.size() - 1; j++) {
                String name = allData.get(j)[0];
                Double[] opinions = new Double[allData.get(j).length - 1];
                for (int i = 1; i < allData.get(j).length; i++) {
                    opinions[i - 1] = Double.valueOf(allData.get(j)[i]);
                }
                Candidat candidat = new Candidat(name, null, opinions);
                candidats[j] = candidat;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return candidats;
    }
    
    public static Candidat[] getCandidatsFromAleatoire(int nbCandidats, int nbCriteres) {
            Candidat[] candidats = new Candidat[nbCandidats];
            for (int i = 0; i < nbCandidats; i++) {
                String name = "Candidat n°"+i;
                Double[] opinions = new Double[nbCriteres];
                for (int j = 0; i < nbCriteres; i++) {
                    opinions[j] = Math.random();
                }
                Candidat candidat = new Candidat(name, null, opinions);
                candidats[i] = candidat;
            }
        
        return candidats;
    }
    
    public static Electeur[] getElecteursFromAleatoire(int nbElecteurs, int nbCriteres) {
            Electeur[] electeurs = new Electeur[nbElecteurs];
            for (int i = 0; i < nbElecteurs; i++) {
                String name = "Electeur n°"+i;
                Double[] opinions = new Double[nbCriteres];
                for (int j = 0; i < nbCriteres; i++) {
                    opinions[j] = Math.random();
                }
                Electeur electeur = new Electeur(name, null, opinions);
                electeurs[i] = electeur;
            }
        
        return electeurs;
    }
    
}
