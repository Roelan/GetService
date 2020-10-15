package com.example.getservice.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getservice.R;
import com.example.getservice.adapters.NoteAdapter;
import com.example.getservice.data.NoteData;
import com.example.getservice.room.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.getservice.R.id.btn_delete_all_notes;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Note";

    private NoteViewModel noteViewModel;
    private EditText etField;
    private RecyclerView rvComments;
    private final NoteAdapter adapter = new NoteAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        // Recycler view
        rvComments = findViewById(R.id.rvData);
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        rvComments.setHasFixedSize(true);
        rvComments.setAdapter(adapter);

        Button btn = findViewById(R.id.button);
        etField = findViewById(R.id.myEditText);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveNote();
            }
        });

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getPosition(viewHolder.getAdapterPosition()));
                Log.d(TAG, "Note at position " + viewHolder.getAdapterPosition() + "deleted");
            }
        }).attachToRecyclerView(rvComments);
    }

    private void saveNote() {

        Date currentDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        String message = etField.getText().toString().trim();

        if (message.isEmpty()) {
            Toast.makeText(this, "Please enter the note", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Edit box is empty");
        } else {
            NoteData noteData = new NoteData(message, timeText);
            noteViewModel.insert(noteData);
            etField.setText("");
            Log.d(TAG, "Note saved");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case btn_delete_all_notes:
                noteViewModel.deleteAllNotes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}