public class Informe {
    private long data;
    private int ventesTotals;
    private Plat platsPopulars;


    public Informe(long data, int ventesTotals, Plat platsPopulars) {
        this.data = data;
        this.ventesTotals = ventesTotals;
        this.platsPopulars = platsPopulars;
    }

    public long getData() {
        return this.data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public int getVentesTotals() {
        return this.ventesTotals;
    }

    public void setVentesTotals(int ventesTotals) {
        this.ventesTotals = ventesTotals;
    }

    public Plat getPlatsPopulars() {
        return this.platsPopulars;
    }

    public void setPlatsPopulars(Plat platsPopulars) {
        this.platsPopulars = platsPopulars;
    }

    @Override
    public String toString() {
        return "{" +
            " data='" + getData() + "'" +
            ", ventesTotals='" + getVentesTotals() + "'" +
            ", platsPopulars='" + getPlatsPopulars() + "'" +
            "}";
    }

}