package com.lsy.service;

import com.lsy.dao.MovieDao;
import com.lsy.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieDao movieDao;

    public void save(Movie movie){
        movieDao.save(movie);
    }

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
       return movieDao.findAll("id","desc");
    }

    public Movie findById(Integer id) {
        return movieDao.findById(id);
    }
}
