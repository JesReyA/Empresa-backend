package com.tuempresa.empresa_backend.service;
//Se importa tanto la entidad como el repositorio, además de las capacidades de Service
import com.tuempresa.empresa_backend.entity.Cliente;
import com.tuempresa.empresa_backend.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//se anota como servicio
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;  // Se trabaja con la instancia de la clase/interfaz repository

    //Constructor de cliente service
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    //Se definen los metodos del CRUD
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    //Estos métodos de heredan por la interfaz repository
    public Optional<Cliente> obtenerPorId(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente crear (Cliente cliente){
        clienteRepository.findByEmail(cliente.getEmail()).ifPresent(c ->{
            throw new IllegalArgumentException("Ya existe un cliente con ese email");
        });
    return clienteRepository.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente datos){
        Cliente actual = clienteRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado"));

        actual.setNombre(datos.getNombre());
        actual.setEmail(datos.getEmail());

        return clienteRepository.save(actual);
    }

    public void eliminar(Long id){
        if(!clienteRepository.existsById(id)){
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
    }
}
