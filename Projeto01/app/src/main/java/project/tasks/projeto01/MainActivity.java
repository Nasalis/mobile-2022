package project.tasks.projeto01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        Button button = findViewById(R.id.btnAddTask);

        button.setOnClickListener(this::addItem);

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        AutoCompleteTextView autocomplete = findViewById(R.id.autoCTask);
        autocomplete.setAdapter(itemsAdapter);

        setUpListViewAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main:
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                break;
            case R.id.action_group_1:
                System.out.println("Group menu clicado");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpListViewAdapter() {
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Context context = getApplicationContext();
            Toast.makeText(context, "Item removido", Toast.LENGTH_LONG).show();
            items.remove(i);
            itemsAdapter.notifyDataSetChanged();
            playSound();
            return true;
        });
    }

    private void playSound() {
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.paper);
        mp.setOnCompletionListener(MediaPlayer::release);
        mp.start();
    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.etTaskName);
        String taskName = input.getText().toString();

        if (!taskName.equals("")) {
            itemsAdapter.add(taskName);
            input.setText("");
            return;
        }

        Toast.makeText(getApplicationContext(), "Atividade não pode está vazio", Toast.LENGTH_LONG).show();
    }
}