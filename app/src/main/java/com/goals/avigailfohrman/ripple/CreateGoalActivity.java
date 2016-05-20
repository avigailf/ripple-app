package com.goals.avigailfohrman.ripple;

import android.content.Intent;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateGoalActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView amountSoFarText;
    private int finalTargetAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        setSeekBarFunctionality();
    }

    private void setSeekBarFunctionality() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Integer p = (Integer) progress;
                amountSoFarText.setText(p.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO: Maybe add an animation later.
                amountSoFarText.setTypeface(Typeface.DEFAULT_BOLD);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO: Maybe add an animation later.
                amountSoFarText.setTypeface(Typeface.DEFAULT);
                finalTargetAmount = Integer.parseInt(amountSoFarText.getText().toString());
            }
        });
    }

    private void initActivity() {
        setContentView(R.layout.activity_create_goal);
        Button createGoalButton =  (Button) findViewById(R.id.CreateGoalButton);
        finalTargetAmount = 0;

        // Get information for goal and create goal.
        final Goal goalToAdd = makeGoal();

        createGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference(goalToAdd.getGoalId() + "");
                reference.setValue(goalToAdd);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Goal value = dataSnapshot.getValue(Goal.class);
                        Log.d("TAG", "Value is: " + goalToAdd.getGoalName());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("TAG", "DatabaseError: " + databaseError);

                    }
                });

//                Intent currentIntent = getIntent().putExtra("name", goalName)
//                        .putExtra("target", finalTargetAmount);
//                setResult(RESULT_OK, currentIntent);
                // Close the activity.
                finish();
            }
        });
        initVariables();
//        getIntent().putExtra("name", findViewById(R.id.TargetAmountText).toString())
//                .putExtra("target", finalTargetAmount);
        //this.setResult(RESULT_OK, getIntent());

//        Intent intent = this.getIntent();
//        intent.putExtra("SOMETHING", "EXTRAS");
//        this.setResult(RESULT_OK, intent);
//        finish();
    }

    private Goal makeGoal() {
        EditText nameGoalTextBox = (EditText) findViewById(R.id.NameGoalTextBox);
        final String goalName = nameGoalTextBox.getText().toString();
        EditText goalCreatorsNameTextBox = (EditText) findViewById(R.id.CreatorNameGoalTextBox);
        final String goalCreatorsName = goalCreatorsNameTextBox.getText().toString();
        return new Goal(goalName, finalTargetAmount, goalCreatorsName, 0);
    }

    private void initVariables() {
        seekBar = (SeekBar) findViewById(R.id.TargetAmountSeekBarInput);
        amountSoFarText = (TextView) findViewById(R.id.TargetCurrentAmountText);
        amountSoFarText.setText(getString(R.string.initial_goal_target));
        finalTargetAmount = 1;
    }


}
