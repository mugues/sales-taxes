package it.tech.test.domain.concrete;

import static ch.lambdaj.Lambda.on;
import it.tech.test.domain.contract.BasketItem;
import it.tech.test.domain.contract.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.core.style.ToStringCreator;

import ch.lambdaj.Lambda;

/**
 * A general implementation for the Order interface; it's been implemented as a composition. 
 * 
 * @author Massimo Ugues
 *
 */
public class OrderImpl implements Order {
	private Collection<BasketItem> basketItems = new ArrayList<BasketItem>();

	@Override
	public Collection<BasketItem> getBasketItems() {
		return basketItems;
	}

	@Override
	public void addBasketItem(BasketItem basketItem) {
		this.basketItems.add(basketItem);
	}
	
	@Override
	public BigDecimal getSalesTaxes() {
		BigDecimal sum = Lambda.sum(basketItems, on(BasketItem.class).getSalesTaxes());
		return sum.setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public BigDecimal getTotal() {
		BigDecimal sum = Lambda.sum(basketItems, on(BasketItem.class).getTotalPrice());
		return sum.setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public BigDecimal getSalesTaxesLambda() {
		return sumAndRound(x -> x.getSalesTaxes());
	}

	@Override
	public BigDecimal getTotalLambda() {		
		return sumAndRound(x -> x.getTotalPrice());
	}

	public BigDecimal sumAndRound(final Function<BasketItem, BigDecimal> func) {
		Optional<BigDecimal> reduce = basketItems.stream().map(func).reduce((x, y) -> x.add(y));
		return reduce.get().setScale(2, RoundingMode.HALF_UP);
	}
	
	
	@Override
	public String toString() {
		String basketItemsString = (basketItems != null) ? Arrays.toString(basketItems.toArray()) : "";
		return new ToStringCreator(this).append("basketItems=" + basketItemsString).toString();
	}

	

	
}
