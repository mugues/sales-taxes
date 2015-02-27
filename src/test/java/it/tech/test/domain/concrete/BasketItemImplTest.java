package it.tech.test.domain.concrete;

import it.tech.test.domain.contract.Item;
import it.tech.test.domain.contract.ItemType;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context/common-context.xml" })
public class BasketItemImplTest {
	Logger logger = LoggerFactory.getLogger(BasketItemImplTest.class);

	@Test
	public void testTaxRateBigDecimal() {
		BigDecimal price = new BigDecimal("47.50");
		
		BigDecimal taxes = (price.multiply(new BigDecimal("10"))).divide(new BigDecimal("100"));		
		logger.debug("taxes pre round {} ", taxes);
		
		BigDecimal taxesRounded = taxes.setScale(2, RoundingMode.CEILING);		
		logger.debug("taxes post round {} ", taxesRounded);
		
		BigDecimal duty = (price.multiply(new BigDecimal("5"))).divide(new BigDecimal("100"));
		logger.debug("duty pre round {} ", duty);
		
		BigDecimal dutyRounded = duty.setScale(1, RoundingMode.CEILING);		
		logger.debug("dutyRounded post round {} ", dutyRounded);
		
		BigDecimal total = price.add(taxesRounded).add(dutyRounded);
		logger.debug("totale pre round {} ", total);
		logger.debug("totale {} ", total.setScale(2, RoundingMode.FLOOR));
	}
	
	@Test
	public void testRoundingMode() {
		//
		BigDecimal price = new BigDecimal("11.25");
		
		BigDecimal duty = (price.multiply(new BigDecimal("5"))).divide(new BigDecimal("100"));
		logger.debug("duty pre round {} ", duty);
		
		BigDecimal dutyRounded = duty.setScale(1, RoundingMode.CEILING);		
		logger.debug("dutyRounded post round {} ", dutyRounded);
		
		BigDecimal add = price.add(dutyRounded);
		logger.debug("Price {}", add);
	}
	
	
	@Test
	public void testTaxRateDouble() {
		Double price = new Double(18.99);
		
		Double taxes = (price * 10) / 100;		
		logger.debug("taxes pre round {} ", taxes);
		
		Double taxesRounded = MathUtils.round(taxes, 2, BigDecimal.ROUND_CEILING);						
		logger.debug("taxes post round {} ", taxesRounded);
		
		Double total = price + taxesRounded;
		logger.debug("totale pre round {} ", total);
		logger.debug("totale {} ", MathUtils.round(total, 2, BigDecimal.ROUND_HALF_UP));
				
	}
	
	@Test
	public void testTaxRateImportedDouble() {
		Double price = new Double(47.50);
		
		Double taxes = (price * 10) / 100;		
		logger.debug("taxes pre round {} ", taxes);
		
		Double taxesRounded = MathUtils.round(taxes, 2, BigDecimal.ROUND_HALF_UP);						
		logger.debug("taxes post round {} ", taxesRounded);
				
		Double duty = (price * 5) / 100;		
		logger.debug("duty pre round {} ", duty);
		
		Double dutyRounded = MathUtils.round(duty, 2, BigDecimal.ROUND_HALF_UP);						
		logger.debug("duty post round {} ", dutyRounded);
		
		Double total = price + taxesRounded + dutyRounded;
		logger.debug("totale pre round {} ", total);
		logger.debug("totale {} ", MathUtils.round(total, 2, BigDecimal.ROUND_CEILING));
	}
	
	@Test
    public void testDoubleUnprecise() {

        final double d = 0.1;

        double s = 0;
        for (int i = 0; i < 100; i++) {
            s = s + d;
            System.out.println(s);
        }
        System.out.println(s);
    }
	
	@Test
    public void testBigDecimalPrecise() {

        final BigDecimal d = new BigDecimal("0.1");
        BigDecimal result  =new BigDecimal("0");
        for (int i = 0; i < 100; i++) {
        	result = result.add(d);
            System.out.println(result);
        }
        System.out.println(result);
    }
	
	@Test
	public void testGetTotalPriceWithoutTax() {
		logger.debug("executing testGetTotalPrice");		
		
		Item item = new ItemImpl("Il trono di spade", ItemType.BOOK);
		BasketItemImpl basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("12.49"), false);
		
		BigDecimal totalPrice = basketItemImpl.getTotalPrice();
		
		Assert.assertEquals(new BigDecimal("12.49"), totalPrice);
		
		logger.debug("total price {}", totalPrice);		
	}
	
	@Test
	public void testGetTotalPriceWithTax() {
		logger.debug("executing testGetTotalPrice");		
		
		Item item = new ItemImpl("Interpol", ItemType.OTHER);
		BasketItemImpl basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("14.99"), false);
		
		BigDecimal totalPrice = basketItemImpl.getTotalPrice();
		
		Assert.assertEquals(new BigDecimal("16.49"), totalPrice);
		
		logger.debug("total price {}", totalPrice);		
	}
	
	@Test
	public void testGetTotalPriceWithoutTaxImported() {
		logger.debug("executing testGetTotalPrice");		
		
		Item item = new ItemImpl("imported box of chocolates", ItemType.FOOD);
		BasketItemImpl basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("10.00"), true);
		
		BigDecimal totalPrice = basketItemImpl.getTotalPrice();
		
		Assert.assertEquals(new BigDecimal("10.50"), totalPrice);
		
		logger.debug("total price {}", totalPrice);		
	}
	
	@Test
	public void testGetTotalPriceWithTaxImported() {
		logger.debug("executing testGetTotalPrice");		
		
		Item item = new ItemImpl("imported bottle of perfume", ItemType.OTHER);
		BasketItemImpl basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("47.50"), true);
		
		BigDecimal totalPrice = basketItemImpl.getTotalPrice();
		
		Assert.assertEquals(new BigDecimal("54.65"), totalPrice);
		
		logger.debug("total price {}", totalPrice);		
	}
	
	@Test
	public void testTotalOutput() {
		// Input 1 
		
		// first item
		Item item = new ItemImpl("book", ItemType.BOOK);
		BasketItemImpl basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("12.49"), false );
		Assert.assertEquals(new BigDecimal("12.49"), basketItemImpl.getTotalPrice());
		
		// second item
		item = new ItemImpl("music CD", ItemType.OTHER);
		basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("14.99"), false );
		Assert.assertEquals(new BigDecimal("16.49"), basketItemImpl.getTotalPrice());
		
		// third item
		item = new ItemImpl("chocolate bar", ItemType.FOOD);
		basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("0.85"), false );
		Assert.assertEquals(new BigDecimal("0.85"), basketItemImpl.getTotalPrice());
		
		// Input 2
		
		// first item
		item = new ItemImpl("imported box of chocolates", ItemType.FOOD);
		basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("10.00"), true);
		Assert.assertEquals(new BigDecimal("10.50"), basketItemImpl.getTotalPrice());
		
		// second item
		item = new ItemImpl("imported bottle of perfume ", ItemType.OTHER);
		basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("47.50"), true);
		Assert.assertEquals(new BigDecimal("54.65"), basketItemImpl.getTotalPrice());
		
		
		// Input 3
		
		// first item
		item = new ItemImpl("imported bottle of perfume", ItemType.OTHER);
		basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("27.99"), true);
		Assert.assertEquals(new BigDecimal("32.19"), basketItemImpl.getTotalPrice());
		
		// second item
		item = new ItemImpl("bottle of perfume", ItemType.OTHER);
		basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("18.99"), false);
		Assert.assertEquals(new BigDecimal("20.89"), basketItemImpl.getTotalPrice());
		
		// third item
		item = new ItemImpl("packet of headache pills", ItemType.MEDICAL);
		basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("9.75"), false);
		Assert.assertEquals(new BigDecimal("9.75"), basketItemImpl.getTotalPrice());
		
		// fourth item
		item = new ItemImpl("box of imported chocolates ", ItemType.FOOD);
		basketItemImpl = new BasketItemImpl(item, 1, new BigDecimal("11.25"), true);
		Assert.assertEquals(new BigDecimal("11.85"), basketItemImpl.getTotalPrice());
		 
	}
	
}
