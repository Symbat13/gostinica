package kz.kaznitu.lessons.reposotories;

import kz.kaznitu.lessons.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client,Long> {
    Client getGuestById(long id);
}
