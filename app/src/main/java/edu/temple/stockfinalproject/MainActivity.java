package edu.temple.stockfinalproject;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   FloatingActionButton Fab;
    String Symbol;
    ListView Portfolio;
    TextView Welcome;
    public static ArrayList<String> Stocks = new ArrayList<String>();
    Boolean Stockadded=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fab=findViewById(R.id.FAB);
        Welcome=findViewById(R.id.textView);
        Portfolio=findViewById(R.id.listView);
        StockAdapter2 adapter = new StockAdapter2(MainActivity.this,android.R.layout.simple_list_item_1,Stocks);
        Portfolio.setAdapter(adapter);
        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Stock Symbol Search");

                final EditText entry = new EditText(MainActivity.this);
                entry.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                builder.setView(entry);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Symbol = entry.getText().toString();
                        Stocks.add(Symbol.toUpperCase());
                        Welcome.setText("");
                        //adapter.notifyDataSetChanged();
                        /*
                        JSON Parsed
                        If Successful-> Check if stock price is >= Opening
                            If yes -> Green
                            Else -> Red


                         */

                        Log.i("ADD","Plus one for the Arraylist");
                        //Check the Status before Adding to the Array List!!


                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
}
