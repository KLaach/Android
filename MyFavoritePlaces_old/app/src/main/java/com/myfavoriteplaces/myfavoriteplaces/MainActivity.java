package com.myfavoriteplaces.myfavoriteplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton SavePlaces;
    ImageButton ListPlaces;
    ImageButton About;
    ImageButton Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addListenerOnButtonSavePlaces();
        addListenerOnButtonListPlaces();
        addListenerOnButtonAbout();
        addListenerOnButtonExit();
    }

    public void addListenerOnButtonSavePlaces() {

        SavePlaces = (ImageButton) findViewById(R.id.SavePlaces);
        SavePlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Sauvegarder une place", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SavePlaces.class);
                startActivity(intent);


            }
        });


    }

    public void addListenerOnButtonListPlaces() {
        ListPlaces = (ImageButton) findViewById(R.id.ListPlaces);
        ListPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Liste des places", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ListPlaces.class);
                startActivity(intent);
            }
        });
    }

    public void addListenerOnButtonAbout() {
        About = (ImageButton) findViewById(R.id.About);
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"About", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });
    }

    public void addListenerOnButtonExit() {
        Exit = (ImageButton) findViewById(R.id.Exit);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Exit", Toast.LENGTH_SHORT).show();
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
