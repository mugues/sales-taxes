package it.tech.test.service;

import it.tech.test.domain.contract.Order;

/**
 * The public interface for the service method
 * 
 * @author mugues
 *
 */
public interface CheckoutService {

	public void processOrder(Order order); 
}
