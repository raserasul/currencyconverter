package work.rasul.currencyconverter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] titles;
    private final String[] descs;

    public CustomListAdapter(Activity context, String[] titleArray,String[] descArray) {
        super(context, R.layout.curlist, titleArray);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.titles=titleArray;
        this.descs=descArray;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.curlist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView desc = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(titles[position]);
        imageView.setImageResource(getImageId(context, titles[position]));
        desc.setText(""+descs[position]);
        return rowView;

    };

    public static int getImageId(Context context, String imageName) {
        if(imageName.equals("TRY"))
            imageName="turk";
        return context.getResources().getIdentifier("drawable/" + imageName.toLowerCase(), null, context.getPackageName());
    }
}