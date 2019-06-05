package com.example.rheza.epem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Button login , daftar;
    MaterialEditText emailLogin , passwordLogin , emailDaftar , passwordDaftar;
    private LayoutInflater inflater;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        login = findViewById(R.id.Login);
        daftar = findViewById(R.id.daftar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLogin();
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDaftar();
            }
        });


    }

    private void dialogDaftar() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialog.setTitle("Daftar");

        dialog.setMessage("Jangan Pernah lupakan password anda");

       // LayoutInflater layoutInflater = LayoutInflater.from(this);

        View Daftar_layout = inflater.inflate(R.layout.activity_daftar, null);
        emailDaftar = Daftar_layout.findViewById(R.id.emailDaftar);
        passwordDaftar = Daftar_layout.findViewById(R.id.passDaftar);
        dialog.setView(Daftar_layout);



        dialog.setPositiveButton("Masuk", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this , Activity_admin.class);

                startActivity(intent);

            }
        });

        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogCancel, int which) {
                dialogCancel.dismiss();

            }
        });
        dialog.show();
    }

    private void dialogLogin() {
         dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialog.setTitle("Login");
        dialog.setMessage("Gunakan akun yang sudah terdaftar");

        //LayoutInflater layoutInflater = LayoutInflater.from(this);

        View Login_layout = inflater.inflate(R.layout.activity_login, null);


        emailLogin = Login_layout.findViewById(R.id.emailLogin);
        passwordLogin = Login_layout.findViewById(R.id.passLogin);


        dialog.setView(Login_layout);

        dialog.setPositiveButton("Masuk", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this , Activity_admin.class);

                startActivity(intent);
            }
        });

        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogCancel, int which) {
              dialogCancel.dismiss();
            }
        });
        dialog.show();
    }

}
