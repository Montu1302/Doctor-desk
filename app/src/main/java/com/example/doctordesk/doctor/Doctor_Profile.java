package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorProfileBinding;
import com.example.doctordesk.patient.PatientLogin;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Doctor_Profile extends AppCompatActivity {
ActivityDoctorProfileBinding binding;
    PreferenceManager preferencesManager;

FirebaseFirestore firestore;
    Context context;
    public static boolean accept=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferencesManager = new PreferenceManager(getApplicationContext());


      LoadDoctorDetails();


        binding.AddMedicineFloating.setOnClickListener(view -> addMedicine());

        binding.BnViewDoc.setSelectedItemId(R.id.MyProfile);


        binding.DoctorLogout.setOnClickListener(view -> logout());
        binding.DoctorEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Doctor_Profile.this, Doctor_EditProfile.class));
            }
        });
        binding.BnViewDoc.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.MyProfile:
                        return true;
                    case R.id.MyPtient:
                        startActivity(new Intent(getApplicationContext(), Doctor_MyPatient.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.Appointment:
                        startActivity(new Intent(getApplicationContext(), Doctor_Appointment.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.message:
                        startActivity(new Intent(getApplicationContext(), Doctor_Message.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }


    private void addMedicine(){

        firestore=FirebaseFirestore.getInstance();
        Button addMedicineBtn,cancel;
        EditText edit;
        Dialog dialog= new Dialog(Doctor_Profile.this);
        dialog.setContentView(R.layout.add_medcine_database_dialog);

        addMedicineBtn=dialog.findViewById(R.id.addMedicineBtn);
        cancel=dialog.findViewById(R.id.cancel);
        edit=dialog.findViewById(R.id.addMedicineEdit);

        addMedicineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit.getText().toString().isEmpty()){
                    ShowToast("Enter Medicine");
                }else{
                    HashMap<String,Object> medicine=new HashMap<>();

                    medicine.put(Constants.KEY_MEDICINE_NAME,edit.getText().toString());

                    firestore.collection(Constants.KEY_COLLECTION_MEDICINES).add(medicine).addOnSuccessListener(documentReference -> {
                        ShowToast("Medicine Added");
                        edit.setText("");
                    });
                }

            }
        });
        dialog.show();

        cancel.setOnClickListener(view -> dialog.dismiss());



    }
    private void ShowToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void logout(){
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        DocumentReference documentReference=
                database.collection(Constants.KEY_COLLECTION_DOCTORS).document(preferencesManager.getString(Constants.KEY_DOCTOR_ID));
        HashMap<String,Object> updates=new HashMap<>();
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferencesManager.clear();
                    startActivity(new Intent(getApplicationContext(), DoctorLogin.class));
                    finish();
                })
                .addOnFailureListener(e-> ShowToast("Unable to Sign out"));
    }

    private void LoadDoctorDetails(){

        binding.DoctorNameProfile.setText(preferencesManager.getString(Constants.KEY_DOCTOR_NAME));
        binding.DoctorClinicNameProfile.setText(preferencesManager.getString(Constants.KEY_CLINIC_NAME));
        binding.DoctorNumberProfile.setText(preferencesManager.getString(Constants.KEY_DOCTOR_PHONENUMBER));
        binding.DoctorAdressProfile.setText(preferencesManager.getString(Constants.KEY_CLINIC_ADDRESS));
        binding.DoctorREGNOProfile.setText(preferencesManager.getString(Constants.KEY_DOCTOR_REGISTRATION_NUMBER));
        binding.DoctorSpecProfile.setText(preferencesManager.getString(Constants.KEY_SPECIALIZATION));


    }
}