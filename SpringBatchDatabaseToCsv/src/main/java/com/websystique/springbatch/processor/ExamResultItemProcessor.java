package com.websystique.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.websystique.springbatch.model.ExamResult;

public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult>{

	@Override
	public ExamResult process(ExamResult result) throws Exception {
		System.out.println("Processing result :"+result);

		// Only return results which are more than 35%
		if(result.getPercentage() < 35){
			return null;
		}
		return result;
	}
}
