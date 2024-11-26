public class Kebab extends Plat {
    private String tipusCarn;
    private String mida;
    private String salsa;

    // Constructor
    public Kebab(String nom, double preu, String tipusCarn, String mida, String salsa) {
        super(nom, preu, "Kebab");
        this.tipusCarn = tipusCarn;
        this.mida = mida;
        this.salsa = salsa;
    }

    // Getters i Setters
    public String getTipusCarn() { return tipusCarn; }
    public void setTipusCarn(String tipusCarn) { this.tipusCarn = tipusCarn; }

    public String getMida() { return mida; }
    public void setMida(String mida) { this.mida = mida; }

    public String getSalsa() { return salsa; }
    public void setSalsa(String salsa) { this.salsa = salsa; }

    public void personalitzarKebab() {
        System.out.println("Personalitzant el kebab...");
    }
    

    @Override
    public String toString() {
        return "{" +
            " tipusCarn='" + getTipusCarn() + "'" +
            ", mida='" + getMida() + "'" +
            ", salsa='" + getSalsa() + "'" +
            "}";
    }

}
