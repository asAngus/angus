package com.javachen.controller;

import com.javachen.aspects.NotifyClients;
import com.javachen.model.Idea;
import com.javachen.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class IdeaController {

    @Autowired
    private IdeaService service;

    @RequestMapping(method = RequestMethod.GET)
    public String viewIdeas() {
        return "ideas";
    }

    @RequestMapping(value = "/ideas", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Idea> getIdeas() {
        return service.getIdeas();
    }

    @NotifyClients
    @RequestMapping(value = "/ideas/{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    Idea update(@PathVariable int id, @RequestBody Idea idea) {
        idea.setId(id);
        Idea out = service.updateIdea(idea);
        return out;
    }

    @NotifyClients
    @RequestMapping(value = "/ideas", method = RequestMethod.POST)
    public
    @ResponseBody
    Idea add(@RequestBody Idea idea) {
        Idea out = service.addIdea(idea);
        return out;
    }

    @NotifyClients
    @RequestMapping(value = "/ideas/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        Idea task = new Idea();
        task.setId(id);
        service.deleteIdea(task);
    }
}
