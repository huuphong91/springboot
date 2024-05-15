package clps.hibernate.demoonetoone.dao;

import clps.hibernate.demoonetoone.entity.Instructor;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findById(int theId);

    void deleteById(int theId);
}
