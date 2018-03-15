package kz.kaznitu.lessons.reposotories;

import kz.kaznitu.lessons.models.Komnata;
import kz.kaznitu.lessons.models.Komnata;
import org.springframework.data.repository.CrudRepository;


public interface KomnataRepository extends CrudRepository<Komnata,Long> {
    Komnata getGuestById(long id);
}
