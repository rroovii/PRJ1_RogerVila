import java.util.ArrayList;

public class Gerent extends Usuari {
    private String responsabilitats;
    private ArrayList<Informe> informesGenerats = new ArrayList<>(); 

    public Gerent(String nom, String contrasenya, String rol, String responsabilitats) {
        super(nom, contrasenya, "Gerent");
        this.responsabilitats = responsabilitats;
    }


    public String getResponsabilitats() {
        return this.responsabilitats;
    }

    public void setResponsabilitats(String responsabilitats) {
        this.responsabilitats = responsabilitats;
    }

    public ArrayList<Informe> getInformesGenerats() {
        return this.informesGenerats;
    }

    public void setInformesGenerats(ArrayList<Informe> informesGenerats) {
        this.informesGenerats = informesGenerats;
    }


    @Override
    public String toString() {
        return "{" +
            " responsabilitats='" + getResponsabilitats() + "'" +
            ", informesGenerats='" + getInformesGenerats() + "'" +
            "}";
    }

}