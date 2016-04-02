package com.common.batch.mapper;

import java.util.List;


public interface UnifyingItemsMapper<T> {

	T mapItems(List<?> items) throws Exception;
}
