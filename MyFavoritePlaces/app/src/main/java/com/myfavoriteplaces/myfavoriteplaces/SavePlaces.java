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

        final EditText name = (EditText) findViewById(R.id.NomPlace);
        final Spinner type = (Spinner) findViewById(R.id.TypePlace);
        final EditText address = (EditText) findViewById(R.id.AdressePlace);
        final EditText description = (EditText) findViewById(R.id.DescriptionPlace);

        final Spinner TypePlace = (Spinner) findViewById(R.id.TypePlace);
        List ChoixType = new ArrayList();
        ChoixType.add("Default");
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
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                String nouveauType = input.getText().toString();
                                adapter.add(nouveauType);
                                TypePlace.setAdapter(adapter);
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,	int id) {
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
