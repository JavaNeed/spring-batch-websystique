package com.websystique.springbatch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.websystique.springbatch.model.ExamResult;

public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult>{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExamResultItemProcessor.class);
	
	@Override
	public ExamResult process(ExamResult result) throws Exception {
		LOGGER.debug("Processing result :"+result);
		
		/*
		 * Only return results which are more than 60%
		 * 
		 */
		if(result.getPercentage() < 60){
			return null;
		}
		
		return result;
	}

}
