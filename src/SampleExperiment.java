import java.util.HashSet;

public class SampleExperiment implements Experiment{

    private String sampleName;
    private double quantity;
    private String unit;
    private String location;


    public SampleExperiment(String sampleName, double quantity, String unit, String location) {
        this.sampleName = sampleName;
        this.quantity = quantity;
        this.unit = unit;
        this.location = location;
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public int getPriority() {
        return 0;
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



