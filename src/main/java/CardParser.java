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
        Elements cardContainer = cardDoc.select("div.gatherer-container");
        String name = cardContainer.select("h3.gatherer-name").text();
        Elements cardData = cardContainer.select("div.collapse");

        Elements cardType = cardDoc.select("p.gatherer-type");
        String type = cardType.text();
        String powerVal = " ";
        if (type.contains("Creature") || type.contains("Planeswalker")) {
            Elements cardPower = cardDoc.select("div.gatherer-power");
            powerVal = cardPower.text();
        }

        Elements current = cardData.select("p.gatherer-oracle");
        String description = current.text();
        current = cardData.select(".collapse .gatherer-rarity");
        String rarity = current.text();
        // TODO: parse colour, cost, and type parameters


        Card card = new Card();
        card.setCardName(name);
        card.setType(type);
        card.setDescription(description);
        card.setRarity(rarity);
        card.setPowerVal(powerVal);
        return card;
    }
}
