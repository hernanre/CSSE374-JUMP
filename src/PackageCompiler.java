import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PackageCompiler {
    public void toJSONfile(ArrayList<JSONObject> experiments) throws JSONException, IOException {
        JSONObject jSON = new JSONObject();

        JSONObject payload = new JSONObject();
        for(int i = 0; i < experiments.size(); i++){
            payload.put("experiment"+(i+1), experiments.get(i));
        }
        jSON.put("payload", payload);
        FileWriter file = new FileWriter("src/File.json");
        file.write(jSON.toString(1));
//       file.append(jSON.toString(1));
        file.close();
    }





}
