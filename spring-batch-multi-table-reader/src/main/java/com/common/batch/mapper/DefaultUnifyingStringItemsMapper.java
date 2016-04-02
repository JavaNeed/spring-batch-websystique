package com.common.batch.mapper;

import java.util.List;

public class DefaultUnifyingStringItemsMapper implements UnifyingItemsMapper<String>{

	@Override
	public String mapItems(List<?> items) throws Exception {
		if (items != null && items.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Object item : items) {
				if (item != null) {
					sb.append(item);
				}
			}
			if (sb.length() > 0) {
				return sb.toString();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}