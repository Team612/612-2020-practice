package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wheel extends SubsystemBase {

  public static Object getClosestColor;
// Target values for each color value
  public final Color kBlueTarget = ColorMatch.makeColor(.143, .427, .429);
  public final Color kGreenTarget = ColorMatch.makeColor(.197, .561, .240);
  public final Color kRedTarget = ColorMatch.makeColor(.561, .232, .114);
  public final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  // The matcher that map the sensor value to one of the targets
  public ColorMatch colorMatcher = new ColorMatch();

  // Create the color sensor object
  public ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

  public Wheel() {
    createMatches();  // Create the matches for the color matcher
  }

  public void createMatches() {
    // Create the color matches
    colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kGreenTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorMatcher.addColorMatch(kYellowTarget);

    // Create the threshold for the matcher
    colorMatcher.setConfidenceThreshold(0.00);
  }
   
  public char getClosestColor() {

    // Read the color sensor and get the closest match
    ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor()); 

    // Get string version of color
    if (match.color == kBlueTarget) {
      return 'B';
    } else if (match.color == kGreenTarget) {
      return 'G';
    } else if (match.color == kRedTarget) {
      return 'R';
    } else {
      return 'Y';  // If none of the 3 others return the last choice, yellow
    }
    
  }

  @Override
  public void periodic() {
  }

}
