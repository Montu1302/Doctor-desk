package com.example.doctordesk.doctor.Model;

public class myPatientsModel {

    String Appointment_Age,Appointment_Gender,Appointment_Name,Appointment_Phone_Number,Appointment_Id,Appointment_Status,Patient_Id;

    public myPatientsModel() {
    }

    public myPatientsModel(String appointment_Age, String appointment_Gender, String appointment_Name, String appointment_Phone_Number, String appointment_Id, String appointment_Status, String patient_Id) {
        Appointment_Age = appointment_Age;
        Appointment_Gender = appointment_Gender;
        Appointment_Name = appointment_Name;
        Appointment_Phone_Number = appointment_Phone_Number;
        Appointment_Id = appointment_Id;
        Appointment_Status = appointment_Status;
        Patient_Id = patient_Id;
    }

    public String getAppointment_Age() {
        return Appointment_Age;
    }

    public void setAppointment_Age(String appointment_Age) {
        Appointment_Age = appointment_Age;
    }

    public String getAppointment_Gender() {
        return Appointment_Gender;
    }

    public void setAppointment_Gender(String appointment_Gender) {
        Appointment_Gender = appointment_Gender;
    }

    public String getAppointment_Name() {
        return Appointment_Name;
    }

    public void setAppointment_Name(String appointment_Name) {
        Appointment_Name = appointment_Name;
    }

    public String getAppointment_Phone_Number() {
        return Appointment_Phone_Number;
    }

    public void setAppointment_Phone_Number(String appointment_Phone_Number) {
        Appointment_Phone_Number = appointment_Phone_Number;
    }

    public String getAppointment_Id() {
        return Appointment_Id;
    }

    public void setAppointment_Id(String appointment_Id) {
        Appointment_Id = appointment_Id;
    }

    public String getAppointment_Status() {
        return Appointment_Status;
    }

    public void setAppointment_Status(String appointment_Status) {
        Appointment_Status = appointment_Status;
    }

    public String getPatient_Id() {
        return Patient_Id;
    }

    public void setPatient_Id(String patient_Id) {
        Patient_Id = patient_Id;
    }
}
