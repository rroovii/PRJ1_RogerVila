import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        validaUsuari();
    }
    
    static void validaUsuari() {
        System.out.println("1. INICIA SESSIÓ");
        System.out.println("2. Sortir");

        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();

        switch(opcio){
            case 1: 
                Scanner scanner = new Scanner(System.in);
                System.out.print("Entra el nom d'usuari: ");
                String usuari = scanner.nextLine();
                cercaUsuari(usuari);
                break;
            case 2:
                System.out.println("Sortint...");
                System.exit(0);
        }
        
    }

    static void cercaUsuari(String usuari) {
        try {
            File myObj = new File("DADES/USUARIS.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains(usuari)) {
                    String[] userData = data.split(",");
                    String rol = userData[2];
                    if (rol.equals("Gerent")) {
                        menuOpcionsGerent();
                    } else if (rol.equals("Empleat")) {
                        Empleat empleat = new Empleat(userData[0], userData[1], rol, userData[3]);
                        menuOpcionsEmpleat(empleat);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    static void menuOpcionsGerent() {
        System.out.println("Benvingut al menú de Gerent");
        System.out.println();
        System.out.println("1. GESTIONAR PERSONAL");
        System.out.println("2. CONSULTAR MENU");
        System.out.println("3. GENERAR INFORME");
        System.out.println("4. ACTUALITZAR ESTOC");
        System.out.println("5. Sortir");

        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();

        switch(opcio) {
            case 1:
                gestionarPersonal();
                break;
            case 2:
                consultarMenu();
                break;
            case 3:
                // Implementar generació d'informes
                break;
            case 4:
                menuEstoc();
                break;
            case 5:
                validaUsuari();
                break;
            default:
                System.out.println("Has sortit del programa");
                break;
        }
    }
    
    static void menuOpcionsEmpleat(Empleat empleat) {
        System.out.println("Benvingut al menú de Empleat");
        System.out.println();
        System.out.println("1. CONSULTAR MENU");
        System.out.println("2. ACTUALITZAR ESTOC");
        System.out.println("3. GESTIONAR COMANDA");
        System.out.println("4. Sortir");

        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();

        switch(opcio) {
            case 1:
                consultarMenu();
                break;
            case 2:
                menuEstoc();
                break;
            case 3:
                gestionarComanda(empleat);
                break;
            case 4: 
                validaUsuari();
                break;
            default: 
                System.out.println("Opció no vàlida");
        }
    }

    static void consultarMenu() {
        try {
            File myObj = new File("DADES/MENU.txt");
            Scanner myReader = new Scanner(myObj);
            
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                
                String regex = "[.]";
                String[] myArray = data.split(regex);
                double p = Double.parseDouble(myArray[1]);

                Plat objPlat = new Plat(myArray[0], p, myArray[2]);
                
                System.out.println(objPlat.toString());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer");
            e.printStackTrace();
        }
    }

    static void menuEstoc() {
        System.out.println("1. MOSTRAR ESTOC");
        System.out.println("2. AFEGIR ESTOC");
        System.out.println("3. Sortir");
        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();

        switch(opcio) {
            case 1:
                mostrarEstoc();
                break;
            case 2: 
                afegirEstoc();
                break;
            case 3: 
                validaUsuari();
                break;
            default: 
                System.out.println("Opció no vàlida");
                break;
        }
    }

    static void mostrarEstoc() {
        try {
            File myObj = new File("DADES/ESTOC.txt");
            Scanner myReader = new Scanner(myObj);
            
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                
                String regex = "[.]";
                String[] myArray = data.split(regex);
                int p = parseInt(myArray[1]);

                Ingredient objIngredient = new Ingredient(myArray[0], p);
                
                System.out.println(objIngredient.toString());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer");
            e.printStackTrace();
        }
        validaUsuari();
    }

    static void afegirEstoc() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Entra el nom de l'ingredient: ");
        String nomIngredient = sc.nextLine();
        
        System.out.print("Entra la quantitat: ");
        int quantitat = sc.nextInt();
        sc.nextLine();

        File estocFile = new File("DADES/ESTOC.txt");
        File tempFile = new File("DADES/ESTOC_temp.txt");
        
        boolean ingredientFound = false;
        
        try {
            Scanner myReader = new Scanner(estocFile);
            PrintWriter writer = new PrintWriter(tempFile);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String regex = "[.]";
                String[] myArray = data.split(regex);
                String nomActual = myArray[0];
                int quantitatActual = Integer.parseInt(myArray[1]);
                
                if (nomActual.equalsIgnoreCase(nomIngredient)) {
                    quantitatActual += quantitat; 
                    writer.println(nomActual + "." + quantitatActual); 
                    ingredientFound = true; 
                } else {
                    writer.println(data); 
                }
            }
            if (!ingredientFound) {
                writer.println(nomIngredient + "." + quantitat);
            }
            
            myReader.close();
            writer.close();
            
            if (!estocFile.delete()) {
                System.out.println("No s'ha pogut eliminar el fitxer original.");
                return;
            }
            if (!tempFile.renameTo(estocFile)) {
                System.out.println("No s'ha pogut renombrar el fitxer temporal.");
            }
            
            if (ingredientFound) {
                System.out.println("Ingredient actualitzat correctament.");
            } else {
                System.out.println("Ingredient afegit correctament.");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer d'estoc.");
            e.printStackTrace();
        }
        validaUsuari();
    }

    static void gestionarComanda(Empleat empleat) {
        Comanda comanda = new Comanda();

        System.out.println("Menú de plats:");
        consultarMenu();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Entra el nom del plat que vols afegir a la comanda: ");
            String nomPlat = sc.nextLine();
            Plat plat = buscarPlat(nomPlat);
            if (plat != null) {
                comanda.getPlats().add(plat);
                System.out.println("Plat afegit a la comanda!");
            } else {
                System.out.println("Plat no trobat!");
            }
            System.out.print("Vols afegir més plats? (s/n): ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                break;
            }
        }

        double preuTotal = 0;
        for (Plat plat : comanda.getPlats()) {
            preuTotal += plat.getPreu();
        }
        comanda.setPreuTotal(preuTotal);
        empleat.getComandes().add(comanda);

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("DADES/COMANDA.txt", true));
            writer.println("Comanda " + comanda.getIdComanda());
            writer.println("Assignada a: " + empleat.getNom());
            for (Plat plat : comanda.getPlats()) {
                writer.println(plat.getNom() + " - " + plat.getPreu() + "€");
            }
            writer.println("Preu total: " + comanda.getPreuTotal() + "€");
            writer.println();
            writer.close();
            System.out.println("Comanda guardada en DADES/COMANDA.txt");
        } catch (IOException e) {
            System.out.println("No s'ha pogut crear el fitxer");
            e.printStackTrace();
        }
        validaUsuari();
    }

    static Plat buscarPlat(String nomPlat) {
        try {
            File myObj = new File("DADES/MENU.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String regex = "[.]";
                String[] myArray = data.split(regex);
                String nomActual = myArray[0];
                if (nomActual.equalsIgnoreCase(nomPlat)) {
                    double preu = Double.parseDouble(myArray[1]);
                    String tipus = myArray[2];
                    return new Plat(nomActual, preu, tipus);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer");
            e.printStackTrace();
        }
        return null;
    }

    static void gestionarPersonal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Afegir personal");
        System.out.println("2. Eliminar personal");
        System.out.println("3. Mostrar personal");
        System.out.println("4. Sortir");

        int opcio = sc.nextInt();

        switch (opcio) {
            case 1:
                afegirPersonal();
                break;
            case 2:
                eliminarPersonal();
                break;
            case 3:
                mostrarPersonal();
                break;
            case 4:
                validaUsuari();
                break;
            default:
                System.out.println("Opció no vàlida");
        }
        
    }

    static void afegirPersonal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Afegir empleat");
        System.out.println("2. Afegir gerent");
        System.out.println("3. Sortir");

        int opcio = sc.nextInt();

        switch (opcio) {
            case 1:
                afegirEmpleat();
                break;
            case 2:
                afegirGerent();
                break;
            case 3:
                validaUsuari();
                break;
            default:
                System.out.println("Opció no vàlida");
        }
    }

    static void afegirEmpleat() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entra el nom de l'empleat: ");
        String nom = sc.nextLine();
        System.out.print("Entra la contrasenya: ");
        String contrasenya = sc.nextLine();
        System.out.print("Entra el torn: ");
        String torn = sc.nextLine();

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("DADES/USUARIS.txt", true));
            writer.println();
            writer.println(nom + "," + contrasenya + ",Empleat," + torn);
            writer.close();
            System.out.println("Empleat afegit correctament.");
        } catch (IOException e) {
            System.out.println("No s'ha pogut afegir l'empleat.");
            e.printStackTrace();
        }
        validaUsuari();
    }

    static void afegirGerent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entra el nom del gerent: ");
        String nom = sc.nextLine();
        System.out.print("Entra la contrasenya: ");
        String contrasenya = sc.nextLine();
        System.out.print("Entra les responsabilitats: ");
        String responsabilitats = sc.nextLine();

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("DADES/USUARIS.txt", true));
            writer.println();
            writer.println(nom + "," + contrasenya + ",Gerent," + responsabilitats);
            writer.close();
            System.out.println("Gerent afegit correctament.");
        } catch (IOException e) {
            System.out.println("No s'ha pogut afegir el gerent.");
            e.printStackTrace();
        }
        validaUsuari();
    }

    static void eliminarPersonal() {
        // Implementació per eliminar personal
    }

    static void mostrarPersonal() {
        try {
            File fitxerUsuaris = new File("DADES/USUARIS.txt");
            Scanner myReader = new Scanner(fitxerUsuaris);

            System.out.println("Llista de personal:");
            System.out.println("--------------------");

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!data.isEmpty()) {
                    String[] dadesUsuari = data.split(",");
                    String nom = dadesUsuari[0];
                    String rol = dadesUsuari[2];
                    System.out.println("Nom: " + nom + " | Rol: " + rol);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer d'usuaris.");
            e.printStackTrace();
        }  
        validaUsuari(); 
    }
}