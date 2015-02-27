package it.tech.test.domain.concrete;

import it.tech.test.domain.contract.BasketItem;
import it.tech.test.domain.contract.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.core.style.ToStringCreator;

/**
 * An implementation for the BasketItem interface
 * 
 * @author Massimo Ugues
 *
 */
public class BasketItemImpl implements BasketItem{
	private Item item;
	private Integer quantity = 1;
	private BigDecimal price = new BigDecimal("0.0");
	private Boolean imported = false;

	public BasketItemImpl() {
		super();
	}
	
	public BasketItemImpl(Item item, Integer quantity, BigDecimal price, Boolean imported) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.price = price;
		this.imported = imported;
	}

	@Override
	public Item getItem() {
		return item;
	}

	@Override
	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public Boolean isImported() {
		return imported;
	}

	@Override
	public void setImported(Boolean imported) {
		this.imported = imported;		
	}
	
	@Override
	public BigDecimal getSalesTaxes() {
		BigDecimal totalTaxes = new BigDecimal("0.0");
		BigDecimal dutyTax = new BigDecimal("5");
		
		if (item != null && item.getItemType() !=null) {
			BigDecimal taxes = (price.multiply(new BigDecimal(item.getItemType().getSaleTax())).divide(new BigDecimal("100")));
			BigDecimal taxesRounded = taxes.setScale(2, RoundingMode.CEILING);
			
			BigDecimal dutyTaxesRounded = new BigDecimal("0.0");
			if (isImported()) {
				BigDecimal dutyTaxes = (price.multiply(dutyTax).divide(new BigDecimal("100")));
				dutyTaxesRounded = dutyTaxes.setScale(1, RoundingMode.CEILING);
			}
			
			totalTaxes = taxesRounded.add(dutyTaxesRounded);
			
			totalTaxes.setScale(2, RoundingMode.HALF_UP);
			
		}
		
		return totalTaxes;
	}
	
	@Override
	public BigDecimal getTotalPrice() {
		return price.add(getSalesTaxes());
	}
		
	
	@Override
	public String toString() {
		return new ToStringCreator(this).append("item=" + item).append("quantity=" + quantity).append("price=" + price).append("imported=" + imported).toString();
	}

	

	

}
