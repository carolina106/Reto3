package Service;

import Repository.CostumeRepository;
import model.Client;
import model.Costume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumeService {
    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll(){
        return costumeRepository.getAll();
    }

    public Optional<Costume> getCostume(int id){
        return costumeRepository.getCostume(id);
    }

    public Costume save(Costume costume){
        if(costume.getId()==null){
            return costumeRepository.save(costume);
        }else{
            Optional<Costume> costumeEncontrado = getCostume(costume.getId());
            if(costumeEncontrado.isPresent()){
                return costumeRepository.save(costume);
            }else{
                return costume;
            }
        }
    }

    public Costume update(Costume costume){
        if(costume.getId()!=null){
            Optional<Costume> costumeEncontrado = getCostume(costume.getId());
            if(costumeEncontrado.isPresent()){
                if(costume.getName()!=null){
                    costumeEncontrado.get().setName(costume.getName());
                }
                if(costume.getBrand()!=null){
                    costumeEncontrado.get().setBrand(costume.getBrand());
                }
                if(costume.getYear()!=null){
                    costumeEncontrado.get().setYear(costume.getYear());
                }
                if(costume.getDescription()!=null){
                    costumeEncontrado.get().setDescription(costume.getDescription());
                }
                if(costume.getCategory()!=null){
                    costumeEncontrado.get().setCategory(costume.getCategory());
                }
                return costumeRepository.save(costumeEncontrado.get());
            }
        }
        return costume;
    }

    public boolean delete(int id){
        Boolean respuesta = getCostume(id).map(elemento ->{
            costumeRepository.delete(elemento);
            return true;
        }).orElse(false);

        return respuesta;
    }
}
