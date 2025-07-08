package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.model.Course;
import gr.aueb.cf.schoolapp.model.Region;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.TeacherMoreInfo;
import gr.aueb.cf.schoolapp.model.enums.GenderType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school8PU");
    private static EntityManager em = emf.createEntityManager();


    public static void main(String[] args) {

        //select active teachers - JPQL
//        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t WHERE active = true", Teacher.class);
//        List<Teacher> teachers = query.getResultList();
//        teachers.forEach(System.out::println);

        //Criteria API - select active teachers
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> query1 = cb.createQuery(Teacher.class);
//        Root<Teacher> teacher = query1.from(Teacher.class);
//        query1.select(teacher)
//                .where(cb.isTrue(teacher.get("active")));
//        List<Teacher> teachers = em.createQuery(query1).getResultList();
//        teachers.forEach(System.out::println);



        //select active teachers and from Αθηνα
//        String sql = "SELECT t FROM Teacher t WHERE t.active = true AND t.region.title = :city";
//        TypedQuery<Teacher> query = em.createQuery(sql, Teacher.class);
//        List<Teacher> teachers = query.setParameter("city", "Αθήνα").getResultList();
//        teachers.forEach(System.out::println);


//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
//        Root<Teacher> teacher = query.from(Teacher.class);
//        Join<Teacher, Region> region = teacher.join("region");
//        ParameterExpression<String> regionTitle = cb.parameter(String.class);
//        query.select(teacher).where(cb.and(cb.isTrue(teacher.get("active")),
//                cb.equal(region.get("title"), regionTitle)));
//        List<Teacher> activeTeachersInRegion = em.createQuery(query).setParameter(regionTitle,"Αθήνα").getResultList();
//        activeTeachersInRegion.forEach(System.out::println);

        //Select courses where comment starting with "Coding"

//        String sql = "SELECT c FROM Course c WHERE c.comment LIKE :comment";
//        TypedQuery<Course> query = em.createQuery(sql,Course.class);
//        query.setParameter("comment","%Coding%");
//        List<Course> courses = query.getResultList();
//        courses.forEach(System.out::println);

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Course> query = cb.createQuery(Course.class);
//        Root<Course> course = query.from(Course.class);
//        ParameterExpression<String> comments = cb.parameter(String.class);
//        query.select(course).where(
//                cb.like(course.get("comments"),comments)
//        );
//        List<Course> courses = em.createQuery(query).setParameter(comments, "%Coding%").getResultList();
//        courses.forEach(System.out::println);


        //Select male teachers born after a specific date
//        String sql = "SELECT t FROM Teacher t JOIN t.teacherMoreInfo tmi WHERE tmi.gender = :gender AND tmi.dateOfBirth > :date";
//        TypedQuery<Teacher> query = em.createQuery(sql, Teacher.class);
//        query.setParameter("gender", GenderType.MALE);
//        query.setParameter("dateOfBirth", LocalDateTime.of(1970,1,1,0,0,0));
//        List<Teacher> teachers = query.getResultList();
//        teachers.forEach(System.out::println);

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
//        Root<Teacher> teacher = query.from(Teacher.class);
//        Join<Teacher, TeacherMoreInfo> teacherMoreInfo = teacher.join("teacherMoreInfo");
//        ParameterExpression<GenderType> gender = cb.parameter(GenderType.class);
//        ParameterExpression<LocalDateTime> date = cb.parameter(LocalDateTime.class);
//        query.select(teacher).where(cb.and(
//                cb.equal(teacherMoreInfo.get("gender"),gender),
//                cb.greaterThan(teacherMoreInfo.get("dateOfBirth"),LocalDateTime.of(1970,1,1,0,0,0))));
//        List<Teacher> maleTeachers = em.createQuery(query).setParameter(gender,GenderType.MALE)
//                        .setParameter(date, LocalDateTime.of(1970,1,1,0,0,0))
//                        .getResultList();
//        maleTeachers.forEach(System.out::println);

        //select for every region => region.title and count for inactive teachers per region
        String sql = "SELECT r.title, COUNT(t) FROM Region r LEFT JOIN r.teachers t WHERE t.active = false OR t.active IS NULL GROUP BY r.title ";
        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        List<Object[]> inactiveTeachersCountPerRegion = query.getResultList();
        for (Object[] row : inactiveTeachersCountPerRegion){
            for(Object item: row){
                System.out.print(item + " ");
            }
            System.out.println();
        }

    }
}
