package com.lsy.controller;

import com.lsy.pojo.Movie;
import com.lsy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;


    @GetMapping
    public String list(Movie movie,Model model){
        model.addAttribute("movieList",movieService.findAll());
        return "movie/list";

    }

    @GetMapping("/movie/{id:\\d+}")
    public String show(@PathVariable Integer id,Model model){
        Movie movie=movieService.findById(id);
        model.addAttribute("movie",movie);
        return "movie/movie";

    }

}
