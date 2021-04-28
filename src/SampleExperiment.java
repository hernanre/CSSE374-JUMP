import java.util.HashSet;

public class SampleExperiment implements Experiment{

    private String sampleName;
    private double quantity;
    private String unit;
    private String location;

    private HashSet<Observer> observers;

    public SampleExperiment(String sampleName, double quantity, String unit, String location) {
        this.sampleName = sampleName;
        this.quantity = quantity;
        this.unit = unit;
        this.location = location;
        this.observers = new HashSet<>();
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observers);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers) {

        }
    }

    public double getQuantity() {
        return quantity;
    }

    public String getSampleName() {
        return sampleName;
    }

    public String getLocation() {
        return location;
    }

    public String getUnit() {
        return unit;
    }
}


