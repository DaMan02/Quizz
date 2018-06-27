package com.dayal.quizz;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseDB {

    private DatabaseReference quesRef;

    private List<Question> questionList;

    public FirebaseDB() {
       create();
    }

    public void create() {

        quesRef = FirebaseDatabase.getInstance().getReference("Questions");
         questionList = new ArrayList<>();
        addQuestions();
    }


    public void addQuestions() {

        Question q1 = new Question("Which company is the largest manufacturer of network equipment ?", "HP", "IBM", "CICSO", "CISCO");
        questionList.add(q1);
        Question q2 = new Question("Which of the following is NOT an operating system ?", "Linux", "BIOS", "DOS", "BIOS");
        questionList.add(q2);
        Question q3 = new Question("Who is the founder of Apple Inc. ?", "Jose Thomas", "Bill Gates", "Steve Jobs", "Steve Jobs");
        questionList.add(q3);
        Question q4 = new Question("Who is the first cricketer to score an international double century in 50-over match ?", "Ricky Ponting", "Sachin Tendulkar", "Brian Lara", "Sachin Tendulkar");
        questionList.add(q4);
        Question q5 = new Question("Which is the biggest largest city in the world ?", "Vienna", "Reno", "Delhi", "Reno");
        questionList.add(q5);
//        Question q6 = new Question("Which is the biggest desert in in the world ?", "Thar", "Sahara", "Mohave", "Sahara");
//        questionList.add(q6);
//        Question q7 = new Question("Which is the the largest coffee growing country in the world ?", "Brazil", "India", "Myanmar", "Brazil");
//        questionList.add(q7);
//        Question q8 = new Question("Which is the longest river in the world ?", "Ganga", "Amazon", "Nile", "Nile");
//        questionList.add(q8);
//        Question q9 = new Question("Which country is known as the country of copper ?", "Somalia", "Cameroon", "Zambia", "Zambia");
//        questionList.add(q9);
//        Question q10 = new Question("Which is the world's oldest known city ?", "Rome", "Damascus", "Jerusalem", "Damascus");
//        questionList.add(q10);

        addQuestionToDB(questionList);



    }

    public void addQuestionToDB(List<Question> questions) {

        quesRef.setValue(questions);

    }


    public List<Question> getAllQuestions() {
       final List<Question> questionList = new ArrayList<>();

           quesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                       Question q = postSnapshot.getValue(Question.class);
                       questionList.add(q);

                        Log.w("log",questionList.toString());
                                            }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        return questionList;
    }

}
