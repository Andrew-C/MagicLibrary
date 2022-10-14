import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardParserTest {
    // TODO: add more test cases
    @Test
    void returnsCardName() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Emrakul+the+Aeons+Torn#paper");
        Card card = parser.parse();
        assertEquals("Emrakul, the Aeons Torn", card.getCardName());
    }

    @Test
    void returnsManaCostGen() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Emrakul+the+Aeons+Torn#paper");
        Card card = parser.parse();
        assertEquals("15", card.getManaCost());
    }

    @Test
    void returnsManaCostColour() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Progenitus#paper");
        Card card = parser.parse();
        assertEquals("white white blue blue black black red red green green", card.getManaCost());
    }

    @Test
    void returnsManaCostGenAndColour() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Temporal+Manipulation#paper");
        Card card = parser.parse();
        assertEquals("3 blue blue", card.getManaCost());
    }

    @Test
    void returnsConvertedCostGen() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Emrakul+the+Aeons+Torn#paper");
        Card card = parser.parse();
        assertEquals(15, card.getConvertedCost());
    }

    @Test
    void returnsConvertedCostColour() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Progenitus#paper");
        Card card = parser.parse();
        assertEquals(10, card.getConvertedCost());
    }

    @Test
    void returnsConvertedCostGenAndColour() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Temporal+Manipulation#paper");
        Card card = parser.parse();
        assertEquals(5, card.getConvertedCost());
    }

    // TODO: handle hyphen
    @Test
    void returnsType() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Emrakul+the+Aeons+Torn#paper");
        Card card = parser.parse();
        assertEquals("Legendary Creature \u2014 Eldrazi", card.getType());
    }

    @Test
    void returnsDescription() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Emrakul+the+Aeons+Torn#paper");
        Card card = parser.parse();
        assertEquals("This spell can't be countered. " +
                "When you cast this spell, take an extra turn after this one. " +
                "Flying, protection from spells that are one or more colors, annihilator 6 " +
                "When Emrakul, the Aeons Torn is put into a graveyard from anywhere, its owner shuffles their graveyard into their library.",
                card.getDescription());
    }

    @Test
    void returnsRarity() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Emrakul+the+Aeons+Torn#paper");
        Card card = parser.parse();
        assertEquals("Mythic", card.getRarity());
    }

    @Test
    void returnsPowerVal() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Emrakul+the+Aeons+Torn#paper");
        Card card = parser.parse();
        assertEquals("15 / 15", card.getPowerVal());
    }

    @Test
    void returnsEnchantmentPowerVal() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Enlightened+Tutor#paper");
        Card card = parser.parse();
        assertEquals(" ", card.getPowerVal());
    }
}
