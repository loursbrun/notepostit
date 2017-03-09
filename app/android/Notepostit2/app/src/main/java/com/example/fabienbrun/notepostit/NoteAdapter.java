package com.example.fabienbrun.notepostit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fabienbrun on 09/03/2017.
 */

public class NoteAdapter extends BaseAdapter {

    @Override
    public long getItemId(int position) {
        Log.v("ltm", "getItemId(" + position + ")=");
        return tab_images_pour_la_liste[position];
    }


    @Override
    public Object getItem(int position) {
        Log.v("ltm", "getItem(" + position + ")");
        return position;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        Log.v("ltm", "getCount()");
        return tab_images_pour_la_liste.length;
    }

    private Integer[] tab_images_pour_la_liste = {
            R.drawable.etat1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6
    };

    String[] values = new String[]{"Device", "Géo localisation", "Accéléromètre",
            "Navigateur internet", "Dialogues", "Album photos"};

    Context _context;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.notelayout, parent, false);

        //if( convertView == null ) {
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        textView.setText(values[position]);

        imageView.setImageResource(tab_images_pour_la_liste[position]);
        /*}else {
            rowView = (View)convertView;
        }*/

        Log.v("ltm", "Position = " + position);

        return rowView;
    }

    public NoteAdapter(Context context) {
        super();
        _context = context;
    }

    public NoteAdapter(Context _context, String[] values) {
        this._context = _context;
        this.values = values;
    }

}

