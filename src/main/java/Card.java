public class Card {
    private String cardName;
    private String manaCost;
    private int convertedCost = 0;
    private String rarity;
    private String powerVal;
    private String type;
    private String description;

    private Colour colour;

    public Card() {

    }

    public Card(String cardName, String manaCost, int convertedCost, String powerVal, String type, String rarity, String description) {
        this.cardName = cardName;
        this.manaCost = manaCost;
        this.convertedCost = convertedCost;
        this.powerVal = powerVal;
        this.type = type;
        this.rarity = rarity;
        this.description = description;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public int getConvertedCost() {
        return convertedCost;
    }

    public void setConvertedCost(int convertedCost) {
        this.convertedCost = convertedCost;
    }

    public String getPowerVal() {
        return powerVal;
    }

    public void setPowerVal(String powerVal) {
        this.powerVal = powerVal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRarity() {return rarity; }

    public void setRarity(String rarity) {this.rarity = rarity; }
}
