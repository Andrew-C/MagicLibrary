import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        // Connecting to MongoDB
        Dotenv dotenv = Dotenv.configure().load();
        String uri = dotenv.get("MONGODB_PREFIX") + dotenv.get("MONGODB_USER") + ":" + dotenv.get("MONGODB_PASSWORD") + dotenv.get("MONGODB_CLUSTER");
        MongoClientURI clientURI = new MongoClientURI(uri);

        MongoDatabase mongoDatabase;
        try (MongoClient mongoClient = new MongoClient(clientURI)) {
            mongoDatabase = mongoClient.getDatabase("MagicLibrary");
        }
        MongoCollection<Document> cardCollection = mongoDatabase.getCollection("CardData");

        // scrape testing
        final String listURL =
                "https://www.mtggoldfish.com/index/PLIST#paper";
        try {
            // Connect to the URL
            final org.jsoup.nodes.Document listDoc = Jsoup.connect(listURL).get();

            // Looks for every table row in document
            for (Element row : listDoc.select ("table tr")) {
                if (row.select("td:nth-of-type(1)").text().equals("")) {
                    continue;
                }
                else {
                    Elements aElement = row.select("td a");
                    // check that there is a link to a card and not just an empty box
                    String cardLink = aElement.attr("href");
                    if (cardLink.equals("")) {
                        continue;
                    }

                    String cardURL = ("mtggoldfish.com" + cardLink);
                    // TODO: finish parser and parse here
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}