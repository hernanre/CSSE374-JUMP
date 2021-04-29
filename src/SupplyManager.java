import java.util.HashSet;

public class SupplyManager implements Observer, Manager{

    private HashSet<Supply> supplies;

    public SupplyManager() {
        this.supplies = new HashSet<>();
    }

    @Override
    public void addExperiment(Experiment e) {
        if(e instanceof SampleExperiment) {
            for(Supply supply : supplies) {
                if(supply.getName().equals("test tube") || supply.getName().equals("caps")) {
                    supply.setQuantityAvailable(supply.getQuantityAvailable() - 10);
                }
            }
        }
    }

    @Override
    public void notifyObserver() {

    }

    @Override
    public boolean validExperiment(Experiment experiment) {
        if(experiment instanceof SampleExperiment) {
            for(Supply supply : supplies) {
                if(supply.getName().equals("test tube") && supply.getQuantityAvailable() < 10) {
                    return false;
                } else if(supply.getName().equals("caps") && supply.getQuantityAvailable() < 10) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
