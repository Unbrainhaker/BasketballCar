// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

  // Motor Declation
  WPI_VictorSPX m_FrontLeft = new WPI_VictorSPX(Constants.kFrontLeftMotorID);
  WPI_VictorSPX m_FrontRight = new WPI_VictorSPX(Constants.kFrontRightMotorID);
  WPI_VictorSPX m_RearLeft = new WPI_VictorSPX(Constants.kRearLeftMotorID);
  WPI_VictorSPX m_RearRight = new WPI_VictorSPX(Constants.kRearRightMotorID);

  MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_FrontLeft, m_RearLeft);
  MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_FrontRight, m_RearRight);

  DifferentialDrive m_drive = new DifferentialDrive(m_leftGroup, m_rightGroup);

  /** Creates a new Drive. */
  public Drive() {
    m_FrontLeft.setInverted(false);
    m_FrontRight.setInverted(true);
    m_RearLeft.setInverted(false);
    m_RearRight.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double speed, double turn) {
    m_drive.arcadeDrive(-speed, turn);
  }

  public void motorSet2Zero() {
    m_FrontLeft.set(0);
    m_FrontRight.set(0);
    m_RearLeft.set(0);
    m_RearRight.set(0);
  }
}
