import java.util.ArrayList;

public class Comanda {
    private static int nextId = 1;
    private int idComanda;
    private ArrayList<Plat> plats = new ArrayList<>(); ;
    private double preuTotal;

    // Constructor
    public Comanda() {
        this.idComanda = nextId++;
        this.plats = new ArrayList<>();
        this.preuTotal = 0.0;
    }

    // Getters i Setters

    public int getIdComanda() {
        return this.idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public ArrayList<Plat> getPlats() {
        return this.plats;
    }

    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
    }

    public double getPreuTotal() {
        return this.preuTotal;
    }

    public void setPreuTotal(double preuTotal) {
        this.preuTotal = preuTotal;
    }


    @Override
    public String toString() {
        return "{" +
            " idComanda='" + getIdComanda() + "'" +
            ", plats='" + getPlats() + "'" +
            ", preuTotal='" + getPreuTotal() + "'" +
            "}";
    }
}
