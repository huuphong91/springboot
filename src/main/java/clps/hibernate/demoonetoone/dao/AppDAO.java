package clps.hibernate.demoonetoone.dao;

import clps.hibernate.demoonetoone.entity.Instructor;
import clps.hibernate.demoonetoone.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findById(int theId);

    void deleteById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
