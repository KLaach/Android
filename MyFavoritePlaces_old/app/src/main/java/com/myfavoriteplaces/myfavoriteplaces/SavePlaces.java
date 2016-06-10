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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SavePlaces extends AppCompatActivity {

    private NotesDbAdapter mDbHelper;
    private int mNoteNumber = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_places);

        final EditText name = (EditText) findViewById(R.id.NomPlace);
        final Spinner type = (Spinner) findViewById(R.id.TypePlace);
        final EditText adresse = (EditText) findViewById(R.id.AdressePlace);
        final EditText description = (EditText) findViewById(R.id.DescriptionPlace);

        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();
        fillData();

        Spinner TypePlace = (Spinner) findViewById(R.id.TypePlace);
        List ChoixType = new ArrayList();
        ChoixType.add("Default");
        ChoixType.add("Boîte");
        ChoixType.add("Camping");
        ChoixType.add("Cinéma");
        ChoixType.add("Musée");
        ChoixType.add("Restaurant");
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                ChoixType
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TypePlace.setAdapter(adapter);


    }

    /** Called when the user clicks the Send button */
    public void savePlace(View view) {
        final EditText name = (EditText) findViewById(R.id.NomPlace);
        final Spinner type = (Spinner) findViewById(R.id.TypePlace);
        final EditText address = (EditText) findViewById(R.id.AdressePlace);
        final EditText description = (EditText) findViewById(R.id.DescriptionPlace);

        Intent intent = new Intent(SavePlaces.this, ListPlaces.class);
        intent.putExtra("name" , name.getText().toString());
        intent.putExtra("type", type.getSelectedItem().toString());
        intent.putExtra("address", address.getText().toString());
        intent.putExtra("description", description.getText().toString());
        startActivity(intent);

        createPlace(name.getText().toString(), type.getSelectedItem().toString(), address.getText().toString(), description.getText().toString());

        name.setText("");
        type.setSelection(0);
        address.setText("");
        description.setText("");
    }

    private void fillData(){
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

    private void createPlace(String title, String type, String adresse, String description) {
        mDbHelper.createPlace(title, type, adresse, description);
        fillData();
    }
}
