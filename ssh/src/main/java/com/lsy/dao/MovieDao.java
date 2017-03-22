package com.lsy.dao;

import com.lsy.pojo.Category;
import com.lsy.pojo.Movie;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*@Repository
public class MovieDao {
    @Autowired
    private SessionFactory sessionFactory;
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void save(Movie movie){
        getSession().saveOrUpdate(movie);
    }
    public Movie findById(Integer id){
       return (Movie)getSession().get(Movie.class,id);

    }
    public void delete(Integer id){
        getSession().delete(findById(id));
    }
    public List<Movie> findAll(){
        Criteria criteria=getSession().createCriteria(Movie.class);
        return criteria.list();

    }
    public List<Movie> findAll(String propertyName,String orderType){
        Criteria criteria=getSession().createCriteria(Movie.class);
        if("desc".equalsIgnoreCase(orderType)){
            criteria.addOrder(Order.desc(propertyName));
        }else {
            criteria.addOrder(Order.asc(propertyName));
        }
        return criteria.list();
    }
}*/
@Repository
public class MovieDao extends BaseDao<Movie,Integer>{

}
