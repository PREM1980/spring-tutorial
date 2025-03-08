package com.luv2code.cruddemo.dao;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;
    
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        // save the student
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        return entityManager.createQuery("from Student order by lastName", Student.class).getResultList();
    }

    @Override
    public List<Student> findByLastName(String thelastName) {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where lastName=:theData", Student.class);
        theQuery.setParameter("theData", thelastName);
        return theQuery.getResultList();
        
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }
}
