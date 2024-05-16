package clps.hibernate.demoonetoone;

import clps.hibernate.demoonetoone.dao.AppDAO;
import clps.hibernate.demoonetoone.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoOneToOneApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoOneToOneApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(final AppDAO appDAO) {
        return runner -> deleteStudent(appDAO);
    }

    private void deleteStudent(final AppDAO appDAO) {
        int theId = 1;

        appDAO.deleteStudentById(theId);
    }

    private void deleteCourse(final AppDAO appDAO) {
        int theId = 10;

        appDAO.deleteCourseById(theId);
    }

    private void addMoreCoursesForStuden(final AppDAO appDAO) {
        int theId = 1;

        Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

        System.out.println("Student: " + tempStudent);
        System.out.println("Courses: " + tempStudent.getCourses());

        // add more courses to student
        Course tempCourse1 = new Course("Rubik's Cube - How To Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        // add student to courses
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        appDAO.update(tempStudent);

        System.out.println("Done!");
    }

    private void findStudenAndCourses(final AppDAO appDAO) {
        int theId = 1;

        Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

        System.out.println("Student: " + tempStudent);
        System.out.println("Courses: " + tempStudent.getCourses());
    }

    private void findCourseAndStudents(final AppDAO appDAO) {
        int theId = 10;

        Course tempCourse = appDAO.findCourseAndStudentByCourseId(theId);

        System.out.println("Course: " + tempCourse);
        System.out.println("Students: " + tempCourse.getStudents());
    }

    private void createCourseAndStudents(final AppDAO appDAO) {
        // create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        // add some students
        tempCourse.addStudent(new Student("John", "Doe", "dfdf@gmail.com"));
        tempCourse.addStudent(new Student("MMMM", "MMMM", "dfdddff@gmail.com"));
        tempCourse.addStudent(new Student("BBBB", "BBBB", "dfddfdfdf@gmail.com"));

        // print course students
        System.out.println("Saving the course: " + tempCourse);
        System.out.println("Saving the students: " + tempCourse.getStudents());

        // save the course ... and leverage the cascade all :-)
        appDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(final AppDAO appDAO) {
        int theId = 10;

        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        appDAO.deleteCourseById(theId);
    }

    private void retrieveCourseAndReviews(final AppDAO appDAO) {
        int theId = 10;

        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        System.out.println("Course: " + tempCourse);
        System.out.println("Reviews: " + tempCourse.getReviews());
    }

    private void createCourseAndReviews(final AppDAO appDAO) {
        // create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        // add some reviews
        tempCourse.addReview(new Review("Great course ... loved it!"));
        tempCourse.addReview(new Review("Cool course, job well done"));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        // save the course ... and leverage the cascade all :-)
        appDAO.save(tempCourse);

        // print course reviews
        System.out.println("Done!");
    }

    private void deleteCourseById(final AppDAO appDAO) {
        int theId = 10;

        appDAO.deleteCourseById(theId);
    }

    private void deleteInstructorById(final AppDAO appDAO) {
        int theId = 1;

        appDAO.deleteInstructorById(theId);
    }


    private void updateCourse(final AppDAO appDAO) {
        int theId = 10;

        Course tempCourse = appDAO.findCourseById(theId);

        tempCourse.setTitle("Java Masterclass");

        appDAO.updateCourse(tempCourse);
    }

    private void updateInstructor(final AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findById(theId);

        tempInstructor.setFirstName("Madhu");

        appDAO.updateInstructor(tempInstructor);
    }

    private void findInstructorByIdJoinFetch(final AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("Found instructor: " + tempInstructor);
        System.out.println("Found instructor detail: " + tempInstructor.getInstructorDetail());
        System.out.println("Found courses: " + tempInstructor.getCourses());
    }

    private void findCoursesForInstructor(final AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findById(theId);

        System.out.println("Found instructor: " + tempInstructor);

        // find Courses for the instructor
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        tempInstructor.setCourses(courses);

        System.out.println("Found courses: " + tempInstructor.getCourses());
    }

    private void findInstructorWithCourses(final AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findById(theId);

        System.out.println("Found instructor: " + tempInstructor);
        System.out.println("Found instructor detail: " + tempInstructor.getInstructorDetail());
        System.out.println("Found courses: " + tempInstructor.getCourses());
    }

    private void createInstructorWithCourses(final AppDAO appDAO) {
        // create the instructor
        Instructor tempInstructor = new Instructor("Chad", "Darby", "huuphong91@gmail.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com", "Love to code");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course course1 = new Course("Air Guitar - The Ultimate Guide");
        Course course2 = new Course("The Pinball Masterclass");

        // add courses to instructor
        tempInstructor.add(course1);
        tempInstructor.add(course2);

        // save the instructor
        appDAO.save(tempInstructor);
    }

    private void deleteInstructorDetail(final AppDAO appDAO) {
        int theId = 2;

        appDAO.deleteInstructorDetailById(theId);
    }

    private void findInstructorDetail(final AppDAO appDAO) {
        int theId = 2;

        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        System.out.println("Found instructor detail: " + tempInstructorDetail);
        System.out.println("Found instructor: " + tempInstructorDetail.getInstructor());
    }

    private void deleteInstructor(final AppDAO appDAO) {
        int theId = 1;

        appDAO.deleteById(theId);
    }

    private void findInstructor(final AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findById(theId);

        System.out.println("Found instructor: " + tempInstructor);
        System.out.println("Found instructor detail: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(final AppDAO appDAO) {

        // create the instructor
        Instructor tempInstructor = new Instructor("Chad", "Darby", "huuphong91@gmail.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com", "Love to code");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        appDAO.save(tempInstructor);
    }
}
