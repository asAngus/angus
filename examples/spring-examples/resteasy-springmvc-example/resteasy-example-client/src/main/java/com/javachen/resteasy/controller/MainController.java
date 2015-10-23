package com.javachen.resteasy.controller;

import com.javachen.resteasy.rest.ClientProxy;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private ResteasyClient client = new ResteasyClientBuilder().build();
    private ClientProxy proxy = client.target(ClientProxy.URL).proxy(ClientProxy.class);

    @RequestMapping(value = "/index.html")
    public String getIndex(ModelMap model) {
        model.addAttribute("user", proxy.getUser());
        return "index";
    }
}
