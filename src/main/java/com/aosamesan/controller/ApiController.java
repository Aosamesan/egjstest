package com.aosamesan.controller;

import com.aosamesan.model.Collection;
import com.aosamesan.model.RootImageDirectoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/api")
public class ApiController {
    RootImageDirectoryProvider rootImageDirectoryProvider;

    @Autowired
    public ApiController(RootImageDirectoryProvider rootImageDirectoryProvider) {
        this.rootImageDirectoryProvider = rootImageDirectoryProvider;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/collection")
    @ResponseBody
    public Map<String, Object> getCollection() {
        Map<String, Object> result = new HashMap<>();
        List<Collection> origin = rootImageDirectoryProvider.getCollections();

        result.put("result", origin);

        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/collection/{collectionNumber}")
    @ResponseBody
    public Collection getCollection( @PathVariable(name = "collectionNumber") int collectionNumber) {
        return rootImageDirectoryProvider.getCollection(collectionNumber);
    }


}
