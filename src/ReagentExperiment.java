public class ReagentExperiment implements Experiment {


    private String reagentName, detail, measurement, ID, name;
    private int quantity, time;

    public ReagentExperiment(String reagentName, String ID, String name,
                             String detail, String measurement, int quantity, int time){
        this.reagentName = reagentName;
        this.detail = detail;
        this.quantity = quantity;
        this.time = time;
        this.measurement= measurement;
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
}
