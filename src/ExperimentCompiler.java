import java.util.HashSet;

public class ExperimentCompiler implements Subject{

    private HashSet<Observer> observers;
    private HashSet<Manager> managers;

    public ExperimentCompiler() {
        this.observers = new HashSet<>();
    }

    public void compileSampleOnly(String sampleName, double quantity, String unit, String location) {
        Experiment e = new SampleExperiment(sampleName, quantity, unit, location);
        boolean valid = true;
        for (Observer observer : observers) {
            if (!observer.validExperiment(e)) {
                valid = false;
            }
        }
        if(valid) {
            for (Manager manager : managers) {
                manager.addExperiment(e);
            }
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Experiment e) {

    }
}
