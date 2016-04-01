package com.common.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.common.batch.model.Customer;

public class CustomerProcessor implements ItemProcessor<Customer, Customer>{

	@Override
	public Customer process(Customer result) throws Exception {
		System.out.println("Processing result :"+result);

		return result;
	}
}
