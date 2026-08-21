package com.tuempresa.empresa_backend.repository;

//importa la clase de Entidad de Cliente que se creó
import com.tuempresa.empresa_backend.entity.Cliente;
//Permite que se hereden metodos CRUD sin usar SQL
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Esta es una interfaz para repositorio de la entidad, sirve para conectar una entidad con las operaciones de base de datos en JPA
public interface ClienteRepository extends JpaRepository<Cliente, Long>{  //Es una clase genérica, por lo tanto se indica la clase de entidad y tipo de archivo del ID(PK)

    //Optinal se usa para manejar de forma segura los casos donde no existe, y evitar NullPointerException
    Optional<Cliente> findByEmail(String email);  //Se pueden crear nuestros propios métodos
}
