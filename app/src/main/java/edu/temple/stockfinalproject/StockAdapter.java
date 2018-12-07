package edu.temple.stockfinalproject;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static edu.temple.stockfinalproject.MainActivity.Stocks;


public class StockAdapter extends BaseAdapter {
Context context;
ArrayList<String> stocks = new ArrayList<String>();
    public StockAdapter(@NonNull Context context, int resource, ArrayList<String> stocks) {
        this.context=context;
        this.stocks=stocks;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.GREEN);
        textView.setText(stocks.get(position));
        textView.setTextSize(10);
        return textView;
    }
}
