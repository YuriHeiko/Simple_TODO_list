package com.heiko.to_do_list.controller;


import com.heiko.to_do_list.model.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemRepositoryCustom {
    public List<Item> FindByState(Boolean state);
}
