package com.myfavoriteplaces.myfavoriteplaces;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListPlaces extends AppCompatActivity {
    private NotesDbAdapter mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_places);

        Intent intent = getIntent();

        ListView listplace = (ListView) findViewById(R.id.affichage_listplace);

        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();
        fillData();
    }

    private void fillData() {
        ListView listplace = (ListView) findViewById(R.id.affichage_listplace);

        // Get all of the notes from the database and create the item list
        Cursor c = mDbHelper.fetchAllPlaces();
        startManagingCursor(c);

        String[] from = new String[] { NotesDbAdapter.KEY_TITLE };
        int[] to = new int[] { R.id.text1 };

        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter places =
                new SimpleCursorAdapter(this, R.layout.place_rows, c, from, to);
        listplace.setAdapter(places);
    }
}
