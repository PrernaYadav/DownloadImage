package com.starlingsoftwares.downloadimage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button click;
    ImageView imageView;
    boolean success = false;
    Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = findViewById(R.id.click);
        imageView = findViewById(R.id.image);
        Picasso.get().load("https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg?resize=640%2C426").into(imageView);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Picasso.get()
                        .load("https://i2.wp.com/beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg?resize=640%2C426")
                        .into(new Target() {
                                  @Override
                                  public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                      try {
                                          String root = Environment.getExternalStorageDirectory().toString();
                                          File myDir = new File(root + "/prerna");

                                          if (!myDir.exists()) {
                                              myDir.mkdirs();
                                          }

                                          String name = new Date().toString() + ".jpg";
                                          Log.i("prerna",""+name);


                                          myDir = new File(myDir, name);
                                          FileOutputStream out = new FileOutputStream(myDir);
                                          bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

                                          out.flush();
                                          out.close();
                                      } catch (Exception e) {
                                          // some action
                                      }
                                  }

                                  @Override
                                  public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                  }

                                  @Override
                                  public void onPrepareLoad(Drawable placeHolderDrawable) {
                                  }
                              }
                        );



              /*  BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                // path to /data/data/yourapp/app_data/imageDir
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

                // Create imageDir
                File mypath=new File(directory,"profile.jpg");

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mypath);
                    Log.i("prerna",""+mypath);

                    // Use the compress method on the BitMap object to write image to the OutputStream
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/


            }
        });


    }

}
