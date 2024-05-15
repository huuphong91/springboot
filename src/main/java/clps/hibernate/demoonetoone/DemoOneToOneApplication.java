package clps.hibernate.demoonetoone;

import clps.hibernate.demoonetoone.dao.AppDAO;
import clps.hibernate.demoonetoone.entity.Course;
import clps.hibernate.demoonetoone.entity.Instructor;
import clps.hibernate.demoonetoone.entity.InstructorDetail;
import clps.hibernate.demoonetoone.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoOneToOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOneToOneApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            deleteCourseAndReviews(appDAO);
        };
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;

        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        appDAO.deleteCourseById(theId);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        int theId = 10;

        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        System.out.println("Course: " + tempCourse);
        System.out.println("Reviews: " + tempCourse.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
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

    private void deleteCourseById(AppDAO appDAO) {
        int theId = 10;

        appDAO.deleteCourseById(theId);
    }

    private void deleteInstructorById(AppDAO appDAO) {
        int theId = 1;

        appDAO.deleteInstructorById(theId);
    }


    private void updateCourse(AppDAO appDAO) {
        int theId = 10;

        Course tempCourse = appDAO.findCourseById(theId);

        tempCourse.setTitle("Java Masterclass");

        appDAO.updateCourse(tempCourse);
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findById(theId);

        tempInstructor.setFirstName("Madhu");

        appDAO.updateInstructor(tempInstructor);
    }

    private void findInstructorByIdJoinFetch(AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("Found instructor: " + tempInstructor);
        System.out.println("Found instructor detail: " + tempInstructor.getInstructorDetail());
        System.out.println("Found courses: " + tempInstructor.getCourses());
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findById(theId);

        System.out.println("Found instructor: " + tempInstructor);

        // find Courses for the instructor
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        tempInstructor.setCourses(courses);

        System.out.println("Found courses: " + tempInstructor.getCourses());
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findById(theId);

        System.out.println("Found instructor: " + tempInstructor);
        System.out.println("Found instructor detail: " + tempInstructor.getInstructorDetail());
        System.out.println("Found courses: " + tempInstructor.getCourses());
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
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

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 2;

        appDAO.deleteInstructorDetailById(theId);
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 2;

        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        System.out.println("Found instructor detail: " + tempInstructorDetail);
        System.out.println("Found instructor: " + tempInstructorDetail.getInstructor());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;

        appDAO.deleteById(theId);
    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findById(theId);

        System.out.println("Found instructor: " + tempInstructor);
        System.out.println("Found instructor detail: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

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
