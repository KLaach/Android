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

    ListView mListView;
    BDManager sav;
    SimpleCursorAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_places);

        mListView = (ListView) findViewById(R.id.affichage_listplace);

        sav = new BDManager(this);
        sav.open();
        sav.getPlaces();

        displayListView();
    }

    private void displayListView(){
        Cursor cursor = sav.getPlaces();

        String[] columns = new String[]{
                BDManager.KEY_NOM_PLACE
        };

        int[] to = new int[] {
                R.id.NomPlace
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.list_places,
                cursor,
                columns,
                to,
                0
        );

        mListView = (ListView) findViewById(R.id.affichage_listplace);
        mListView.setAdapter(dataAdapter);

    }

}
