package com.heiko.to_do_list.Repository;

import com.heiko.to_do_list.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
    Page<Item> findByState(boolean stateVal, Pageable pageable);

    Page<Item> findAll(Pageable pageable);
}
