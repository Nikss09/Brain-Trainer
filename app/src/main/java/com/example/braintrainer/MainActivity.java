package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout game;
    ConstraintLayout go;

    public void startGame(View view){
        game.setVisibility(View.VISIBLE);
        go.setVisibility(View.INVISIBLE);

        generate_question();
        start_timer();
    }

    public void generate_question(){
        ques1= new Random().nextInt(30)+1;
        ques2= new Random().nextInt(30)+1;
        correct_answer=ques1+ques2;

        question.setText(question_number+". "+ques1+" + "+ques2);

        potential_answers=new ArrayList<>();
        correct_location=new Random().nextInt(4);
        for(int i=0;i<4;i++){
            if(i==correct_location)
                potential_answers.add(correct_answer);
            else {
                int wrong=new Random().nextInt(60)+1;
                while(wrong==correct_answer){
                    wrong=new Random().nextInt(60)+1;
                }
                potential_answers.add(wrong);
            }
        }

        answer1.setText(""+potential_answers.get(0));
        answer2.setText(""+potential_answers.get(1));
        answer3.setText(""+potential_answers.get(2));
        answer4.setText(""+potential_answers.get(3));
    }

    public void start_timer(){
        new CountDownTimer(30000,1000){
            @Override
            public void onTick(long l) {
                l=l/1000;
                if((l%60)<10)
                    time.setText((l/60)+":0"+(l%60));
                else
                    time.setText((l/60)+":"+(l%60));

            }

            @Override
            public void onFinish() {
                time.setText("Over!!");
                result.setText("Final Score is "+correct+"/"+total);
                playAgain.setVisibility(View.VISIBLE);
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
            }
        }.start();
    }

    public void play_again(View view){
        question_number=1;
        correct=0;
        total=0;
        score.setText("0/0");
        start_timer();
        generate_question();
        result.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer4.setEnabled(true);
    }

    public void choose_answer(View view){
        total++;
        Button b = (Button)view;
        int ans=Integer.parseInt(b.getText().toString());
        if(ans==correct_answer){
            correct++;
            result.setText("Correct :)");
        }
        else
            result.setText("Incorrect :(");
        score.setText(correct+"/"+total);
        question_number++;
        generate_question();
    }

    int ques1;
    int ques2;
    int question_number=1;
    ArrayList<Integer> potential_answers;
    int correct_location;
    int correct_answer;

    TextView time;
    TextView question;
    TextView score;

    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    Button playAgain;

    TextView result;

    int correct=0;
    int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time=findViewById(R.id.time);
        question=findViewById(R.id.question);
        score=findViewById(R.id.score);

        answer1=findViewById(R.id.option1);
        answer2=findViewById(R.id.option2);
        answer3=findViewById(R.id.option3);
        answer4=findViewById(R.id.option4);
        playAgain=findViewById(R.id.playAgain);
        result=findViewById(R.id.result);

        game=(ConstraintLayout)findViewById(R.id.game);
        go=(ConstraintLayout)findViewById(R.id.go_Button);
    }
}