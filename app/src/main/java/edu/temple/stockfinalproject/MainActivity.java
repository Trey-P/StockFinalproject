package edu.temple.stockfinalproject;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
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
        final StockAdapter2 adapter = new StockAdapter2(MainActivity.this,android.R.layout.simple_list_item_1,Stocks);
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
                        adapter.notifyDataSetChanged();
                        Log.i("ADD","Plus one for the Arraylist");
                        //Check the Status before Adding to the Array List!!
                        Thread t = new Thread() {
                            @Override
                            public void run() {

                                URL stockQuoteUrl;

                                try {

                                    //stockQuoteUrl = new URL("http://finance.yahoo.com/webservice/v1/symbols/" + stockSymbol + "/quote?format=json");
                                    stockQuoteUrl = new URL("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol="+Symbol);

                                    BufferedReader reader = new BufferedReader(
                                            new InputStreamReader(
                                                    stockQuoteUrl.openStream()));

                                    String response = "", tmpResponse;

                                    tmpResponse = reader.readLine();
                                    while (tmpResponse != null) {
                                        Log.i("READING", "READING INPUT");
                                        response = response + tmpResponse;
                                        tmpResponse = reader.readLine();
                                    }

                                    JSONObject stockObject = new JSONObject(response);
                                    Message msg = Message.obtain();
                                    msg.obj = stockObject;
                                    stockResponseHandler.sendMessage(msg);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.i("ERROR","No stock found");
                                }
                            }
                        };
                        t.start();

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


    Handler stockResponseHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {

            JSONObject responseObject = (JSONObject) msg.obj;

            try {
                Stock stock = new Stock(responseObject);

                        Log.i("SYMBOL",stock.getSymbol());
                        Log.i("NAME",stock.getName());
                       // Log.i("PRICE", (String)stock.getPrice());
            } catch (Exception e) {
                e.printStackTrace();
            }


            return false;
        }
    });
}

