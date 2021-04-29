import java.util.HashSet;

public class ComponentManager implements Observer, Manager{

    private HashSet<Component> components;

    public ComponentManager() {
        this.components = new HashSet<>();
    }

    @Override
    public void notifyObserver() {

    }

    @Override
    public boolean validExperiment(Experiment experiment) {
        if(experiment instanceof SampleExperiment) {

        }
        return false;
    }

    @Override
    public void addExperiment(Experiment e) {

    }
}
