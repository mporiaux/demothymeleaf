package be.condorcet.demo11.repositories;

import be.condorcet.demo11.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
   List<Client> findClientsByNomLike(String nom);
   Client findClientsByNomAndPrenomAndTel(String nom,String prenom,String tel);
}
/*{
    @Query(value = "SELECT cl FROM Client cl WHERE  cl.cp = 1000")
    Collection<Client> findAllBXLClients();

    @Query(value =" SELECT cl FROM Client cl WHERE  cl.cp = :cp")
    Collection<Client> findAllCPClients(@Param("cp") Integer cp);
}*/

