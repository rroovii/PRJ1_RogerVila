public class Beguda extends Plat {
    private String tipus;
    private String mida;

    // Constructor
    public Beguda(String nom, double preu, String tipus, String mida) {
        super(nom, preu, "Beguda");
        this.tipus = tipus;
        this.mida = mida;
    }

    // Getters i Setters
    public String getTipus() { return tipus; }
    public void setTipus(String tipus) { this.tipus = tipus; }

    public String getMida() { return mida; }
    public void setMida(String mida) { this.mida = mida; }

    // gel
    public void afegirGel() {
        System.out.println("Afegint gel a la beguda...");
        
    }
    //Tostring

    @Override
    public String toString() {
        return "{" +
            " tipus='" + getTipus() + "'" +
            ", mida='" + getMida() + "'" +
            "}";
    }

}
