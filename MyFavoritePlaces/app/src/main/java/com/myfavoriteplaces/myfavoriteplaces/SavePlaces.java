package com.myfavoriteplaces.myfavoriteplaces;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SavePlaces extends AppCompatActivity {

    final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_places);

        Intent i = getIntent();
        final String nom_modif = i.getStringExtra("nom_place");

        final EditText name = (EditText) findViewById(R.id.NomPlace);
        final Spinner type = (Spinner) findViewById(R.id.TypePlace);
        final EditText address = (EditText) findViewById(R.id.AdressePlace);
        final EditText description = (EditText) findViewById(R.id.DescriptionPlace);

        final Spinner TypePlace = (Spinner) findViewById(R.id.TypePlace);
        final List ChoixType = new ArrayList();
        ChoixType.add("Défaut");
        ChoixType.add("Boîte");
        ChoixType.add("Camping");
        ChoixType.add("Cinéma");
        ChoixType.add("Musée");
        ChoixType.add("Restaurant");
        final ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                ChoixType
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TypePlace.setAdapter(adapter);

        if(nom_modif != null){
            BDManager sav = new BDManager(this);
            sav.open();
            BD p = new BD("", "", "", "");
            p = sav.getPlace(nom_modif);

            name.setText(p.getNom_place());
            address.setText(p.getAddress_place());
            description.setText(p.getDescription_place());

            String stringSpinner = p.getType_place();
            ArrayAdapter Adapt = (ArrayAdapter) TypePlace.getAdapter();
            int spinnerPosition = Adapt.getPosition(stringSpinner);
            TypePlace.setSelection(spinnerPosition);
        }

        Button btnSupprimer= (Button)findViewById(R.id.btnSupprimer);
        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.remove((String) TypePlace.getSelectedItem());
                TypePlace.setAdapter(adapter);
            }
        });

        Button btnAjouter = (Button) findViewById(R.id.btnAjouter);

        btnAjouter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // get prompts.xml view
                LayoutInflater layoutInflater = LayoutInflater.from(context);

                View promptView = layoutInflater.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompts.xml to be the layout file of the alertdialog builder
                alertDialogBuilder.setView(promptView);

                final EditText input = (EditText) promptView.findViewById(R.id.userInput);

                // setup a dialog window
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                String nouveauType = input.getText().toString();
                                ChoixType.add(nouveauType);
                                final ArrayAdapter adapter = new ArrayAdapter(
                                        context,
                                        android.R.layout.simple_spinner_item,
                                        ChoixType
                                );
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                TypePlace.setAdapter(adapter);
                                int spinnerPosition = adapter.getPosition(nouveauType);
                                TypePlace.setSelection(spinnerPosition);
                            }
                        })
                        .setNegativeButton("Annuler",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alertD = alertDialogBuilder.create();

                alertD.show();

            }
        });

    }

    /** Called when the user clicks the Send button */
    public void savePlace(View view) {
        final EditText name = (EditText) findViewById(R.id.NomPlace);
        final Spinner type = (Spinner) findViewById(R.id.TypePlace);
        final EditText address = (EditText) findViewById(R.id.AdressePlace);
        final EditText description = (EditText) findViewById(R.id.DescriptionPlace);

        Intent i = getIntent();
        final String nom_modif = i.getStringExtra("nom_place");

        BDManager sav = new BDManager(this);
        sav.open();

        if(nom_modif != null){
            sav.modPlace(nom_modif, new BD(name.getText().toString(), type.getSelectedItem().toString(), address.getText().toString(), description.getText().toString()));
            Intent y = new Intent(SavePlaces.this, ListPlaces.class);
            startActivity(y);
            sav.close();

        }else{
            sav.addPlace(new BD(name.getText().toString() ,type.getSelectedItem().toString() , address.getText().toString(),description.getText().toString()));
            sav.close();
        }

        name.setText("");
        type.setSelection(0);
        address.setText("");
        description.setText("");
    }

    public void cancel(View view){
        final EditText name = (EditText) findViewById(R.id.NomPlace);
        final Spinner type = (Spinner) findViewById(R.id.TypePlace);
        final EditText address = (EditText) findViewById(R.id.AdressePlace);
        final EditText description = (EditText) findViewById(R.id.DescriptionPlace);

        Intent i = getIntent();
        final String nom_modif = i.getStringExtra("nom_place");

        if(nom_modif != null){
            Intent y = new Intent(SavePlaces.this, ListPlaces.class);
            startActivity(y);
        }else{
            name.setText("");
            type.setSelection(0);
            address.setText("");
            description.setText("");

            finish();
        }
    }

}
