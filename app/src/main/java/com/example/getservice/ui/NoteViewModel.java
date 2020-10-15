package com.example.getservice.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.getservice.App;
import com.example.getservice.data.NoteData;
import com.example.getservice.room.Note;
import com.example.getservice.room.NoteRepository;

import java.util.List;

import javax.inject.Inject;

public class NoteViewModel extends AndroidViewModel {

    private static final String TAG = "Note";

    @Inject
    public NoteRepository repository;

    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        ((App) getApplication()).getAppComponent().inject(this);
        allNotes = repository.getAllNotes();
    }

    public void insert(NoteData noteData) {
        Note note = new Note(noteData.getMessage(), noteData.getTime());
        repository.insert(note);
        Log.d(TAG, "Insert one Note");
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
        Log.d(TAG, "One Note is delete");
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
        Log.d(TAG, "All Note are delete");
    }

    public LiveData<List<Note>> getAllNotes() {
        Log.d(TAG, "Get all notes");
        return allNotes;
    }
}