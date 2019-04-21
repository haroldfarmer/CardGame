
public class Card {
	private int value;
	private String label;
	private String suit;
	private int labelVal;
	
	Card(int value, int lVal, String label, String suit) {
		this.value = value;
		this.label = label;
		this.suit = suit;
		this.labelVal = lVal;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getlabelValue() {
		return labelVal;
	}
	public String toString() {
		if(value == 0) {
			return label + " of " + suit;
		}
		else {
			return String.valueOf(value) + " of " + suit ;
		}
		
		
	}
}
