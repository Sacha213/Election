package com.mycompany.election;

import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import com.opencsv.*;
import java.io.File;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans l'élection 2.0");

        Simulation simulation = new Simulation();

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        boolean wrongAnswer = true;
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
                    Candidat[] candidats = null;
                    Electeur[] electeurs = null;
                    while(wrongAnswer) {
                        System.out.println("Taper 1 pour : Valeurs fournies");
                        System.out.println("Taper 2 pour : Valeurs aléatoires");

                        choix = scanner.nextInt();

                        switch (choix) {
                            case 1:
                                //Instruction
                                System.out.println("Renommer vos fichiers Electeurs.csv et Candidats.csv, avec un délimiteur ','");
                                System.out.println("Puis placer les à la racine du projet");
                                System.out.println("Taper 1 pour : Continuer");
                                choix = scanner.nextInt();

                                electeurs = getElecteursFromCsv("Electeurs.csv");
                                candidats = getCandidatsFromCsv("Candidats.csv");
                                
                                wrongAnswer = false;
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
                                
                                wrongAnswer = false;
                                break;
                            default:
                                System.out.println("Oups, veuillez entrer une valeur correcte");
                                break;
                        }
                    }
                    wrongAnswer = true;
                    simulation.setCandidats(candidats);
                    simulation.setElecteurs(electeurs);
                    System.out.println("Vous avez chargé " + simulation.getElecteurs().length + " electeur(s) et " + simulation.getCandidats().length + " candidat(s).");
                    break;

                case 2:
                    // Choisir un scrutin
                    Scrutin scrutin = null;
                    while(wrongAnswer) {
                        System.out.println("Taper 1 pour : Majoritaire à un tour");
                        System.out.println("Taper 2 pour : Majoritaire à deux tours");
                        System.out.println("Taper 3 pour : Approbation");
                        System.out.println("Taper 4 pour : Alternatif");
                        System.out.println("Taper 5 pour : Borda");
                        choix = scanner.nextInt();
                        
                        switch (choix) {
                            case 1:
                                scrutin = new Majoritaire1t();
                                wrongAnswer = false;
                                break;
                            case 2:
                                scrutin = new Majoritaire2t();
                                wrongAnswer = false;
                                break;
                            case 3:
                                scrutin = new Approbation();
                                wrongAnswer = false;
                                break;
                            case 4:
                                scrutin = new Alternatif();
                                wrongAnswer = false;
                                break;
                            case 5:
                                scrutin = new Borda();
                                wrongAnswer = false;
                                break;
                            default:
                                System.out.println("Oups, veuillez entrer une valeur correcte");
                                break;
                        }
                    }
                    wrongAnswer = true;
                    simulation.setScrutin(scrutin);
                    break;
                // Effectuer une simulation
                case 3:
                    if(simulation.getScrutin()==null || simulation.getElecteurs()==null || simulation.getCandidats()==null){
                        System.out.println("Veuillez d'abord choisir un scrutin et initialiser les electeurs et candidats.");
                        break;
                    }
                    
                    while (wrongAnswer) {
                        System.out.println("Taper 1 pour : Réaliser une élection");
                        System.out.println("Taper 2 pour : Réaliser un sondage");
                        System.out.println("Taper 3 pour : Réaliser une évolution");
                        choix = scanner.nextInt();
                    
                        switch (choix) {
                            case 1:
                                List<Map.Entry<Candidat, Integer>> resultatElection = simulation.election();
                                simulation.getScrutin().afficherResultat(resultatElection, simulation.getElecteurs().length);
                                wrongAnswer = false;
                                break;
                            case 2:
                                List<Map.Entry<Candidat, Integer>> resultatSondage = simulation.sondage();
                                simulation.getScrutin().afficherResultat(resultatSondage, (int) (0.1 * simulation.getElecteurs().length));
                                wrongAnswer = false;
                                break;
                            case 3:
                                if(simulation.getScrutin().getClass() != Majoritaire1t.class){
                                    System.out.println("Le mode évolution est pour l'instant uniquement disponible pour ");
                                    System.out.println("le mode de scrutin majoritaire à 1 tour, veuiller attendre une prochaine");
                                    System.out.println("mise a jour de l'application Election 2.0");
                                    break;
                                }
                                
                                while(wrongAnswer) {
                                    System.out.println("Taper 1 pour : Evolution avec interaction socio-politique");
                                    System.out.println("Taper 2 pour : Evolution à partir d'un sondage 1");
                                    System.out.println("Taper 3 pour : Evolution à partir d'un sondage 2");
                                    System.out.println("Taper 4 pour : Evolution à partir d'un sondage 3");
                                    choix = scanner.nextInt();

                                    switch (choix) {
                                        case 1:
                                            simulation.evolution(new Interaction());
                                            wrongAnswer = false;
                                            break;
                                        case 2:
                                            simulation.evolution(new Sondage1());
                                            wrongAnswer = false;
                                            break;
                                        case 3:
                                            simulation.evolution(new Sondage2());
                                            wrongAnswer = false;
                                            break;
                                        case 4:
                                            simulation.evolution(new Sondage3());
                                            wrongAnswer = false;
                                            break;
                                        default:
                                            System.out.println("Oups, veuillez entrer une valeur correcte");
                                            break;
                                    }
                                }
                                break;
                            default:
                                System.out.println("Oups, veuillez entrer une valeur correcte");
                                break;
                        }
                    }
                    wrongAnswer = true;
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
            electeurs = new Electeur[allData.size()];
            for (int j = 0; j < allData.size(); j++) {
                String name = allData.get(j)[0];
                String firstname = allData.get(j)[1];
                Double[] opinions = new Double[allData.get(j).length - 2];
                for (int i = 2; i < allData.get(j).length; i++) {
                    opinions[i - 2] = Double.valueOf(allData.get(j)[i]);
                }
                Electeur electeur = new Electeur(firstname, name, opinions);
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
            candidats = new Candidat[allData.size()];
            for (int j = 0; j < allData.size(); j++) {
                String name = allData.get(j)[0];
                String firstname = allData.get(j)[1];
                Double[] opinions = new Double[allData.get(j).length - 2];
                for (int i = 2; i < allData.get(j).length; i++) {
                    opinions[i - 2] = Double.valueOf(allData.get(j)[i]);
                }
                Candidat candidat = new Candidat(firstname, name, opinions);
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
            String firstname = "Candidat";
            String name = "n°" + i;
            Double[] opinions = new Double[nbCriteres];
            for (int j = 0; j < nbCriteres; j++) {
                opinions[j] = Math.random();
            }
            Candidat candidat = new Candidat(name, firstname, opinions);
            candidats[i] = candidat;
        }

        return candidats;
    }

    public static Electeur[] getElecteursFromAleatoire(int nbElecteurs, int nbCriteres) {
        Electeur[] electeurs = new Electeur[nbElecteurs];
        for (int i = 0; i < nbElecteurs; i++) {
            String firstname = "Electeur";
            String name = "n°" + i;
            Double[] opinions = new Double[nbCriteres];
            for (int j = 0; j < nbCriteres; j++) {
                opinions[j] = Math.random();
            }
            Electeur electeur = new Electeur(name, firstname, opinions);
            electeurs[i] = electeur;
        }

        return electeurs;
    }

}
