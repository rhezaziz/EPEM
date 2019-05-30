package com.example.rheza.epem.Kategori2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rheza.epem.Kategori.Activity_kategori;
import com.example.rheza.epem.R;

public class Activity_pilih extends AppCompatActivity {
    Button kusen , bangunan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih);

        kusen = (findViewById(R.id.kusen));
        bangunan = (findViewById(R.id.bangunan));


        kusen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String kusen ;
                Intent intent = new Intent(Activity_pilih.this, Activity_kategori.class);
                intent.putExtra( Activity_kategori.MENU , "bangunan" );
                startActivity(intent);
            }
        });

        bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_pilih.this, Activity_kategori.class);
                intent.putExtra(Activity_kategori.MENU , "mebel");
                startActivity(intent);
            }
        });
    }
}
