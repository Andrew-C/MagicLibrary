
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class Main {

    public static void main(String[] args) {
        // Connecting to MongoDB
        Dotenv dotenv = Dotenv.configure().load();
        String uri = dotenv.get("MONGODB_PREFIX") + dotenv.get("MONGODB_USER") + ":" + dotenv.get("MONGODB_PASSWORD") + dotenv.get("MONGODB_CLUSTER");
        ConnectionString clientURI = new ConnectionString(uri);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        MongoDatabase mongoDatabase;
        MongoClient mongoClient;

        try {
            mongoClient = MongoClients.create(MongoClientSettings.builder()
                    .applyConnectionString(clientURI)
                    .codecRegistry(codecRegistry)
                    .build());
            mongoDatabase = mongoClient.getDatabase("MagicLibrary");

            MongoCollection<Card> cardCollection = mongoDatabase.getCollection("CardData", Card.class);

            // scrape testing
            final String listURL =
                    "https://www.mtggoldfish.com/index/PLIST#paper";
            // Connect to the URL
            final org.jsoup.nodes.Document listDoc = Jsoup.connect(listURL).get();

            // Looks for every table row in document
            for (Element row : listDoc.select("table tr")) {
                if (row.select("td:nth-of-type(1)").text().equals("")) {
                    continue;
                } else {
                    Elements aElement = row.select("td a");
                    // check that there is a link to a card and not just an empty box
                    String cardLink = aElement.attr("href");
                    if (cardLink.equals("")) {
                        continue;
                    }

                    String cardURL = ("https://www.mtggoldfish.com" + cardLink);
                    CardParser parser = new CardParser(cardURL);
                    Card card = parser.parse();

                    cardCollection.insertOne(card);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}