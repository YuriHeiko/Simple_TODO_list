package com.heiko.to_do_list.controller;

import com.heiko.to_do_list.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemDAO extends PagingAndSortingRepository<Item, Integer> {
    Iterable<Item> findByStatus(boolean stateVal);
}
