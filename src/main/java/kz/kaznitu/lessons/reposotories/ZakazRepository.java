package kz.kaznitu.lessons.reposotories;

import kz.kaznitu.lessons.models.Zakaz;
import org.springframework.data.repository.CrudRepository;


public interface ZakazRepository extends CrudRepository<Zakaz,Long> {
    Zakaz getGuestById(long id);
}
