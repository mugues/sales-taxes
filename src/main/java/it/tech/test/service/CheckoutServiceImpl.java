package it.tech.test.service;

import it.tech.test.domain.contract.BasketItem;
import it.tech.test.domain.contract.Order;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * The public service to process an order
 * @author Massimo Ugues
 *
 */
@Service
public class CheckoutServiceImpl implements CheckoutService{
	Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);	
	
	public void processOrder(Order order) {		
		logger.debug("processOrder start");
		
		Assert.notNull(order, "Order not initialized...");
		
		Collection<BasketItem> basketItems = order.getBasketItems();		
		logger.debug("processing {} bask items", basketItems.size());
		
		
		for (BasketItem basketItem : basketItems) {
			Assert.notNull(basketItem, "basketItem not initialized...");
			Assert.notNull(basketItem.getItem(), "item not initialized...");
			
			String output = "%s %s : %s";
			String format = String.format(output, basketItem.getQuantity(), basketItem.getItem().getDescription(), basketItem.getTotalPrice());
			logger.debug(format);
		}
		
		logger.debug("Sales Taxes: {}", order.getSalesTaxes());
		logger.debug("Total: {}", order.getTotal());
		
		//jdk8
		logger.debug("Sales Taxes lambda: {}", order.getSalesTaxesLambda());
		logger.debug("Total lambda: {}", order.getTotalLambda());
		
		
		logger.debug("processOrder stop");
	}

}
