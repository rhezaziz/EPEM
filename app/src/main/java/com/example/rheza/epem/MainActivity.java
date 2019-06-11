package com.example.rheza.epem;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button login , daftar;
    EditText emailLogin , passwordLogin ;
    private LayoutInflater inflater;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.btn_login);
        emailLogin=findViewById(R.id.et_email);
        passwordLogin=findViewById(R.id.et_password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLogin.getText().toString();
                String password = passwordLogin.getText().toString();

                if(email.isEmpty()){
                    emailLogin.setError("Email tidak boleh kosong");
                }else if(password.isEmpty()){
                    passwordLogin.setError("Password tidak boleh kosong");
                }else{
                    login(email, password);
                }
            }
        });

    }

    public void login(String email, String password){
        Call<Value> call =RetrofitServer.getInstance().RegisterApi().Login(email, password);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                if(response.body().value.equals("1")){
                    new android.app.AlertDialog.Builder(MainActivity.this).setTitle("Info").setMessage("Login berhasil").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, Activity_admin.class);
                                    startActivity(intent);
                                }
                            }).show();
                }else{
                    new android.app.AlertDialog.Builder(MainActivity.this).setTitle("Info").setMessage(response.body().getMessage()).setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
