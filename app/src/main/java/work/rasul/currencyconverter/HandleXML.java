package work.rasul.currencyconverter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class HandleXML {

    private ArrayList<FeedMessage> feed;
    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    public HandleXML(String url){
        this.urlString = url;
    }

    public void parseXMLAndStoreIt(XmlPullParser parser) {
        int event;
        try {
            feed = new ArrayList<FeedMessage>();
            event = parser.getEventType();
            FeedMessage fm = new FeedMessage();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = null;
                switch (event){
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name == "item"){
                            fm = new FeedMessage();
                        } else if (fm != null){
                            if (name.equals("title")){
                                fm.setTitle(parser.nextText());
                            } else if (name.equals("pubDate")){
                                fm.setPubDate(parser.nextText());
                            } else if (name.equals("description")){
                                fm.setDescription(parser.nextText());
                            } else if (name.equals("quant")){
                                fm.setQuant(parser.nextText());
                            } else if (name.equals("index")){
                                fm.setIndex(parser.nextText());
                            } else if (name.equals("change")){
                                fm.setChange(parser.nextText());
                            } else if (name.equals("link")){
                                fm.setLink(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("item") && fm != null){
                            feed.add(new FeedMessage(fm.getTitle(),fm.getDescription()));
                        }
                        break;
                    default: break;
                }
                event = parser.next();
            }
            parsingComplete = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                }

                catch (Exception e) {
                }
            }
        });
        thread.start();
    }
    public ArrayList<FeedMessage> getFeed() {
        return feed;
    }

    public void setFeed(ArrayList<FeedMessage> feed) {
        this.feed = feed;
    }

}