package com.example.activitynavegation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitynavegation.dao.IPhotographerDAO;
import com.example.activitynavegation.dao.PhotographerDAO;
import com.example.activitynavegation.model.Photographer;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);

        Photographer currentPhotographer = new Photographer();
        IPhotographerDAO photographerDAO = PhotographerDAO.getInstance(this);
        Button save = findViewById(R.id.save);
        Button cancel = findViewById(R.id.cancel);
        TextView vacancyId = findViewById(R.id.id);
        TextView newPhotographerName = findViewById(R.id.newPhotographerName);
        TextView newPhotographerDescription = findViewById(R.id.newPhotographerDescription);

        Bundle data = getIntent().getExtras();
        String newPhotographerId = data.getString("id");

        for (Photographer photographer : photographerDAO.getPhotographers()) {
            if (photographer.getId().equals(newPhotographerId)) {
                currentPhotographer = photographer;
            }
        }

        vacancyId.setText(currentPhotographer.getId());
        newPhotographerName.setText(currentPhotographer.getName());
        newPhotographerDescription.setText(currentPhotographer.getDescription());

        Photographer finalCurrentcurrentPhotographer = currentPhotographer;
        save.setOnClickListener(view -> {
            photographerDAO.editPhotographer(new Photographer(newPhotographerId, newPhotographerName.getText().toString(),
                    newPhotographerDescription.getText().toString()), finalCurrentcurrentPhotographer);

            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });

        cancel.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Ação cancelada", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });
    }
}