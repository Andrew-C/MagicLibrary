import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardParserTest {
    // TODO: add more test cases
    @Test
    void returnsCardName() throws IOException {
        CardParser parser = new CardParser("https://www.mtggoldfish.com/price/The+List/Wrenn+and+Six#paper");
        Card card = parser.parse();
        assertEquals("Wrenn and Six", card.getCardName());
    }

}
