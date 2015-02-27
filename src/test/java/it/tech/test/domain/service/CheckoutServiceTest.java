package it.tech.test.domain.service;

import it.tech.test.domain.concrete.BasketItemImpl;
import it.tech.test.domain.concrete.ItemImpl;
import it.tech.test.domain.concrete.OrderImpl;
import it.tech.test.domain.contract.BasketItem;
import it.tech.test.domain.contract.Item;
import it.tech.test.domain.contract.ItemType;
import it.tech.test.domain.contract.Order;
import it.tech.test.service.CheckoutService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context/common-context.xml" })
public class CheckoutServiceTest {
	Logger logger = LoggerFactory.getLogger(CheckoutServiceTest.class);

	@Autowired
	private CheckoutService service;

	@Test(expected=IllegalArgumentException.class)
	public void testProcessOrderWithNullOrder() {
		service.processOrder(null);		
	}
	
	@Test()
	@Ignore
	public void testProcessOrderWithNullBaskItems() {
		Order order = new OrderImpl();
		service.processOrder(order);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProcessOrderWithBasketItemWithNullName() {
		Order order = new OrderImpl();
		
		// first item
		BasketItemImpl basketItem = new BasketItemImpl(null, 1, new BigDecimal("12.49"), false);
		order.addBasketItem(basketItem);
		
		service.processOrder(order);
	}

	
	@Test
	public void testProcessOrder() {
		
		// Input 1 
		Order order = new OrderImpl();
		
		// first item
		Item item = new ItemImpl("book", ItemType.BOOK);
		BasketItemImpl basketItem = new BasketItemImpl(item, 1, new BigDecimal("12.49"), false);
		order.addBasketItem(basketItem);
		
		// second item
		item = new ItemImpl("music CD", ItemType.OTHER);
		basketItem = new BasketItemImpl(item, 1, new BigDecimal("14.99"), false );
		order.addBasketItem(basketItem);
		
		// third item
		item = new ItemImpl("chocolate bar", ItemType.FOOD);
		basketItem = new BasketItemImpl(item, 1, new BigDecimal("0.85"), false );
		order.addBasketItem(basketItem);
		
		logger.debug("");
		logger.debug("Output 1:");
		service.processOrder(order);
		
		// Input 2 
		order = new OrderImpl();

		// first item		
		item = new ItemImpl("imported box of chocolates", ItemType.FOOD);
		basketItem = new BasketItemImpl(item, 1, new BigDecimal("10.00"), true);
		order.addBasketItem(basketItem);
		
		// second item
		item = new ItemImpl("imported bottle of perfume ", ItemType.OTHER);
		basketItem = new BasketItemImpl(item, 1, new BigDecimal("47.50"), true);
		order.addBasketItem(basketItem);
	
		logger.debug("");
		logger.debug("Output 2:");
		service.processOrder(order);
		
		// Input 2 
		order = new OrderImpl();

		// first item
		item = new ItemImpl("imported bottle of perfume", ItemType.OTHER);
		basketItem = new BasketItemImpl(item, 1, new BigDecimal("27.99"), true);
		order.addBasketItem(basketItem);
		
		// second item
		item = new ItemImpl("bottle of perfume", ItemType.OTHER);
		basketItem = new BasketItemImpl(item, 1, new BigDecimal("18.99"), false);
		order.addBasketItem(basketItem);
		
		// third item
		item = new ItemImpl("packet of headache pills", ItemType.MEDICAL);
		basketItem = new BasketItemImpl(item, 1, new BigDecimal("9.75"), false);
		order.addBasketItem(basketItem);
		
		// fourth item
		item = new ItemImpl("box of imported chocolates ", ItemType.FOOD);
		basketItem = new BasketItemImpl(item, 1, new BigDecimal("11.25"), true);
		order.addBasketItem(basketItem);

		logger.debug("");
		logger.debug("Output 3:");
		service.processOrder(order);
	}
	
	@Test
	public void testLambda() {
		Order order = new OrderImpl();
		
		// first item
		Item item = new ItemImpl("book", ItemType.BOOK);
		BasketItemImpl basketItem = new BasketItemImpl(item, 1, new BigDecimal("12.49"), false);
		order.addBasketItem(basketItem);
		
		// second item
		item = new ItemImpl("music CD", ItemType.OTHER);
		basketItem = new BasketItemImpl(item, 1, new BigDecimal("14.99"), false );
		order.addBasketItem(basketItem);
		
		Collection<BasketItem> basketItems = order.getBasketItems();
		DoubleSummaryStatistics sum = basketItems.stream().mapToDouble(x -> x.getPrice().doubleValue()).summaryStatistics();
		
		logger.debug("Sum {}", sum.getSum());
		
		Double salesTaxes8 = getSalesTaxes8(order, x -> x.getPrice().doubleValue());
		logger.debug("Sum {}", salesTaxes8);
		
		Assert.assertEquals(sum.getSum(), salesTaxes8, 0.0);		
		
		BigDecimal salesTaxesLambda = getSalesTaxesLambda(order, x -> x.getPrice());
		System.out.println(salesTaxesLambda);
	}
		
	public Double getSalesTaxes8(Order order, final ToDoubleFunction<BasketItem> func) {
		Collection<BasketItem> basketItems = order.getBasketItems();
		
		DoubleSummaryStatistics sum = basketItems.stream().mapToDouble(func).summaryStatistics();
		return MathUtils.round(sum.getSum(), 2, BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal getSalesTaxesLambda(Order order, Function<BasketItem, BigDecimal> func) {
		Collection<BasketItem> basketItems = order.getBasketItems();
		
		Optional<BigDecimal> reduce = basketItems.stream().map(func).reduce((x, y) -> x.add(y));
		BigDecimal bigDecimal = reduce.get();
		
		return bigDecimal.setScale(2, RoundingMode.HALF_UP);
		
	}

}
