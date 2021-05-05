import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public void extractResult(){
        try{
            result = (JSONObject) parser.parse(new FileReader("src/result.json"));
            data = (JSONObject) result.get("return-status");

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
    public void notifyObservers(Experiment experiment) {
        for (Observer o : observers){
            o.extractResults(data);
        }
    }
}
