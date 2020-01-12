package com.example.indoapps.printtouch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextInputEditText textInputEmail,textInputPassword;
    Button b1;
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInputEmail = findViewById(R.id.idUserName);
        textInputPassword = findViewById(R.id.idPassword);
        b1 = findViewById(R.id.button);
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });

    }


    public void onClickText(View view)
    {
        t1=findViewById(R.id.textView);
        t1.setClickable(true);
        Intent i=new Intent(MainActivity.this,registration_page.class);
        startActivity(i);
    }
    public void onForget(View view)
    {
        t2=findViewById(R.id.textView2);
        t2.setClickable(true);
        Intent j=new Intent(Intent.ACTION_VIEW);
        j.setData(Uri.parse("http://www.printtouch.co.in/Forget-Password"));
        startActivity(j);
    }

    private void login() {

        if (TextUtils.isEmpty(textInputEmail.getText().toString().trim()) || TextUtils.isEmpty(textInputPassword.getText().toString().trim())) {
            textInputEmail.setError("Field can't be Empty");
            textInputEmail.requestFocus();
            textInputPassword.setError("Field can't be Empty");
            textInputPassword.requestFocus();
        } else if (!emailValidator(textInputEmail.getText().toString())) {
            textInputEmail.setError("Please Enter Valid Email Address");
            textInputEmail.requestFocus();
        } else {
            Intent l = new Intent(MainActivity.this, CameraCapture.class);
            startActivity(l);


        }

    }
public static boolean emailValidator(String email)
{
    Pattern pattern;
    Matcher matcher;
    pattern=Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
    matcher=pattern.matcher(email);
    return matcher.matches();

}
    public void onBackPressed()
    {

        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("PrintTouch");
        builder.setMessage("Do You Want To Exit ?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();

            }
        });
        builder.show();
    }



}
