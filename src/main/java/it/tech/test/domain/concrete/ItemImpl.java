package it.tech.test.domain.concrete;

import org.springframework.core.style.ToStringCreator;

import it.tech.test.domain.contract.Item;
import it.tech.test.domain.contract.ItemType;

/**
 * An implementation for the Item interface 
 * 
 * @author Massimo Ugues
 *
 */
public class ItemImpl implements Item {
	private String description;
	private ItemType itemType;

	public ItemImpl() {
		super();
	}

	public ItemImpl(String description, ItemType itemType) {
		super();
		this.description = description;
		this.itemType = itemType;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public ItemType getItemType() {
		return itemType;
	}

	@Override
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this).append("description=" + description).append("itemType=" + itemType).toString();
	}
	
}
