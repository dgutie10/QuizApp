package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup questionGroupOne;
    private RadioButton questionRadioOne;
    private RadioGroup questionGroupThree;
    private RadioButton questionRadioThree;
    private EditText questionFourAnswer;
    private Button submitButton;
    private TextView resultText;
    private CheckBox questionTwoA;
    private CheckBox questionTwoB;
    private CheckBox questionTwoC;
    private CheckBox questionTwoD;
    private int numberCorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionGroupOne = (RadioGroup) findViewById(R.id.group_one);
        questionTwoA = (CheckBox) findViewById(R.id.q2_a);
        questionTwoB = (CheckBox) findViewById(R.id.q2_b);
        questionTwoC = (CheckBox) findViewById(R.id.q2_c);
        questionTwoD = (CheckBox) findViewById(R.id.q2_d);
        questionGroupThree = (RadioGroup) findViewById(R.id.group_three);
        questionFourAnswer  = (EditText) findViewById(R.id.q4_a);

        submitButton = (Button) findViewById(R.id.submit_button);
        resultText = (TextView) findViewById(R.id.result_text);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetQuiz();
                checkAnswers();
            }
        });
    }

    private void checkAnswers(){
        numberCorrect = 0;

        int selectedIdOne = questionGroupOne.getCheckedRadioButtonId();
        questionRadioOne = (RadioButton) findViewById(selectedIdOne);
        if (questionRadioOne == null) {
            Toast.makeText(this, "Question one cannot be blank",Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton correctAnswerOne = (RadioButton) findViewById(R.id.q1_d);
        if (questionRadioOne == correctAnswerOne){
            numberCorrect = numberCorrect+1;
            questionRadioOne.setTextColor(this.getResources().getColor(R.color.reset_answer));
        } else {
            questionRadioOne.setTextColor(this.getResources().getColor(R.color.wrong_answer));
        }
        correctAnswerOne.setTextColor(this.getResources().getColor(R.color.correct_answer));

        if (!answerTwoCheck()) return;

        int selectedIdThree = questionGroupThree.getCheckedRadioButtonId();
        questionRadioThree = (RadioButton) findViewById(selectedIdThree);
        if (questionRadioThree == null) {
            Toast.makeText(this, "Question three cannot be blank",Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton correctAnswerThree = (RadioButton) findViewById(R.id.q3_d);
        if (questionRadioThree == correctAnswerThree){
            numberCorrect = numberCorrect+1;
        } else {
            questionRadioThree.setTextColor(this.getResources().getColor(R.color.wrong_answer));
        }
        correctAnswerThree.setTextColor(this.getResources().getColor(R.color.correct_answer));

        String answerText = questionFourAnswer.getText().toString().trim();
        if (TextUtils.isEmpty(answerText)){
            Toast.makeText(this, "Question four cannot be blank",Toast.LENGTH_SHORT).show();
            return;
        }

        if (Integer.parseInt(answerText) == 2) {
            numberCorrect = numberCorrect+ 1;
            questionFourAnswer.setTextColor(this.getResources().getColor(R.color.correct_answer));
        } else questionFourAnswer.setTextColor(this.getResources().getColor(R.color.wrong_answer));

        resultText.setText(String.valueOf(numberCorrect)+" "+this.getResources().getString(R.string.result_text));

    }

    private void resetQuiz(){
        if (questionRadioOne != null) questionRadioOne.setTextColor(this.getResources().getColor(R.color.reset_answer));
        if (questionRadioThree != null) questionRadioThree.setTextColor(this.getResources().getColor(R.color.reset_answer));
    }

    private boolean answerTwoCheck(){
        if (!questionTwoA.isChecked() && !questionTwoB.isChecked() && !questionTwoC.isChecked() && !questionTwoD.isChecked()) {
            Toast.makeText(this, "Question two cannot be blank", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!questionTwoA.isChecked() || !questionTwoB.isChecked() || !questionTwoC.isChecked() || !questionTwoD.isChecked()) {
            questionTwoA.setTextColor(this.getResources().getColor(R.color.wrong_answer));
            questionTwoB.setTextColor(this.getResources().getColor(R.color.wrong_answer));
            questionTwoC.setTextColor(this.getResources().getColor(R.color.wrong_answer));
            questionTwoD.setTextColor(this.getResources().getColor(R.color.wrong_answer));
        } else {
            questionTwoA.setTextColor(this.getResources().getColor(R.color.correct_answer));
            questionTwoB.setTextColor(this.getResources().getColor(R.color.correct_answer));
            questionTwoC.setTextColor(this.getResources().getColor(R.color.correct_answer));
            questionTwoD.setTextColor(this.getResources().getColor(R.color.correct_answer));
            numberCorrect = numberCorrect +1;
        }
        return true;



    }
}
