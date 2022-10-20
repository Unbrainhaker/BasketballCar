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
    double ForBackstage;
    boolean isAbled, isAutoBurst, isTeleBurst;
    double autoSpeedControl;
    double teleSpeedControl;

    public DriveForAndBack(Drive drive){
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        initTime = Timer.getFPGATimestamp();
        isAbled = false;
        isAutoBurst = false;
        isTeleBurst = false;
        autoSpeedControl = 0.6;
        teleSpeedControl = 0.7;
    }

    @Override
    public void execute() {
        if(isAutoBurst){
            autoSpeedControl = 0.9;
        }else{
            autoSpeedControl = 0.6;
        }
        if(isTeleBurst){
            teleSpeedControl = 1.0;
        }else{
            teleSpeedControl = 0.7;
        }
        if(m_driverJoystick.getRawButtonPressed(Constants.Btn_A)){
            isAbled = !isAbled;
        }
        if(isAbled){
            ForBackstage = (Timer.getFPGATimestamp()-initTime)%4;
            if(0<ForBackstage && ForBackstage<=1){
                drive.arcadeDrive(autoSpeedControl, 0);
            }else if(1<ForBackstage && ForBackstage<=1.5){
                drive.arcadeDrive(autoSpeedControl-0.2,0);
            }else if(1.5<ForBackstage && ForBackstage<=2){
                drive.arcadeDrive(autoSpeedControl-0.3, 0);
            }else if(2<ForBackstage && ForBackstage<=3){
                drive.arcadeDrive(-autoSpeedControl, 0);
            }else if(3<ForBackstage && ForBackstage<=3.5){
                drive.arcadeDrive(-autoSpeedControl+0.2, 0);
            }else if(3.5<ForBackstage && ForBackstage<4){
                drive.arcadeDrive(-autoSpeedControl+0.3, 0);
            }
        }else{
            drive.arcadeDrive(m_driverJoystick.getRawAxis(Constants.leftStick_Y)*teleSpeedControl, m_driverJoystick.getRawAxis(Constants.rightStick_X)*teleSpeedControl);
            initTime = Timer.getFPGATimestamp();
            if(m_driverJoystick.getRawButtonPressed(Constants.Btn_X)){
                isAutoBurst = !isAutoBurst;
            }
            if(m_driverJoystick.getRawButtonPressed(Constants.Btn_Y)){
                isTeleBurst = !isTeleBurst;
            }
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
        if(m_driverJoystick.getRawButtonPressed(Constants.Btn_B)){
            drive.arcadeDrive(0, 0);
            return true;
        }
        return false;
    }
}