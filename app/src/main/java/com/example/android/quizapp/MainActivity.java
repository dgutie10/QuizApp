package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup questionGroupOne;
    private RadioButton questionRadioOne;
    private RadioGroup questionGroupThree;
    private RadioButton questionRadioThree;
    private EditText questionFourAnswer;
    private Button submitButton;
    private TextView resultText;
    private int numberCorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionGroupOne = (RadioGroup) findViewById(R.id.group_one);
        questionGroupThree = (RadioGroup) findViewById(R.id.group_three);
        questionFourAnswer  = (EditText) findViewById(R.id.q4_a);

        submitButton = (Button) findViewById(R.id.submit_button);
        resultText = (TextView) findViewById(R.id.result_text);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers();
            }
        });
    }

    private void checkAnswers(){
        numberCorrect = 0;
        if (questionRadioThree != null)   questionRadioThree.setTextColor(this.getResources().getColor(R.color.reset_answer));
        int selectedIdOne = questionGroupOne.getCheckedRadioButtonId();
        questionRadioOne = (RadioButton) findViewById(selectedIdOne);
        RadioButton correctAnswerOne = (RadioButton) findViewById(R.id.q1_d);
        if (questionRadioOne == correctAnswerOne){
            numberCorrect = numberCorrect+1;
            questionRadioOne.setTextColor(this.getResources().getColor(R.color.reset_answer));
        } else {
            questionRadioOne.setTextColor(this.getResources().getColor(R.color.wrong_answer));
        }
        correctAnswerOne.setTextColor(this.getResources().getColor(R.color.correct_answer));

        int selectedIdThree = questionGroupThree.getCheckedRadioButtonId();
        questionRadioThree = (RadioButton) findViewById(selectedIdThree);
        RadioButton correctAnswerThree = (RadioButton) findViewById(R.id.q3_d);
        if (questionRadioThree == correctAnswerThree){
            numberCorrect = numberCorrect+1;
        } else {
            questionRadioThree.setTextColor(this.getResources().getColor(R.color.wrong_answer));
        }
        correctAnswerThree.setTextColor(this.getResources().getColor(R.color.correct_answer));

        String answerText = questionFourAnswer.getText().toString().trim();
        if (Integer.parseInt(answerText) == 2) {
            numberCorrect = numberCorrect+ 1;
            questionFourAnswer.setTextColor(this.getResources().getColor(R.color.correct_answer));
        } else questionFourAnswer.setTextColor(this.getResources().getColor(R.color.wrong_answer));

        resultText.setText(String.valueOf(numberCorrect)+" "+this.getResources().getString(R.string.result_text));

    }
}
