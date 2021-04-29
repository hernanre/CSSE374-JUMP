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
            for(Component c: components) {
                if(c.getType().equals("ARM") && c.getStatus().equals("Failed")) {
                    return false;
                } else if(c.getName().equals("Sample Ejector") && c.getStatus().equals("Failed")) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void addExperiment(Experiment e) {
        //do nothing
    }

    public void addComponent(Component component) {
        components.add(component);
    }
}
