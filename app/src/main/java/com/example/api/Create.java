package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api.services.InfoServices;
import com.example.api.services.dataResponse.InfoResponse;
import com.example.api.services.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Create extends AppCompatActivity {

    EditText nombreTxt, usuarioTxt, rolTxt, passwordTxt;
    Button agregarBtn, volverBtn;
    Intent intent;

    Create context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        nombreTxt = findViewById(R.id.nombreTxt);
        usuarioTxt = findViewById(R.id.usuarioTxt);
        rolTxt = findViewById(R.id.rolTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        agregarBtn = findViewById(R.id.agregarBtn);
        volverBtn = findViewById(R.id.volverBtn);

        context = this;

        agregarBtn.setOnClickListener(v -> {
            if (nombreTxt.getText().toString().isEmpty() ||
                    usuarioTxt.getText().toString().isEmpty()||
                    passwordTxt.getText().toString().isEmpty() ||
                    rolTxt.getText().toString().isEmpty()){
                Toast.makeText(context, "Digite Todos los Valores", Toast.LENGTH_SHORT).show();
            }else {
                createUser(new User(
                        nombreTxt.getText().toString(),
                        usuarioTxt.getText().toString(),
                        passwordTxt.getText().toString(),
                        rolTxt.getText().toString())
                );
            }
        });

        volverBtn.setOnClickListener(v -> {
            intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });
    }

    private void createUser(User u) {
        Call<InfoResponse> respInfo = (new InfoServices().postInfoService(u));
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                Log.i("Info", "Conexión Establecida");
                Log.i("Info", "Usuario Creado");
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Log.i("Info", "Conexión Denegada");
                Log.i("Info", t.getCause().getMessage());
            }
        });
    }
}
