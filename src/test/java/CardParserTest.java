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

    // TODO: handle hyphen
    @Test
    void returnsType() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Emrakul+the+Aeons+Torn#paper");
        Card card = parser.parse();
        assertEquals("\n" +
                "Legendary Creature — Eldrazi", card.getType());
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
