public class Usuari {
    private String nom;
    private String contrasenya;
    private String rol;


    public Usuari(String nom, String contrasenya, String rol) {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.rol = rol;
    }


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContrasenya() {
        return this.contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    @Override
    public String toString() {
        return "{" +
            " nom='" + getNom() + "'" +
            ", contrasenya='" + getContrasenya() + "'" +
            ", rol='" + getRol() + "'" +
            "}";
    }

}