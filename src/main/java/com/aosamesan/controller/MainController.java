package com.aosamesan.controller;

import com.aosamesan.model.Collection;
import com.aosamesan.model.RootImageDirectoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/")
public class MainController {
    private RootImageDirectoryProvider rootImageDirectoryProvider;

    @Autowired
    public MainController(RootImageDirectoryProvider rootImageDirectoryProvider) {
        this.rootImageDirectoryProvider = rootImageDirectoryProvider;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String index(Model model) {
        model.addAttribute("IMAGEPATH", rootImageDirectoryProvider.getRootDirectoryPath());
        model.addAttribute("COLLECTIONS", rootImageDirectoryProvider.getCollections());
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/view/{collectionNumber}")
    public String view(Model model, @PathVariable(name = "collectionNumber") int collectionNumber) {
        Collection collection = rootImageDirectoryProvider.getCollection(collectionNumber);
        model.addAttribute("COLLECTION", collection);
        return "view";
    }
}
