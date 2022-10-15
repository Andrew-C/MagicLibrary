import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CardParser {
    Document cardDoc;

    public CardParser() {

    }

    public CardParser(String URL) throws IOException {
        cardDoc = Jsoup.connect(URL).get();
    }

    public Card parse() throws IOException {
        // Grab card name
        // Elements cardContainer = cardDoc.select("div.gatherer-container");
        String name = cardDoc.select("h3.gatherer-name").text();
        Elements cardData = cardDoc.select("div.collapse");

        // Grab card type
        Elements cardType = cardDoc.select("p.gatherer-type");
        String type = cardType.text();

        // Grab card power/toughness/loyalty
        String powerVal = "";
        if (type.contains("Creature") || type.contains("Planeswalker")) {
            Elements cardPower = cardDoc.select("div.gatherer-power");
            powerVal = cardPower.text();
        }

        // Grab card mana cost
        String manaCost = "";
        int cmc = 0;
        if (!type.contains("Land")) {
            Elements manaCostContainer = cardDoc.select(".gatherer-container .manacost");
            String manaCostText = manaCostContainer.attr("aria-label");
            // Only interested in the mana cost text after the ":"
            int splitter = manaCostText.indexOf(':');
            //try {
                manaCost = manaCostText.substring(splitter + 2);
                cmc = calculateCMC(manaCost);
            /*}
            catch (Exception e) {
                System.out.println(e);
                System.out.println(name);
                System.out.println(manaCostText);
                System.out.println(manaCost);
                System.out.println(cmc);
            }*/

        }

        // Grab card description
        Elements current = cardData.select("p.gatherer-oracle");
        String description = current.text();

        //Grab card rarity
        current = cardData.select(".collapse .gatherer-rarity");
        String rarity = current.text();

        return new Card(name, manaCost, cmc, powerVal, type, rarity, description);
    }

    public int calculateCMC(String string) {
        int total = 0;
        String[] parts = string.split(" ");

        if (isNumeric(parts[0])) {
            // Adds the integer value of the first string
            total = Integer.parseInt(parts[0]);
            // Adds number of remaining strings
            total += parts.length - 1;
        }
        else {
            total = parts.length;
        }
        return total;
    }

    public boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
