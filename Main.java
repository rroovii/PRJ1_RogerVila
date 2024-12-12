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
	
    static void validaUsuari(){
        System.out.print("Entra el nom d'usuari: ");
        Scanner sc = new Scanner(System.in);
        String usuari = sc.nextLine();

        cercaUsuari(usuari);
    }

    static void cercaUsuari (String usuari){
        try {
            File myObj = new File("DADES/USUARIS.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                if (data.contains(usuari)){
                    if(data.contains("Gerent")){
                        menuOpcionsGerent();
                        
                    } else {
                        menuOpcionsEmpleat();
                    }
                }
            }
            myReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
	
    static void menuOpcionsGerent(){
        System.out.println("Benvingut al menú de Gerent");
        System.out.println();
        System.out.println("1. GESTIONAR PERSONAL");
        System.out.println("2. CONSULTAR MENU");
        System.out.println("3. GENERAR INFORME");
        System.out.println("4. GESTIONAR COMANDA");

        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt();

        switch(opcio) {
            case 1:
                break;
            case 2:
                consultarMenu();
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Has sortit del programa");
                break;
        }
    }
	
    static void menuOpcionsEmpleat(){
        System.out.println("Benvingut al menú de Empleat");
        System.out.println();
        System.out.println("1. CONSULTAR MENU");
        System.out.println("2. ACTUALITZAR ESTOC");
        System.out.println("3. GESTIONAR COMANDA");

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
                gestionarComanda();
                break;
            case 4:
                break;
            default:
                System.out.println("Has sortit del programa");
                break;
        }
    }

    static void consultarMenu(){
        try{
			File myObj = new File("DADES/MENU.txt");
			Scanner myReader = new Scanner(myObj);
			
			while(myReader.hasNextLine()){
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

    static void menuEstoc(){
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
                return;
            default:
                System.out.println("Has sortit del programa");
                break;
        }
    }

    static void mostrarEstoc(){
        try{
			File myObj = new File("DADES/ESTOC.txt");
			Scanner myReader = new Scanner(myObj);
			
			while(myReader.hasNextLine()){
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
}

static void gestionarComanda() {
    // Crear una nova comanda
    Comanda comanda = new Comanda(1);

    // Mostrar el menú
    System.out.println("Menú de plats:");
    consultarMenu();

    // Afegir plats a la comanda
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

    // Calcular el preu total
    double preuTotal = 0;
    for (Plat plat : comanda.getPlats()) {
        preuTotal += plat.getPreu();
    }
    comanda.setPreuTotal(preuTotal);

    // Guardar la comanda en el fitxer DADES/COMANDA.txt
    try {
        PrintWriter writer = new PrintWriter(new FileWriter("DADES/COMANDA.txt", true));
        writer.println("Comanda " + comanda.getIdComanda());
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
}
