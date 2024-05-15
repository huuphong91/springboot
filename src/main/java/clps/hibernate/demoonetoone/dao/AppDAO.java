package clps.hibernate.demoonetoone.dao;

import clps.hibernate.demoonetoone.entity.Course;
import clps.hibernate.demoonetoone.entity.Instructor;
import clps.hibernate.demoonetoone.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findById(int theId);

    void deleteById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void updateInstructor(Instructor theInstructor);

    void updateCourse(Course theCourse);

    Course findCourseById(int theId);

    void deleteInstructorById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);
}
