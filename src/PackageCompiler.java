import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class PackageCompiler implements Subject{

    private HashSet<Observer> observers;
    private HashSet<Manager> managers;
    private CommunicationManager communicationManager;

    public PackageCompiler(CommunicationManager communicationManager) {
        this.communicationManager = communicationManager;
        this.observers = new HashSet<>();
        this.managers = new HashSet<>();
    }

    public boolean sendExperiment(PriorityQueue<Experiment> experiments) {
        ArrayList<Experiment> experimentArrayList = new ArrayList<>(experiments);
        for (Observer o : observers){
            if(!o.validExperiments(experimentArrayList))
                return false;
        }
        System.out.println("Approved");
        //Exp -> JSONObject
        ArrayList<JSONObject> expJson = new ArrayList<JSONObject>();
        for (Experiment e: experiments){
            expJson.add(e.getJSONObject());
        }
        try {
            File toSend = toJSONfile(expJson);
            communicationManager.sendExperiment(toSend);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public File toJSONfile(ArrayList<JSONObject> experiments) throws IOException {
        JSONObject jSON = new JSONObject();

        JSONObject payload = new JSONObject();
        for(int i = 0; i < experiments.size(); i++){
            payload.put("experiment"+(i+1), experiments.get(i));
        }
        jSON.put("payload", payload);
        File file = new File("src/File.json");
        FileWriter fr = new FileWriter(file);
        fr.write(jSON.toString());
//       file.append(jSON.toString(1));
        fr.close();
        return file;
    }


    public void registerManager(Manager manager) {
        managers.add(manager);
    }

    public void removeManager(Manager manager) {
        managers.remove(manager);
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
    public void notifyObservers() {

    }
}
