package com.example.activitynavegation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.activitynavegation.adapter.VacancyRecyclerViewAdapter;
import com.example.activitynavegation.dao.IPhotographerDAO;
import com.example.activitynavegation.dao.PhotographerDAO;
import com.example.activitynavegation.model.Photographer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        IPhotographerDAO photographerDAO = PhotographerDAO.getInstance(this);
        RecyclerView recyclerView = findViewById(R.id.vacancies);
        Button add = findViewById(R.id.add);
        Button edit = findViewById(R.id.edit);
        Button delete = findViewById(R.id.delete);
        EditText idPhotographer = findViewById(R.id.idVacancy);

        VacancyRecyclerViewAdapter adapter = new VacancyRecyclerViewAdapter(photographerDAO.getPhotographers());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(view -> {
            boolean wasAdded = photographerDAO.addPhotographer(new Photographer(idPhotographer.getText().toString(), "", ""));

            if (wasAdded && !idPhotographer.getText().toString().equals("")) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("id", idPhotographer.getText().toString());
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "Id ja existe ou input vazio", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Photographer vacancy : photographerDAO.getPhotographers()) {
                    if (vacancy.getId().equals(idPhotographer.getText().toString()) && !idPhotographer.getText().toString().equals("")) {
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("id", idPhotographer.getText().toString());
                        startActivity(intent);
                        return;
                    }
                }
                Toast.makeText(getApplicationContext(), "Id não existe ou input vazio", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Photographer vacancy : photographerDAO.getPhotographers()) {
                    if (vacancy.getId().equals(idPhotographer.getText().toString()) && !idPhotographer.getText().toString().equals("")) {
                        photographerDAO.removePhotographer(Integer.parseInt(idPhotographer.getText().toString()));
                    }
                }
                Toast.makeText(getApplicationContext(), "Id não existe ou input vazio", Toast.LENGTH_SHORT).show();
            }
        });
    }


}