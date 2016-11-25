package com.heiko.to_do_list.controller;

import com.heiko.to_do_list.Repository.ItemRepository;
import com.heiko.to_do_list.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository repository;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private FilterState filterState = FilterState.ALL;
    private int currentPageNumber = 0;
    private int pageSize = 10;

    private enum FilterState {
        ALL("all"), SCHEDULED("scheduled"), DONE("done");

        private String state;

        public String getState() {return state;}
        FilterState(String state) {this.state = state;}
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/items/all?size=" + pageSize;
    }

    @RequestMapping(value = "/setPageSize={size}", method = RequestMethod.GET)
    public String setPageSize(@PathVariable int size) {
        if (size > 0 ) {
            pageSize = size;
        }

        return "redirect:/items/all?size=" + pageSize;
    }

    // TODO Change navigation system, I can create only one method and get a filter parameter from address string

    @RequestMapping(value = "/items/{filter}", method = RequestMethod.GET)
    public String ListItems(ModelMap model, Pageable pageable, @PathVariable String filter) {
        currentPageNumber = pageable.getPageNumber();
        filterState = FilterState.valueOf(filter.toUpperCase());
        if (filterState == FilterState.ALL) {
            model.addAttribute("items", repository.findAll(pageable));
        } else {
            model.addAttribute("items", repository.findByState((filterState == FilterState.DONE), pageable));
        }
        model.addAttribute("size", pageSize);
        model.addAttribute("filter", filterState.getState());
        return "items/list";
    }

    @RequestMapping(value = "items/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id) {
        repository.delete(id);
        return new ModelAndView("redirect:/items/" + filterState.getState() + "?size=" + pageSize + "&page=" + currentPageNumber);
    }

    @RequestMapping(value = "items/new", method = RequestMethod.GET)
    public String newItem() {
        return "items/new";
    }

    // TODO Try to make a modal forms for edit&add item

    @RequestMapping(value = "items/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("title") String title, @RequestParam("description") String description,
                               @RequestParam("deadline") String deadline, @RequestParam("alert") String alert) throws ParseException {
        repository.save(new Item(title, description, format.parse(deadline), "".equals(alert) ? null : format.parse(alert)));
        return new ModelAndView("redirect:/items/" + filterState.getState() + "?size=" + pageSize + "&page=" + currentPageNumber);
    }

    @RequestMapping(value = "items/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable int id, ModelMap model) {
        Item item = repository.findOne(id);
        model.addAttribute("item", item);
        return "items/edit";
    }

    @RequestMapping(value = "items/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("item_id") int id, @RequestParam("title") String title, @RequestParam("description") String description,
                               @RequestParam("deadline") String deadline, @RequestParam("alert") String alert) throws ParseException {
        Item item = repository.findOne(id);
        item.setTitle(title);
        item.setDescription(description);
        item.setDeadline(format.parse(deadline));
        item.setAlert("".equals(alert) ? null : format.parse(alert));
        repository.save(item);
        return new ModelAndView("redirect:/items/" + filterState.getState() + "?size=" + pageSize + "&page=" + currentPageNumber);
    }

    @RequestMapping(value = "items/{id}/status", method = RequestMethod.GET)
    public ModelAndView status(@PathVariable int id) {
        Item item = repository.findOne(id);
        item.setState(!item.isState());
        repository.save(item);
        return new ModelAndView("redirect:/items/" + filterState.getState() + "?size=" + pageSize + "&page=" + currentPageNumber);
    }
}
