import com.mongodb.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    public static MongoClient mongoClient;
    public static DB database;
    public static DBCollection collection;

    public static void main(String[] args) {

        final String url =
                "https://scryfall.com/card/cmr/365/aesi-tyrant-of-gyre-strait";
        try {
            final Document document = Jsoup.connect(url).get();
            Elements ele = document.select("div.card-text");
            System.out.println(ele.select("h1.card-text-title").text());
            System.out.println(ele.select("div.card-text-box").text());
            System.out.println(ele.select("div.card-text-stats").text());

            /*for (Element ele : document.select ("div.card-text")) {
                final String information = ele.select("div.card-text-box").text();
                System.out.println(information);
            }*/
            // System.out.println(document.outerHtml());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        /*mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:28717"));
        database = (DB) mongoClient.getDatabase("MagicLibrary");
        collection = database.getCollection("test");*/
        // DBObject obj = new BasicDBObject("employee", "bob").append("items", new int[]).append("table", "table5");
    }
}