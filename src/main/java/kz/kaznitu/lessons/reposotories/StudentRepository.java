package kz.kaznitu.lessons.reposotories;

import kz.kaznitu.lessons.models.Student;
import kz.kaznitu.lessons.models.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
    Student getUserById(long id);
}
