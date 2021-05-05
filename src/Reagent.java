public class Reagent {

    private String reagentName, detail, measurement;
    private int quantity, time;

    public Reagent(String reagentName, String detail, String measurement, int quantity, int time) {
        this.reagentName = reagentName;
        this.detail = detail;
        this.measurement = measurement;
        this.quantity = quantity;
        this.time = time;
    }

    public String getReagentName() {
        return reagentName;
    }

    public String getDetail() {
        return detail;
    }

    public String getMeasurement() {
        return measurement;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTime() {
        return time;
    }
}
