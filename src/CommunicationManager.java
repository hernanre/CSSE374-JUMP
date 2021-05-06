import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CommunicationManager implements Subject{
    JSONObject result = null;
    JSONArray supplies, experiments, components;
    JSONParser parser = new JSONParser();;
    JSONObject data;
    ArrayList<Observer> observers = new ArrayList<>();


    public CommunicationManager(){

    }

    public boolean sendExperiment(File toSend){
        //Sending experiment package using socket
        ResultSimulator resultSimulator = new ResultSimulator();
        // Perhaps using a Singleton pattern to limit number of Simulator

        try {
            File resultFile = resultSimulator.createResults(toSend);
            extractResult(resultFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public void extractResult(File received){
        try{
            Object obj = parser.parse(new FileReader(received.toString()));
            result = (JSONObject) obj;
            data = (JSONObject) result.get("return-status");
            this.notifyObservers();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
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
    public void notifyObservers() {
        for (Observer o : observers){
            o.extractResults(data);
        }
    }
}
