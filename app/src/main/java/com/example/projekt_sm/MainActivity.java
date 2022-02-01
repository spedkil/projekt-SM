package com.example.projekt_sm;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.projekt_sm.API.ImageActivity;
import com.example.projekt_sm.helpers.DatabaseHelper;
import com.example.projekt_sm.image.FlowerClassificationActivity;
import com.example.projekt_sm.image.ImageClassificationActivity;
import com.example.projekt_sm.image.ObjectDetectionActivity;
public class MainActivity extends AppCompatActivity {
    Button getDataBtn;
    DatabaseHelper mdb;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdb = new DatabaseHelper(this);
        Button notifyBtn;

        createNotificationChannel();
        getDataBtn = findViewById(R.id.getDataButton);

            getDataBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor res = mdb.getAllData();
                    if (res.getCount() == 0){
                        showMessage("ERROR", "NO DATA FOUND");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("ID : " + res.getString(0) + " FILE PATH : " + res.getString(1) + "\n");
                        buffer.append(" RESULTS : "  + res.getString(2) + "\n\n");
                    }
                    showMessage("DATA",buffer.toString());
                }
            });


    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Notification");
        builder.setContentTitle(getString((R.string.notify_title)));
        builder.setContentText(getString(R.string.notify_text));
        builder.setSmallIcon(R.drawable.ic_notify);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
        managerCompat.notify(1, builder.build());
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }



    public void onGotoImageActivity(View view){
        Intent intent = new Intent(this, ImageClassificationActivity.class);
        startActivity(intent);
    }

    public void onGotoFlowerClassification(View view){
        Intent intent = new Intent(this, FlowerClassificationActivity.class);
        startActivity(intent);
    }
    public void onGotoObjectDetection(View view){
        Intent intent = new Intent(this, ObjectDetectionActivity.class);
        startActivity(intent);
    }
    public void onGoToImageDownload(View view){
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }

}