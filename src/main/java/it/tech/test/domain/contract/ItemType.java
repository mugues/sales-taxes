package it.tech.test.domain.contract;

/**
 * An enum for the item type; the saleTax is the sales tax percentage (e.g. 0%, 10%..)
 * 
 * @author Massimo Ugues
 *
 */
public enum ItemType {
	BOOK("0"), FOOD("0"), MEDICAL("0"), OTHER("10");
	
	private String saleTax;

	ItemType(String saleTax) {
		this.saleTax = saleTax;
	}
	
	public String getSaleTax() {
		return saleTax;
	}
}
