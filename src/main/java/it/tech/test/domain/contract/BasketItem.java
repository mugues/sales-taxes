package it.tech.test.domain.contract;

import java.math.BigDecimal;

/**
 * The public interface for a basket item
 * @author Massimo Ugues
 *
 */
public interface BasketItem {	
	public Item getItem();
	public void setItem(Item item);
	
	public Integer getQuantity();
	public void setQuantity(Integer quantity);
	
	public BigDecimal getPrice();
	public void setPrice(BigDecimal price);
	
	public Boolean isImported();
	public void setImported(Boolean imported);
	
	public BigDecimal getSalesTaxes();
	public BigDecimal getTotalPrice();

}
