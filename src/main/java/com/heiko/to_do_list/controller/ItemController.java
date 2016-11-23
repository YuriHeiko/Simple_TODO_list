package com.heiko.to_do_list.controller;

import com.heiko.to_do_list.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private ItemRepository dao;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ListItems(Model model) {
        model.addAttribute("items", dao.findAll());
        return "items/list";
    }

/*    @RequestMapping(value = "/items/scheduled", method = RequestMethod.GET)
    public String ListScheduledItems(Model model) {
        model.addAttribute("items", dao.findByStatus(true));
        return "items/list";
    }*/

    @RequestMapping(value = "/items/done", method = RequestMethod.GET)
    public String ListDoneItems(Model model) {
        model.addAttribute("items", dao.findAll());
        return "items/list";
    }

    @RequestMapping(value = "items/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id) {
        dao.delete(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "items/new", method = RequestMethod.GET)
    public String newItem() {
        return "items/new";
    }

    @RequestMapping(value = "items/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("title") String title, @RequestParam("description") String description,
                               @RequestParam("deadline") String deadline, @RequestParam("alert") String alert) throws ParseException {
        dao.save(new Item(title, description, format.parse(deadline), "".equals(alert) ? null : format.parse(alert)));
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "items/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        Item item = dao.findOne(id);
        model.addAttribute("item", item);
        return "items/edit";
    }

    @RequestMapping(value = "items/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("item_id") int id, @RequestParam("title") String title, @RequestParam("description") String description,
                               @RequestParam("deadline") String deadline, @RequestParam("alert") String alert) throws ParseException {
        Item item = dao.findOne(id);
        item.setTitle(title);
        item.setDescription(description);
        item.setDeadline(format.parse(deadline));
        item.setAlert("".equals(alert) ? null : format.parse(alert));
        dao.save(item);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "items/{id}/status", method = RequestMethod.GET)
    public ModelAndView status(@PathVariable int id) {
        Item item = dao.findOne(id);
        item.setState(!item.isState());
        dao.save(item);
        return new ModelAndView("redirect:/");
    }

}
