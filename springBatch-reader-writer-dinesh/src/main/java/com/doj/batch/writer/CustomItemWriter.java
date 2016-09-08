package com.doj.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> bookNameWithAuthor) throws Exception {
		System.out.println(bookNameWithAuthor);
	}

}
