package br.unitins.topicos1.repository;

import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import br.unitins.topicos1.model.Carro;

@ApplicationScoped
public class CarroRepository implements PanacheRepository<Carro>{
    public List<Carro> findByName(String nome){
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%").list();
    }
}
