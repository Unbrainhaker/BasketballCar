package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class DriveForAndBack extends CommandBase{

    private final Joystick m_driverJoystick = new Joystick(Constants.kDriverJoystickPort);

    private Drive drive;
    double initTime;

    public DriveForAndBack(Drive drive){
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        initTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        if((Timer.getFPGATimestamp()-initTime)%2 <= 1){
            drive.arcadeDrive(0.6, 0);
        }else{
            drive.arcadeDrive(-0.6, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        if(interrupted){
            System.out.println("Inturrupted");
        }
        drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        if(m_driverJoystick.getRawButtonPressed(Constants.Btn_A)){
            return true;
        }
        return false;
    }
}