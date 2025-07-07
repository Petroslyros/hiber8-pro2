package gr.aueb.cf.schoolapp.model;


import gr.aueb.cf.schoolapp.model.enums.LessonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(length = 2048)
    private String comments;

    @Enumerated(EnumType.ORDINAL)        //default
    @Column(name = "lesson_type")
    private LessonType lessonType;


    //    @JoinTable(name = "courses_teachers"
//    , joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id")
//    )
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "courses_teachers")           // courses_id, teachers_id
    private Set<Teacher> teachers = new HashSet<>();

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comments='" + comments + '\'' +
                ", lessonType=" + lessonType +
                '}';
    }
}
