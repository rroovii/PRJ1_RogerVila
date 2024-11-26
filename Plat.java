public class Plat {
    private String nom;
    private double preu;
    private String tipus;

    // Constructor
    public Plat(String nom, double preu, String tipus) {
        this.nom = nom;
        this.preu = preu;
        this.tipus = tipus;
    }

    // Getters i Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPreu() { return preu; }
    public void setPreu(double preu) { this.preu = preu; }

    public String getTipus() { return tipus; }
    public void setTipus(String tipus) { this.tipus = tipus; }

//tostring
    @Override
    public String toString() {
        return "{" +
            " nom='" + getNom() + "'" +
            ", preu='" + getPreu() + "'" +
            ", tipus='" + getTipus() + "'" +
            "}";
    }

}
