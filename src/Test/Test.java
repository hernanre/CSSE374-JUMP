package Test;

public class Test {

    public static void main(String[] args) {
        PackageCompilerTest packageCompilerTest = new PackageCompilerTest();
        packageCompilerTest.testSendExperiment();
        packageCompilerTest.testSendFailForNonExistingReagent();
        packageCompilerTest.testSendFailForUsingTooMuchReagent();
    }
}
