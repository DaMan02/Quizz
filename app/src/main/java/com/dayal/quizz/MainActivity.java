package com.dayal.quizz;

        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

public class MainActivity extends AppCompatActivity  implements MainFragment.MainFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void buttonClicked() {

        MainFragment mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.frag_id);

        QuestionsFragment qFragment = new QuestionsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.remove(mainFragment);
        ft.replace(R.id.frag_container,qFragment)
           .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
           .commit();


    }
}










