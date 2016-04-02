package com.common.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.AbstractCursorItemReader;

import com.common.batch.mapper.UnifyingItemsMapper;

public class CompositeCursorItemReader<T> implements ItemStreamReader<T> {

    private List<AbstractCursorItemReader<?>> cursorItemReaders;

    private UnifyingItemsMapper<T> unifyingMapper;

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // read from all registered readers
        List items = new ArrayList();
        for (AbstractCursorItemReader<?> cursorItemReader : cursorItemReaders) {
            items.add(cursorItemReader.read());
        }
        // delegate to mapper
        return unifyingMapper.mapItems(items);
    }

    @Override
    public void update(ExecutionContext executionContext) {
        for (ItemStream itemStream : cursorItemReaders) {
            itemStream.update(executionContext);
        }
    }

    @Override
    public void close() throws ItemStreamException {
        for (ItemStream itemStream : cursorItemReaders) {
            itemStream.close();
        }
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        for (ItemStream itemStream : cursorItemReaders) {
            itemStream.open(executionContext);
        }
    }

    public void setUnifyingMapper(UnifyingItemsMapper<T> mapper) {
        this.unifyingMapper = mapper;
    }

    public void setCursorItemReaders(List<AbstractCursorItemReader<?>> cursorItemReaders) {
        this.cursorItemReaders = cursorItemReaders;
    }
}