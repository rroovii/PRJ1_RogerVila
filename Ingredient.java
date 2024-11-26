public class Ingredient{
    private String nom;
    private int quantitat;


    public Ingredient(String nom, int quantitat) {
        this.nom = nom;
        this.quantitat = quantitat;
    }


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantitat() {
        return this.quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }


    @Override
    public String toString() {
        return "{" +
            " nom='" + getNom() + "'" +
            ", quantitat='" + getQuantitat() + "'" +
            "}";
    }

}