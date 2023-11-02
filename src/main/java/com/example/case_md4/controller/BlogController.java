package com.example.case_md4.controller;

import com.example.case_md4.model.Blog;
import com.example.case_md4.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    IBlogService blogService;
    @GetMapping
    public ModelAndView showUser(){
        ModelAndView modelAndView = new ModelAndView("blog/blogList");
        modelAndView.addObject("blog1", blogService.findAll());
        return modelAndView;
    }
    @GetMapping("/blogCreate")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/blog/blogCreate");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/blogCreate")
    public ModelAndView save(Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView= new ModelAndView("redirect:/blog");
        return modelAndView;
    }
    @GetMapping("/blogEdit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/blog/blogEdit");
        modelAndView.addObject("blog1", blogService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/blogEdit")
    public ModelAndView update(Blog blog){
        Date date = new Date();
        blog.setTime(date);
        blogService.save(blog);
        ModelAndView modelAndView= new ModelAndView("redirect:/blog");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView remove(@PathVariable Long id){
        ModelAndView modelAndView= new ModelAndView("redirect:/blog");
        blogService.remove(id);
        return modelAndView;
    }
}

