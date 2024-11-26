
import java.util.ArrayList;

public class Empleat extends Usuari {
    private String torns;
    private ArrayList<Comanda> Comandes = new ArrayList<>(); 

    public Empleat(String nom, String contrasenya, String rol, String torns) {
        super(nom, contrasenya, "Empleat");
        this.torns = torns;
    }
    public String getTorns() {
        return this.torns;
    }

    public void setTorns(String torns) {
        this.torns = torns;
    }

    public ArrayList<Comanda> getComandes() {
        return this.Comandes;
    }

    public void setComandes(ArrayList<Comanda> Comandes) {
        this.Comandes = Comandes;
    }

    @Override
    public String toString() {
        return "{" +
            " torns='" + getTorns() + "'" +
            ", Comandes='" + getComandes() + "'" +
            "}";
    }

    }