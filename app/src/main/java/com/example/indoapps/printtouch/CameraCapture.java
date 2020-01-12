package com.example.indoapps.printtouch;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;


public class CameraCapture extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity" ;
    public Toolbar toolbar;
    public TabLayout tabLayout;
    public ViewPager viewPager;
    FloatingActionButton floatingActionButton;
    TextView t1;
    ImageView iv1;
    Button b1, b2;
    final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    private int RESULT_LOAD_IMAGE = 1;
    private static final int HALF = 2;


    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.cameracapture);
        toolbar = findViewById(R.id.toolbar);
        floatingActionButton = findViewById(R.id.idcam);
        setSupportActionBar(toolbar);
        iv1 = findViewById(R.id.imageV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"),RESULT_LOAD_IMAGE);

        } else if (id == R.id.item2) {
            Intent k = new Intent(Intent.ACTION_VIEW);
            k.setData(Uri.parse("http://www.printtouch.co.in"));
            startActivity(k);
        } else if (id == R.id.item3) {
            Intent l = new Intent(Intent.ACTION_VIEW);
            l.setData(Uri.parse("http://www.printtouch.co.in/contact"));
            startActivity(l);
        } else if (id == R.id.item4) {
            Intent m = new Intent(Intent.ACTION_VIEW);
            m.setData(Uri.parse("http://www.printtouch.co.in/about"));
            startActivity(m);

        }
        return true;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("DEBUG", "onActivityResult called");

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Log.d("DEBUG", "result is ok");
            String filename = "printTouch.png";
            File sd = Environment.getExternalStorageDirectory();
            File dest = new File(sd, filename);
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageView imageVi = findViewById(R.id.imageV);
            imageVi.setImageBitmap(image);
            try {
                FileOutputStream out = new FileOutputStream(dest);
                image.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Snackbar.make(floatingActionButton, "Image Captured", Snackbar.LENGTH_SHORT).show();
        }

      else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data ) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imageV);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            }



        else if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED) {
            Snackbar.make(floatingActionButton, "Image Not Captured", Snackbar.LENGTH_SHORT).show();
        }

    }




    public void callU(View view)
    {
        t1=findViewById(R.id.callUser);
        t1.setClickable(true);
        Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:+919878935589"));
        startActivity(i);
    }
    public void openSite(View view1)
    {
        iv1=findViewById(R.id.imageView2);
        iv1.setClickable(true);
        Intent j=new Intent(Intent.ACTION_VIEW);
        j.setData(Uri.parse("http://www.printtouch.co.in"));
        startActivity(j);

    }
    public void uploadFile(View view2)
    {
        b2=findViewById(R.id.buttonUp);



    }
    public void cancelFile(View view3)
    {
        b1=findViewById(R.id.buttonCan);
        ImageView imgv=findViewById(R.id.imageV);
        imgv.setImageDrawable(null);
        Snackbar.make(b1,"Image Reset",Snackbar.LENGTH_SHORT).show();

    }
}