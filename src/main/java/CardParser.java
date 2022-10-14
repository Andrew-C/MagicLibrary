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
        Elements cardContainer = cardDoc.select("div.gatherer-container");
        String name = cardContainer.select("h3.gatherer-name").text();
        Elements cardData = cardContainer.select("div.collapse");

        // Grab card mana cost
        Elements manaCostContainer = cardContainer.select(".gatherer-container .manacost");
        String manaCostText = manaCostContainer.attr("aria-label");
        int splitter = manaCostText.indexOf(':');
        String manaCost = manaCostText.substring(splitter + 2, manaCostText.length());
        int cmc = calculateCMC(manaCost);

        // Grab card type
        Elements cardType = cardDoc.select("p.gatherer-type");
        String type = cardType.text();

        // Grab card power/toughness/loyalty
        String powerVal = " ";
        if (type.contains("Creature") || type.contains("Planeswalker")) {
            Elements cardPower = cardDoc.select("div.gatherer-power");
            powerVal = cardPower.text();
        }

        // Grab card description
        Elements current = cardData.select("p.gatherer-oracle");
        String description = current.text();

        //Grab card rarity
        current = cardData.select(".collapse .gatherer-rarity");
        String rarity = current.text();
        // TODO: parse colour, cost, and type parameters


        Card card = new Card();
        card.setCardName(name);
        card.setManaCost(manaCost);
        card.setConvertedCost(cmc);
        card.setType(type);
        card.setDescription(description);
        card.setRarity(rarity);
        card.setPowerVal(powerVal);
        return card;
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
