package id.ac.poliban.e020320041.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.ac.poliban.e020320041.helloworldchallenge.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Hello World Challenge - Lenny");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}