package com.leroymerlin.breakfastbff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/theolavabo")
@RestController
public class ThelavaboController {
    @GetMapping()
    String theolavabo() {
        return "theolavabo";
    }
}
