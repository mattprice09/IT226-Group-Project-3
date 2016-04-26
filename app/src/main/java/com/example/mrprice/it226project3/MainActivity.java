package com.example.mrprice.it226project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.mrprice.it226project3.MESSAGE";


    public void setOneTime(View view)
    {
        //send user to one time alarm screen
        Intent intent = new Intent(this, CreateOneTimeAlarm.class);
    }

    public void setRecursive(View veiw)
    {

    }

    public void setLocation(View view)
    {

    }

    public void setTimer(View view)
    {
        //send user to timer screen
        Intent intent = new Intent(this, CreateTimer.class);
    }

    //example method, is not set up to set alarm
//    public void example(View view)
//    {
//        //intent is an object that can be used to start another activity.
//        //the keyword "this" is a reference to MainActivity
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//
//        //Creating a text field by finding edit_message text through
//        //main resource folder R (technically R.java file). Searching R by the text id which is
//        //edit_message. Remember: edit_message was created in activity_main.xml
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//
//        //The previous line took the text entered in the field and stored it
//        //in an EditText object.
//        //storing the text from the text field and storing
//        //it inside a string called message.
//        String message = editText.getText().toString();
//
//        //we are storing information to be passed to DisplayMessageActivity
//        //i think that message is being put into EXTRA_MESSAGE.
//        intent.putExtra(EXTRA_MESSAGE, message);
//
//        startActivity(intent);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
