public class ExperimentCompiler {

    public ExperimentCompiler() {

    }

    public static Experiment compileSampleOnly(String sampleName, double quantity, String unit, String location) {
        Experiment e = new SampleExperiment(sampleName, quantity, unit, location);

        return e;
    }

}
