package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.api.services.InfoServices;
import com.example.api.services.dataResponse.InfoResponse;
import com.example.api.services.models.InfoApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView usuariosList;
    Button agregarUserBtn;

    ArrayList<String> listaUsuarios;
    Intent intent;
    InfoApi user;

    MainActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuariosList = findViewById(R.id.usuariosList);
        agregarUserBtn = findViewById(R.id.agregarUserBtn);

        context = this;

        cargarUsuario(0);

        agregarUserBtn.setOnClickListener(v -> {
            intent = new Intent(context, Create.class);
            startActivity(intent);
        });

        usuariosList.setOnItemClickListener((adapterView, view, i, l) -> {
            String[] cadenas = usuariosList.getItemAtPosition(i).toString().split(" - ");
            intent = new Intent(context, UserView.class);
            intent.putExtra("usuariosId", cadenas[0]);
            intent.putExtra("usuariosName", cadenas[1]);
            intent.putExtra("usuariosUser", cadenas[2]);
            intent.putExtra("usuariosRol", cadenas[3]);
            startActivity(intent);
        });
    }

    private void cargarUsuario(int id) {
        Call<InfoResponse> inforesponse = (new InfoServices().getInfoService());
        inforesponse.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                Log.i("Info", "Conexión Establecida");
                listaUsuarios = new ArrayList<>();
                InfoResponse respon = response.body();
                for (int i = 0; i < respon.data.size(); i++) {
                    if (id == -1) {
                        user = new InfoApi(
                                respon.data.get(i).getId(),
                                respon.data.get(i).getNames(),
                                respon.data.get(i).getUsername(),
                                respon.data.get(i).getRol()
                        );
                        listaUsuarios.add(user.toString());
                    } else if (respon.data.get(i).getId() == id) {
                        user = new InfoApi(
                                respon.data.get(i).getId(),
                                respon.data.get(i).getNames(),
                                respon.data.get(i).getUsername(),
                                respon.data.get(i).getRol()
                        );
                        listaUsuarios.add(user.toString());
                    }
                }
                if (listaUsuarios.isEmpty()){
                    Toast.makeText(context, "No se encuentra el Usuario #"+id, Toast.LENGTH_SHORT).show();
                }
                usuariosList.setAdapter(new ArrayAdapter(context, android.R.layout.simple_list_item_1, listaUsuarios));
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Log.i("Info", "Conexión Denegada");
                Log.i("Info", t.getCause().getMessage());
            }
        });
    }
}