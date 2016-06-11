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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_places);

        final EditText name = (EditText) findViewById(R.id.NomPlace);
        final Spinner type = (Spinner) findViewById(R.id.TypePlace);
        final EditText address = (EditText) findViewById(R.id.AdressePlace);
        final EditText description = (EditText) findViewById(R.id.DescriptionPlace);

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

        BDManager sav = new BDManager(this);
        sav.open();
        sav.addPlace(new BD(name.getText().toString() ,type.getSelectedItem().toString() , address.getText().toString(),description.getText().toString()));
        sav.close();

        name.setText("");
        type.setSelection(0);
        address.setText("");
        description.setText("");
    }

    public void addType(){

    }

    public void deleteType(){

    }

}
