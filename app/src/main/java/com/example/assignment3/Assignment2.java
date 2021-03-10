package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Assignment2 extends AppCompatActivity {
    private Spinner candidateSpin;
    ToggleButton Terminalbutton;
    Button buttonSubmission;
    EditText putname, putid;
    private ArrayList<candidateinfo> candidateClassarraylist;
    ArrayList<voterid> voterClassArrayList;
    private boolean Final;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        voterClassArrayList = new ArrayList<voterid>();
        candidateSpin = findViewById(R.id.Spinner);
        Terminalbutton = findViewById(R.id.toggleButton);
        buttonSubmission = findViewById(R.id.button);
        putname = findViewById(R.id.editTextTextPersonName);
        putid = findViewById(R.id.editTextTextPersonID);


        Intent intent = getIntent();
        ArrayList<candidateinfo> candidates = (ArrayList<candidateinfo>) intent.getSerializableExtra("candidates");
        candidateClassarraylist = candidates;
        ArrayAdapter<candidateinfo> adapter = new ArrayAdapter<candidateinfo>(this,
                android.R.layout.simple_spinner_item, candidateClassarraylist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        candidateSpin.setAdapter(adapter);


        buttonSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(putname.getText().toString().isEmpty()){
                    Toast.makeText(Assignment2.this, " Fill  name field", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(putid.getText().toString().isEmpty()){
                    Toast.makeText(Assignment2.this, " Fill the Id field", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (voterid V : voterClassArrayList) {
                    if(V.getId() == Integer.parseInt(putid.getText().toString())){
                        Toast.makeText(Assignment2.this, "Ohh! Id already avaliable ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!Terminalbutton.isChecked()){
                    Toast.makeText(Assignment2.this, "Accept the terms and condition firstly", Toast.LENGTH_SHORT).show();
                    return;
                }

                voterClassArrayList.add(new voterid(Integer.parseInt(putid.getText().toString()), putname.getText().toString()));
                int selectedCandidateIndex = candidateSpin.getSelectedItemPosition();
                candidateinfo selectedCandidate = candidateClassarraylist.get(selectedCandidateIndex);
                selectedCandidate.setVotes(selectedCandidate.getVotes() + 1);

                Toast.makeText(Assignment2.this, "Your vote has been casted !!", Toast.LENGTH_SHORT).show();


            }
        });

        Terminalbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    Terminalbutton.setTextOn("Reject");

                } else {

                    Terminalbutton.setTextOff("Accepted Terms");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Assignment2.this, MainActivity.class);
        intent.putExtra("candidates", candidateClassarraylist);
        startActivity(intent);
    }
}

