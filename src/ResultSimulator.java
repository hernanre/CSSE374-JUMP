import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ResultSimulator {
    public File createResults(File file) throws IOException {
        Random random = new Random();
        File resultFile = new File("src/ResultJSON.json");
        JSONObject jSON = new JSONObject();
        ArrayList<String>ids = this.getExperimentIDs(file);
//        System.out.println(ids);

        JSONObject result_status = new JSONObject();
//        for(int i = 0; i < experiments.size(); i++){
//            payload.put("experiment"+(i+1), experiments.get(i));
//        }
        JSONArray inventory= new JSONArray();
        //This is generating the statuses of the experiments included in the package file
        JSONArray expStatus = new JSONArray();
        for(int i = 0; i < ids.size(); i++){
            JSONObject status = new JSONObject();
            int stat = random.nextInt(2);
            if(stat == 0) {
                status.put(ids.get(i), "Failed");
            }
            if(stat == 1) {
                status.put(ids.get(i), "Successful");
            }
            expStatus.add(status);
        }
        result_status.put("Experiment Status", expStatus);
        jSON.put("result-status", result_status);


        FileWriter fr = new FileWriter(resultFile);
        fr.write(jSON.toString());
//       file.append(jSON.toString(1));
        fr.close();


        return resultFile;
    }
    private ArrayList<String> getExperimentIDs (File file){
        ArrayList<String> experimentIds = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(file.toString()));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONObject payload = (JSONObject) jsonObject.get("payload");
            for(int i = 1; i <= 10; i++){
                JSONObject experiment = (JSONObject)payload.get("experiment"+i);
                if(experiment == null){
                    continue;
                }
                String id = (String) experiment.get("experiment_id");
                experimentIds.add(id);
            }
            return experimentIds;
        } catch (Exception e) {
            e.printStackTrace();
            return experimentIds;
        }
    }


}
