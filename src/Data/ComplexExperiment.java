package Data;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ComplexExperiment implements Experiment {
    private String ID, name, status;
    private ArrayList<Command> commandList;
    private int priority;

    public ComplexExperiment(ArrayList<Command> commandList) {
        this.commandList = commandList;
        this.status = "Entered";
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public JSONObject getJSONObject() {
        return null;
    }
}

