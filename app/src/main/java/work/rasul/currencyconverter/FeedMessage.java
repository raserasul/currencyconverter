package work.rasul.currencyconverter;

/**
 * Created by rasul@activater on 01-Aug-16.
 */
/*
 * Represents one RSS message
 */
public class FeedMessage {

    String title;
    String pubDate;
    String description;
    String quant;
    String index;
    String change;
    String link;

    public FeedMessage() {
        super();
    }

    public FeedMessage(String title, String description){
        this.title = title;
        this.description = description;
    }

    public FeedMessage(String title, String pubDate, String description, String quant, String index, String change, String link) {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.quant = quant;
        this.index = index;
        this.change = change;
        this.link = link;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}