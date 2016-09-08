package com.doj.batch.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;


public class CustomItemReader implements ItemReader<String> {

	private List<String> bookNameList;
	private int bookCount = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException {
		if (bookCount < bookNameList.size()) {
			return bookNameList.get(bookCount++);
		} else {
			return null;
		}
	}

	public List<String> getUserNameList() {
		return bookNameList;
	}

	public void setBookNameList(List<String> bookNameList) {
		this.bookNameList = bookNameList;
	}

}
