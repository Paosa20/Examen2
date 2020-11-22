package com.cenfotec.examen2.controller;

import com.cenfotec.examen2.domain.Workshop;
import com.cenfotec.examen2.service.workshop.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkshopRestController {


    @Autowired
    private WorkshopService workshopService;

    @RequestMapping(path="/dataTable_Workshop", method= RequestMethod.GET)
    public List<Workshop> getAllEmployees(){
        return workshopService.getAll();
    }
}
