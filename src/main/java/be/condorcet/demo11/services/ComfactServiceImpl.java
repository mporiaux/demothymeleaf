package be.condorcet.demo11.services;

import be.condorcet.demo11.entities.Comfact;
import be.condorcet.demo11.entities.Client;

import java.util.List;
import be.condorcet.demo11.repositories.ClientRepository;
import be.condorcet.demo11.repositories.ComfactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ComfactServiceImpl  implements InterfComfactService{
    @Autowired
    private ComfactRepository comfactRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Comfact create(Comfact comfact) throws Exception {
        comfactRepository.save(comfact);
        return comfact;
    }

    @Override
    public Comfact read(Integer id) throws Exception {
        return comfactRepository.findById(id).get();
    }

    @Override
    public Comfact update(Comfact comfact) throws Exception {
        read(comfact.getIdcommande());
        comfactRepository.save(comfact);
        return comfact;
    }

    @Override
    public Comfact delete(Comfact comfact) throws Exception {
        comfactRepository.deleteById(comfact.getIdcommande());
        return comfact;
    }

    @Override
    public List<Comfact> all() throws Exception {
        return comfactRepository.findAll();
    }

    @Override
    public Page<Comfact> allp(Pageable pageable) throws Exception {
        return  comfactRepository.findAll(pageable);
    }

    @Override
    public List<Comfact> getComfacts(Client cl) {
        List<Comfact> lcf = comfactRepository.findComfactByClient(cl);
        return lcf;
    }
}
