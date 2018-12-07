package edu.temple.stockfinalproject;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StockAdapter2 extends ArrayAdapter {
    public Context context;
    public ArrayList<String> Stock = new ArrayList<String>();
    public StockAdapter2(@NonNull Context context, int resource, ArrayList<String> stocks) {
        super(context, resource, stocks);
        this.context =context;
        this.Stock=stocks;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        try {
            TextView textView = (TextView) super.getView(position,convertView,parent);
            textView.setBackgroundColor(Color.GREEN);
            textView.setText(Stock.get(position));
            return textView;
        }
        catch (NullPointerException e)
        {
            Log.i("Error","Karl Morris is laughing at us");
        }

        return getView(position,convertView,parent);
    }
}
