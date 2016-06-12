package com.myfavoriteplaces.myfavoriteplaces;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListPlaces extends AppCompatActivity {

    ListView mListView;
    private BDManager sav;
    private int mPlaceNumber = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_places);

        mListView = (ListView) findViewById(R.id.affichage_listplace);

        sav = new BDManager(this);
        sav.open();
        sav.getPlaces();

        displayListView();

        registerForContextMenu(mListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ListView mListview = (ListView) findViewById(R.id.affichage_listplace);
        Cursor SelectedTaskCursor = (Cursor) mListview.getItemAtPosition(info.position);
        final String SelectedTask = SelectedTaskCursor.getString(SelectedTaskCursor.getColumnIndex(BDManager.KEY_NOM_PLACE));
        String recherche = new String(SelectedTask.replace(' ','+'));
        switch (item.getItemId()) {
            case R.id.modifier:
                Intent i = new Intent(ListPlaces.this, SavePlaces.class);
                i.putExtra("nom_place", SelectedTaskCursor.getString(SelectedTaskCursor.getColumnIndex(BDManager.KEY_NOM_PLACE)));
                startActivity(i);
                return true;
            case R.id.maps:
                Uri location = Uri.parse("geo: 0,0?q="+recherche);
                startActivity(new Intent(Intent.ACTION_VIEW, location));
                return true;
            case R.id.supprimer:
                sav.suppPlace(SelectedTaskCursor.getString(SelectedTaskCursor.getColumnIndex(BDManager.KEY_NOM_PLACE)));
                displayListView();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void displayListView(){
        mListView = (ListView) findViewById(R.id.affichage_listplace);

        Cursor cursor = sav.getPlaces();

        String[] from = new String[]{ BDManager.KEY_NOM_PLACE };
        int[] to = new int[] { R.id.text1 };

        SimpleCursorAdapter places =
                new SimpleCursorAdapter(this, R.layout.place_rows, cursor, from, to);
        mListView.setAdapter(places);

    }

}
