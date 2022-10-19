package be.condorcet.demo11.services;

import be.condorcet.demo11.entities.Client;
import be.condorcet.demo11.entities.Comfact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ComfactServiceImplTest {
    private Client cl;
    private Comfact cf;
    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @Autowired
    private ComfactServiceImpl comfactServiceImpl;

     @BeforeEach
    void setUp() {
        try{
            cl = new Client(null,"NomTest","PrenomTest",1000,"LocTest","rue test","1","0001",new ArrayList<>());
            clientServiceImpl.create(cl);
            System.out.println("création du client : "+cl);
            cf = new Comfact( Date.valueOf(LocalDate.now()),"C",new BigDecimal(1000),cl);
            comfactServiceImpl.create(cf);
            System.out.println("création de la commande : "+cf);
        }
        catch (Exception e){
            System.out.println("erreur de création de commande "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try{
            comfactServiceImpl.delete(cf);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement de commande "+e);
        }
        try{
            clientServiceImpl.delete(cl);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement de client "+e);
        }
    }


    @Test
    void create() {
        assertNotEquals(0,cf.getNumcommande(),"numéro de commande non incrémenté");
    }



    @Test
    void read() {
         try{
            int numc=cf.getNumcommande();
            Comfact co2=comfactServiceImpl.read(numc);
            assertEquals(new BigDecimal(1000.00).setScale(2,RoundingMode.HALF_UP),co2.getMontant().setScale(2,RoundingMode.HALF_UP),"montants différents "+new BigDecimal(1000).setScale(2,RoundingMode.HALF_UP)+"-"+co2.getMontant().setScale(2,RoundingMode.HALF_UP));
            //etc
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        cf.setMontant(new BigDecimal(50.25).setScale(2, RoundingMode.HALF_UP));
        //etc
        try{
            cf= comfactServiceImpl.update(cf);
            assertEquals(cf.getMontant(),new BigDecimal(50.25).setScale(2, RoundingMode.HALF_UP),"montants différents "+cf.getMontant()+"-"+new BigDecimal(50.25));
            //etc

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            comfactServiceImpl.delete(cf);
            Assertions.assertThrows(Exception.class, () -> {
                comfactServiceImpl.read(cf.getNumcommande());
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void affCollection(){ //ajouté
        try {
            Collection<Comfact> lco = comfactServiceImpl.getComfacts(cl);
            boolean trouve = false;
            for(Comfact c:lco){
                if(c.getNumcommande().equals(cf.getNumcommande())){
                    trouve=true;
                    break;
                }
            }
            assertTrue(trouve,"commande absente de la liste du client");
        }
        catch(Exception e){
            fail("Erreur de recherche "+e);
        }
    }
}


