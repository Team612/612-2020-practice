package frc.robot.controls.lib;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Loader {

    public static void loadJSON(String src) {
        System.out.println("[JSON::LOADER] Loading file from source \"" + src + "\"...");

        // JSON Object
        Object obj;
        // Load file if it exists
        try {
            File f = new File(src);
            obj = new JSONParser().parse(new FileReader(f));
        } catch (IOException | ParseException e) {
            System.out.println("[JSON::LOADER::ERROR] Error loading file \"" + src + "\"");
            e.printStackTrace();
            return;
        }

        // JSON Object objects
        JSONObject topObject = (JSONObject) obj;

        String[] portNames = setToString(topObject.keySet());
        ArrayList<Profile> portAL = new ArrayList<>();

        for (String port : portNames) {
            JSONObject currentObj = (JSONObject) topObject.get(port);
            JSONArray portArr = (JSONArray) currentObj.get("ports");
            int portCount = Integer.parseInt(currentObj.get("port_count").toString());
            if (portCount >= portArr.size()) portCount = portArr.size();
            int[] ports = new int[portCount];
            for (int i = 0; i < portCount; ++i) ports[i] = Integer.parseInt(portArr.get(i).toString());
            Profile p = new Profile();
            p.name = port;
            p.ports = ports;
            portAL.add(p);
            System.out.println(p.name);
        }

        PortManager.init(portAL);
    }

    //Converts Set of Objects to String array
    private static String[] setToString(Set<Object> source){
        String[] dest = new String[source.size()];
        int iter=0;
        for (Object o : source) {
            dest[iter]=(String)o;
            iter++;
        }
        return dest;
    }

    public static class Profile{
        public int[] ports;
        public String name;
    }
}