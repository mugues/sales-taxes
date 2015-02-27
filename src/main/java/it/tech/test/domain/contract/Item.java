package it.tech.test.domain.contract;

/**
 * The public interface for an item to buy 
 *  
 * @author Massimo Ugues
 *
 */
public interface Item {
	public String getDescription();
	public void setDescription(String description);
	
	public ItemType getItemType();
	public void setItemType(ItemType itemType);

}
