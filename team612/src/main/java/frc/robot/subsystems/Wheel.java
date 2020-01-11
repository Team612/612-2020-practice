package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wheel extends SubsystemBase {

  // Target values for each color value
  private final Color kBlueTarget = ColorMatch.makeColor(.143, .427, .429);
  private final Color kGreenTarget = ColorMatch.makeColor(.197, .561, .240);
  private final Color kRedTarget = ColorMatch.makeColor(.561, .232, .114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

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

  public String getClosestColor() {

    // Read the color sensor and get the closest match
    ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());

    // Get string version of color
    if (match.color == kBlueTarget) {
      return "blue";
    } else if (match.color == kGreenTarget) {
      return "green";
    } else if (match.color == kRedTarget) {
      return "red";
    } else if (match.color == kYellowTarget) {
      return "yellow";
    }
    return "";
    
  }

  @Override
  public void periodic() {
  }

}
