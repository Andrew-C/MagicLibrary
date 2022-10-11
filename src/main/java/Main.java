import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {

    public static void main(String[] args) {
        // MongoDB access with environment variables
        Dotenv dotenv = Dotenv.configure().load();
        String uri = dotenv.get("MONGODB_PREFIX") + dotenv.get("MONGODB_USER") + ":" + dotenv.get("MONGODB_PASSWORD") + dotenv.get("MONGODB_CLUSTER");
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("MagicLibrary");
        MongoCollection urlCollection = mongoDatabase.getCollection("CardURL");

        /*Document document = new Document("Name", "Andrew");
        document.append("Sex", "Male");
        urlCollection.insertOne(document);*/

        // scrape testing
        final String url =
                "https://www.mtggoldfish.com/index/PLIST#paper";
        try {
            final org.jsoup.nodes.Document document = Jsoup.connect(url).get();

            for (Element row : document.select ("table tr")) {
                if(row.select("td:nth-of-type(1)").text() == "") {
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
                    String cardName = row.select("td:nth-of-type(1)").text();

                    Document doc = new Document("Card Name", cardName).append("URL", cardURL);
                    urlCollection.insertOne(doc);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}