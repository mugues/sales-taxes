package it.tech.test.domain.contract;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * The public interface for an order
 * 
 * @author Massimo Ugues
 *
 */
public interface Order {
	public Collection<BasketItem> getBasketItems();
	public void addBasketItem(BasketItem basketItem);
	
	public BigDecimal getSalesTaxes();
	public BigDecimal getTotal();
	
	public BigDecimal getSalesTaxesLambda();
	public BigDecimal getTotalLambda();
	
	 

}
