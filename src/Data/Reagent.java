package Data;

import org.json.simple.*;

import java.util.ArrayList;

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

    public JSONObject getJSONReagentCommand(){
        JSONObject reagentCommand = new JSONObject();
        ArrayList<Object> params= new ArrayList<>();
        params.add(this.reagentName);
        params.add(this.quantity);
        reagentCommand.put("param", params.toString());
        reagentCommand.put("command", "C6");
        return reagentCommand;
    }
}
