package com.example.projekt_sm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projekt_sm.helpers.ImageHelperActivity;
import com.example.projekt_sm.image.FlowerClassificationActivity;
import com.example.projekt_sm.image.ImageClassificationActivity;
import com.example.projekt_sm.image.ObjectDetectionActivity;
import com.google.mlkit.vision.objects.ObjectDetection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notifyBtn;
        createNotificationChannel();
        notifyBtn = findViewById(R.id.notify_button);
        notifyBtn.setText("NOTIFY");
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);




        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notification code
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
        });

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