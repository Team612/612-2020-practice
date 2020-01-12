package frc.robot.commands.vision;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class NetworkTableListener {

    // Define a network table and create entrys for each value
    NetworkTable table;
    NetworkTableEntry offsetEntry;
    double offset;
    
    // Create network table instance and table
    NetworkTableListener(String table_name) {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        table = inst.getTable(table_name);
        offsetEntry = table.getEntry("tx");
    }

    public void createListeners() {
        offsetEntry.addListener(event -> {
            offset = (double) event.value.getValue();
        }, EntryListenerFlags.kUpdate);
    }

    // Returns value of network table given title of column
    public double getOffset() {
        return offset;
    }

    // Returns true if valid contour is detected
    public boolean isTargetFound() {
        return getOffset()!=-99999;
    }

}