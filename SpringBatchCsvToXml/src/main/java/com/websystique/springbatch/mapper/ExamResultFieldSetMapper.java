package com.websystique.springbatch.mapper;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.websystique.springbatch.model.ExamResult;

public class ExamResultFieldSetMapper implements FieldSetMapper<ExamResult>{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExamResultFieldSetMapper.class);

	@Override
	public ExamResult mapFieldSet(FieldSet fieldSet) throws BindException {
		LOGGER.debug("~~~~ ExamResultFieldSetMapper : mapFieldSet ~~~~");
		ExamResult result = new ExamResult();
		result.setStudentName(fieldSet.readString(0));
		result.setDob(new LocalDate(fieldSet.readDate(1,"dd/MM/yyyy")));
		result.setPercentage(fieldSet.readDouble(2));
		return result;
	}
}
