package com.dayal.quizz;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.CountDownTimer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
         import android.view.View;
        import android.widget.Button;
        import android.widget.Chronometer;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private Button prevBtn;
    private Button nextBtn;
    private Button submitBtn;
    private List<Question> questionList;
    private int score = 0;
    private int quid = 0;
    private Question currentQuestion;

    private ProgressDialog progressDialog;
    private static int flag = 0;

    private TextView txtQuestion;
    private TextView timer;
    private RadioButton option1,option2,option3,option4;
     Chronometer chronometer= null;

    private static final long TIMER_IN_MILIS = 120000;
    private static CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionList = new ArrayList<>();

        prevBtn = (Button) findViewById(R.id.prevBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        submitBtn = (Button) findViewById(R.id.submit_btn);
        timer = (TextView) findViewById(R.id.timerID);

        txtQuestion = (TextView) findViewById(R.id.quesID);
        option1 = (RadioButton) findViewById(R.id.radioButton1);
        option2 = (RadioButton) findViewById(R.id.radioButton2);
        option3 = (RadioButton) findViewById(R.id.radioButton3);
        option4 = (RadioButton) findViewById(R.id.radioButton4);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Quiz...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        FirebaseDB db = new FirebaseDB();
        questionList = db.getAllQuestions();
//        chronometer = (Chronometer)findViewById(R.id.chronometer);
//        chronometer.setBase(SystemClock.elapsedRealtime());

            countDownTimer = new CountDownTimer(TIMER_IN_MILIS, 1000) {

                public void onTick(long millisUntilFinished) {
                    long millis = millisUntilFinished;
                    String ms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.
                            toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    timer.setText(ms);//set text
                }

                public void onFinish() {
                    timer.setText("Time over !");
                    showResult();
                }
            };


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextBtnClick();
            }
        });
            prevBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPrevBtnClick();
                }
            });

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showResult();
                }
            });

        }



    private void onPrevBtnClick() {
        quid--;
        setQuestionView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            currentQuestion = questionList.get(quid);

        }catch(Exception e){
            e.printStackTrace();
        }

        setQuestionView();

    }


    private void setQuestionView() {
//        if(quid == 0){
        prevBtn.setVisibility(View.INVISIBLE);
//        }else{
//            prevBtn.setVisibility(View.VISIBLE);
//        }
        if (!questionList.isEmpty()) {

            if (flag == 0) {
                countDownTimer.start();
                flag++;
            }


            progressDialog.dismiss();
            txtQuestion.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOptA());
            option2.setText(currentQuestion.getOptB());
            option3.setText(currentQuestion.getOptC());
            option4.setText("None of these");
            quid++;
            Log.w("thread", "thread");
        }


    }

    protected void showResult(){

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("score",score);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
    public void onNextBtnClick() {
        RadioGroup grp = (RadioGroup) findViewById(R.id.radiogpID);

        int selectID = grp.getCheckedRadioButtonId();
        if (selectID != -1) {
            RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());

            if (currentQuestion.getAnswer().equals(answer.getText())) {
                score++;
                Log.w("log", "Radio Button id: " + grp.getCheckedRadioButtonId());
                Log.w("log", "Your score: " + score);
            }
        }
            grp.clearCheck();

            if (quid < 5) {
                currentQuestion = questionList.get(quid);
                setQuestionView();
            } else {

                showResult();
            }

    }

  //
//        if (showElapsedTime() == 6000) {
//            chronometer.stop();
//            showResult();


}










