package be.condorcet.demo11.repositories;
import be.condorcet.demo11.entities.Client;
import be.condorcet.demo11.entities.Comfact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface ComfactRepository   extends JpaRepository<Comfact,Integer> {
    public List<Comfact> findComfactByClient(Client cl);
    public List<Comfact> findComfactByDatecom(Date dc);
}
