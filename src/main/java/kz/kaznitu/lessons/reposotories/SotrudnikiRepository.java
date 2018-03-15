package kz.kaznitu.lessons.reposotories;

import kz.kaznitu.lessons.models.Sotrudniki;
import org.springframework.data.repository.CrudRepository;

public interface SotrudnikiRepository extends CrudRepository<Sotrudniki,Long> {
    Sotrudniki getGuestById(long id);
}
