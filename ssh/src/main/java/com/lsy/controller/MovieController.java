package com.lsy.controller;

import com.lsy.pojo.Movie;
import com.lsy.service.MovieService;
import com.lsy.util.Page;
import com.lsy.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;


    @GetMapping
    public String list(Movie movie, Model model, @RequestParam(required = false,defaultValue = "1",name = "p") Integer pageNo, HttpServletRequest request){
        //model.addAttribute("movieList",movieService.findAll());
        //model.addAttribute("page",movieService.findByPage(pageNo));
        List<QueryParam> queryParamList=QueryParam.builderQueryParamRequest(request);
     //   Page<Movie> page=movieService.findByQueryParam(pageNo,queryParamList);
        List<Movie> movieList=movieService.findByQueryParam(queryParamList);
        model.addAttribute("items",movieList);
        return "movie/list";

    }

    @GetMapping("/{id:\\d+}")
    public String show(@PathVariable Integer id,Model model){
        Movie movie=movieService.findById(id);
        model.addAttribute("movie",movie);
        return "movie/movie";

    }

}
