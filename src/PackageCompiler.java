import org.json.simple.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PackageCompiler {
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





}
