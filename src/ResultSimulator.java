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
        result_status.put("experiment_status", expStatus);
        result_status.put("inventory", this.getInventory());
        result_status.put("capabilities", this.getCapabilities());
        jSON.put("return-status", result_status);

        FileWriter fr = new FileWriter(resultFile);
        fr.write(jSON.toJSONString());
//       file.append(jSON.toString(1));
        fr.close();

        return resultFile;
    }
    private JSONArray getInventory(){
        Random r = new Random();
        JSONArray i = new JSONArray();
        JSONObject supply1 = new JSONObject();
        supply1.put("liquid O2", r.nextInt(300));
        i.add(supply1);
        JSONObject supply2 = new JSONObject();
        supply2.put("H2",r.nextInt(300) );
        i.add(supply2);
        JSONObject supply3 = new JSONObject();
        supply3.put("test tube", r.nextInt(400));
        i.add(supply3);
        JSONObject supply4 = new JSONObject();
        supply4.put("caps", r.nextInt(410));
        i.add(supply4);
        JSONObject supply5 = new JSONObject();
        supply5.put("Butane", r.nextInt(200));
        i.add(supply5);
        JSONObject supply6 = new JSONObject();
        supply6.put("Benzene", r.nextInt(300));
        i.add(supply6);
        JSONObject supply7 = new JSONObject();
        supply7.put("Chloric acid", r.nextInt(100));
        i.add(supply7);
        JSONObject supply8 = new JSONObject();
        supply8.put("Nitric acid", r.nextInt(100));
        i.add(supply8);
        JSONObject supply9 = new JSONObject();
        supply9.put("Ethyl alcohol", r.nextInt(100));
        i.add(supply9);
        JSONObject supply10 = new JSONObject();
        supply10.put("Millon's", r.nextInt(200));
        i.add(supply10);
        JSONObject supply11 = new JSONObject();
        supply11.put("Ammonia", r.nextInt(150));
        i.add(supply11);
        JSONObject supply12 = new JSONObject();
        supply12.put("sealed flask", r.nextInt(75));
        i.add(supply12);
        JSONObject supply13 = new JSONObject();
        supply13.put("Methane", r.nextInt(150));
        i.add(supply13);
        JSONObject supply14 = new JSONObject();
        supply14.put("liquid N2", r.nextInt(300));
        i.add(supply14);


        return i;
    }
    private JSONArray getCapabilities() {
        Random r = new Random();
        ArrayList<String> caps = new ArrayList<>();
        caps.add("O2-2");
        caps.add("O2-1");
        caps.add("Arm-2");
        caps.add("Arm-1");
        caps.add("C-1");
        caps.add("H1");
        caps.add("M-1");
        caps.add("M-2");
        caps.add("E1");
        caps.add("E2");
        caps.add("T1");
        caps.add("T2");
        caps.add("T3");
        caps.add("T4");
        caps.add("B1");
        caps.add("H1");
        caps.add("H2");
        caps.add("PH-1");
        caps.add("HPGC-1");
        caps.add("Arm-3");
        JSONArray capabilities = new JSONArray();
        for(int i=0; i<caps.size(); i++){
            JSONObject capability = new JSONObject();
            int status = r.nextInt(2);
            if(status == 0){
                capability.put(caps.get(i), "Failed");
            }
            if(status == 1){
                capability.put(caps.get(i), "Operational");
            }
            capabilities.add(capability);
        }
    return capabilities;
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
