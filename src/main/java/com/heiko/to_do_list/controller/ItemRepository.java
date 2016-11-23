package com.heiko.to_do_list.controller;

import com.heiko.to_do_list.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
    List<Item> findByStatus(boolean stateVal);
}
