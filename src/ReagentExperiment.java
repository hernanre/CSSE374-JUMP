import java.util.ArrayList;

public class ReagentExperiment implements Experiment {


    private String ID, name, status;
    private ArrayList<Reagent> reagents;

    public ReagentExperiment(String ID, String name, ArrayList<Reagent> reagents){
        this.name = name;
        this.ID = ID;
        this.reagents = reagents;
        this.status = "Entered";
    }

    public ArrayList<Reagent> getReagents() {
        return reagents;
    }

    public void addReagemt(Reagent reagent) {
        reagents.add(reagent);
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
        return "Sample ID: " + ID + ", Name: " + name;
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
}
