package kz.kaznitu.lessons.reposotories;

import kz.kaznitu.lessons.models.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher getUserById(long id);
}
