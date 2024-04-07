package mx.edu.utez.Integradora_Hotel.model.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombrecategoria(String nombrecategoria);

}
