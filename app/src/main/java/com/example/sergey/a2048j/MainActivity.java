package com.example.sergey.a2048j;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    int size;
    private ImageView lover;
    private ImageView higher;
    private ImageView sizePicture;
    private TextView sizeText;
    private int min;
    private int max;
    private User2048 user;
    private Score score;
    private PlayField field;
    final String LOG_TAG = "myLogs";
    private static int record = 0;

    {
        min = 4;
        max = 8;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        size = 4;
        lover = (ImageView)findViewById(R.id.lover);
        higher = (ImageView)findViewById(R.id.higher);
        sizePicture = (ImageView)findViewById(R.id.field);
        sizeText = (TextView) findViewById(R.id.sizeText);
        final Button playButton = (Button) findViewById(R.id.playButton);

        lover.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (size == 8) {
                    size--;
                }
                if (size > min) {
                    size--;
                }
                setSizePictureAndText();
            }
        });

        higher.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (size == 6) {
                    size++;
                }
                if (size < max) {
                    size++;
                }
                setSizePictureAndText();
            }
        });

        playButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //field = new PlayField(size);
                try {
                    user = new User2048("jim", "jim", "jim", "jim");
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                Intent playIntent = new Intent(MainActivity.this, PlayActivity.class);
                playIntent.putExtra(User2048.class.getCanonicalName(), user);
                Log.d(LOG_TAG, "startActivity");
                playIntent.putExtra("size", "" + size);
                startActivity(playIntent);
            }
        });

    }

    public void setSizePictureAndText(){
        switch (size){
            case 4:
                sizePicture.setImageResource(R.drawable.f4x4);
                sizeText.setText(R.string.f4x4);
                break;
            case 5:
                sizePicture.setImageResource(R.drawable.f5x5);
                sizeText.setText(R.string.f5x5);
                break;
            case 6:
                sizePicture.setImageResource(R.drawable.f6x6);
                sizeText.setText(R.string.f6x6);
                break;
            case 8:
                sizePicture.setImageResource(R.drawable.f8x8);
                sizeText.setText(R.string.f8x8);
                break;
            default:
                break;
        }
    }

    public static void setRecord(int record){
        MainActivity.record = record;
    }


    // Saves new record in
    private void saveNewRecord(){
        if (MainActivity.record != 0) {
            DBRecordWorker worker = new DBRecordWorker(this);
            SQLiteDatabase dbw = worker.getWritableDatabase();
            //ContentValues cv = new ContentValues();
            //cv.put("record", user.getField().getScore().getScore());
            //dbw.insert(worker.TABLE_NAME, null, cv);
            dbw.execSQL("UPDATE " + worker.TABLE_NAME + " SET record = "
                    + MainActivity.record + " WHERE id = 1");
            dbw.close();
        }
    }
}
