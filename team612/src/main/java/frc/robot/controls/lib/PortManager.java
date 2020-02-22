package frc.robot.controls.lib;

import java.util.ArrayList;
import frc.robot.controls.lib.Loader.Profile;

public class PortManager {
    private static ArrayList<Profile> portProfiles = null;

    static void init(ArrayList<Profile> portIn) {
        portProfiles = portIn;
    }

    public static int getPort(String key){
        for (Profile profile : portProfiles) {
            if (profile.name.equals(key)) return profile.ports[0];
        }
        return -1;
    }

    public static int[] getPorts(String key){
        for (Profile profile : portProfiles) {
            if (profile.name.equals(key)) return profile.ports;
        }
        return  new int[]{-1};
    }
}