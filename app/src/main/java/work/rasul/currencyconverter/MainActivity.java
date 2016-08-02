package work.rasul.currencyconverter;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

public class MainActivity extends Activity {

    private ArrayList <String> currencies;
    ListView feedList;
    Button b1,b2;
    private String finalUrl="http://www.nationalbank.kz/rss/rates_all.xml";
    private HandleXML obj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obj = new HandleXML(finalUrl);
        obj.fetchXML();
        while(obj.parsingComplete);
        Collections.sort(obj.getFeed(), new CustomComparator());
        final String[] titleArray = new String[obj.getFeed().size()];
        String[] descArray = new String[obj.getFeed().size()];
        for (int i = 0;i < obj.getFeed().size();i++) {
            titleArray[i] = obj.getFeed().get(i).getTitle();
            descArray[i] = obj.getFeed().get(i).getDescription();
        }
        CustomListAdapter adapter = new CustomListAdapter(this, titleArray, descArray);
        feedList = (ListView)findViewById(R.id.listView);
        feedList.setAdapter(adapter);
        feedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String selecteditem = titleArray[+position];
                Toast.makeText(getApplicationContext(), selecteditem, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public class CustomComparator implements Comparator<FeedMessage> {
        @Override
        public int compare(FeedMessage lhs, FeedMessage rhs) {
            return lhs.getTitle().compareTo(rhs.getTitle());
        }
    }

    public ArrayList<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<String> currencies) {
        this.currencies = currencies;
    }
}