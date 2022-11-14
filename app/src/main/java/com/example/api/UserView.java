package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api.services.InfoServices;
import com.example.api.services.dataResponse.InfoResponse;
import com.example.api.services.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserView extends AppCompatActivity {

    TextView numeroID, nombreAuto, usuarioAuto, rolAuto;
    Button actualizarBtn, eliminarBtn, volverBtn2;
    Intent intent;

    UserView context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        numeroID = findViewById(R.id.numeroID);
        nombreAuto = findViewById(R.id.nombreAuto);
        usuarioAuto = findViewById(R.id.usuarioAuto);
        rolAuto = findViewById(R.id.rolAuto);
        actualizarBtn = findViewById(R.id.actualizarBtn);
        volverBtn2 = findViewById(R.id.volverBtn2);
        eliminarBtn = findViewById(R.id.eliminarBtn);

        context = this;

        String id = getIntent().getStringExtra("usuariosId");
        numeroID.setText(id);
        nombreAuto.setText(getIntent().getStringExtra("usuariosName"));
        usuarioAuto.setText(getIntent().getStringExtra("usuariosUser"));
        rolAuto.setText(getIntent().getStringExtra("usuariosRol"));

        volverBtn2.setOnClickListener(v -> {
            intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });


        eliminarBtn.setOnClickListener(v -> {
            deleteUser(id);
            intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });

        actualizarBtn.setOnClickListener(v -> {
            if (usuarioAuto.getText().toString().isEmpty() ||
                nombreAuto.getText().toString().isEmpty() ||
                rolAuto.getText().toString().isEmpty()) {
                Toast.makeText(context, "Digite Todos los Campos", Toast.LENGTH_SHORT).show();
            }else {
                updateUser(id, new User(
                        nombreAuto.getText().toString(),
                        usuarioAuto.getText().toString(),
                        "0", rolAuto.getText().toString())
                );
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void deleteUser(String i) {
        Call<InfoResponse> respInfo = (new InfoServices().deleteInfoService(i));
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                Log.i("Info", "Conexión Establecida");
                Log.i("Info", "Usuario Eliminado");
            }
            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Log.i("Info", "Conexión Denegada");
                Log.i("Info", t.getCause().getMessage());
            }
        });
    }

    private void updateUser(String i, User u) {
        Call<InfoResponse> respInfo = (new InfoServices().updateInfoService(i, u));
        respInfo.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                Log.i("Info", "Conexión Establecida");
                Log.i("Info", "Usuario Actualizado");
            }
            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Log.i("Info", "Conexión Denegada");
                Log.i("Info", t.getCause().getMessage());
            }
        });
    }
}
