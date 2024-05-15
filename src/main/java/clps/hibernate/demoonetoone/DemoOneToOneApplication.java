package clps.hibernate.demoonetoone;

import clps.hibernate.demoonetoone.dao.AppDAO;
import clps.hibernate.demoonetoone.entity.Instructor;
import clps.hibernate.demoonetoone.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoOneToOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOneToOneApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            deleteInstructorDetail(appDAO);
        };
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
