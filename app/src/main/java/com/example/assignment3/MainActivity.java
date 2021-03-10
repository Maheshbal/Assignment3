package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<candidateinfo> candidate_information;
    private TextView candidate_information1, candidate_information2, candidate_information3;
    private Button voterbutton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidate_information1 = findViewById(R.id.can1_view);
        candidate_information2 = findViewById(R.id.can2_view);
        candidate_information3 = findViewById(R.id.can3_view);

        voterbutton = findViewById(R.id.cast_vote);

        candidate_information = new ArrayList<candidateinfo>();
        Intent intent = getIntent();

        ArrayList<candidateinfo> candidates = (ArrayList<candidateinfo>) intent.getSerializableExtra("candidates");
        if(candidates == null){
            candidate_information.add(new candidateinfo(1,"Maheshinder",0));
            candidate_information.add(new candidateinfo(2,"Kunwar",0));
            candidate_information.add(new candidateinfo(3,"Pawan",5));
        }
        else{
            candidate_information = candidates;
        }

        candidate_information1.setText(candidate_information.get(0).getName()+" : " + candidate_information.get(0).getVotes());
        candidate_information2.setText(candidate_information.get(1).getName()+" : " + candidate_information.get(1).getVotes());
        candidate_information3.setText(candidate_information.get(2).getName()+" : " + candidate_information.get(2).getVotes());

        voterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Assignment2.class);
                intent.putExtra("candidates", candidate_information);
                startActivity(intent);
            }
        });


    }
}


