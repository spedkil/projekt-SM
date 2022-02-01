package com.example.projekt_sm.API;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.projekt_sm.R;

public class FullscreenImage extends AppCompatActivity {

    String mediumUrl = "";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);


        getSupportActionBar().hide();

        Intent intent = getIntent();
        mediumUrl = intent.getStringExtra("mediumUrl");
        imageView = findViewById(R.id.image_view);
        Glide.with(this).load(mediumUrl).into(imageView);

        Button download_btn;
        download_btn = findViewById(R.id.btnDownload);

        download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(mediumUrl);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadManager.enqueue(request);
                Toast.makeText(FullscreenImage.this, "Download started", Toast.LENGTH_SHORT).show();
            }
        });
    }
}