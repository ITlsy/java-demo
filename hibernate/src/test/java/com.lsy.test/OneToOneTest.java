package com.lsy.test;


import com.lsy.pojo.Card;
import com.lsy.pojo.Person;
import com.lsy.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.junit.Test;

public class OneToOneTest {

    @Test
    public void save(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Person person=new Person();
        person.setName("jack");

        Card card=new Card();
        card.setCardName("001");
        card.setPerson(person);

        session.save(person);
        session.save(card);
        session.getTransaction().commit();
    }

    @Test
    public void find(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        /*Person person= (Person) session.get(Person.class,1);
        System.out.println(person.getName());*/

        Card card= (Card) session.get(Card.class,1);
        System.out.println(card.getCardName());

        session.getTransaction().commit();
    }

    @Test
    public void delete(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Person person= (Person) session.get(Person.class,2);
        session.delete(person);
        session.getTransaction().commit();
    }
}
