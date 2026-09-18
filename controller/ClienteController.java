package com.tuempresa.empresa_backend.controller;

import com.tuempresa.empresa_backend.entity.Cliente;
import com.tuempresa.empresa_backend.service.ClienteService;
//Le indica a Spring que debe realizar las validaciones declaradas
import jakarta.validation.Valid;
//Permite personalizar la respuesta http al importar ResponseEntity<T>
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
//Importa configuraciones de spring web para mappear peticiones http y metodos de Java;
//RestController, RequestMapping, GetMapping, PostMapping, PutMapping, DeleteMapping
//RequestBody, PathVariable, RequestParam = Estos extraen datos del JSON recibido
import org.springframework.web.bind.annotation.*;

//Usada para construir la URL del nuevo recurso creado
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
//Direccion URL a la que se mandarán peticiones

public class ClienteController {
    //Necesita una instancia del servicio
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Se realiza una petición de tipo get
    @GetMapping
    public List<Cliente> listar(){
        return clienteService.listar();
    }

    //id sería una vaiable de plantilla URI, al recibir /api/clientes/42 por ejemplo, el id sería 42
    @GetMapping("/{id}")
    //Response entity es un objeto genérico que encapsula la respuesta http
    //Path variable significa que en la URL va a venir el identificador
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id){
        return clienteService.obtenerPorId(id)
                .map(ResponseEntity::ok) //Map se ejcuta si optional no está vacío, devuelve HTTP 200 OK + JSON. Es una referencia a metodo y hace lo mismo que cliente -> ResponseEntity.ok(cliente)
                .orElse(ResponseEntity.notFound().build()); //Regresa un 404 y con build finaliza la creación del objeto ResponseEntity
    }

    //Crear un registro (POST)
    //RequestBody hace que se tome el JSON que envía el cliente y lo deserialice en una instancia de cliente
    @PostMapping
    public ResponseEntity<Cliente> crear (@Valid @RequestBody Cliente cliente){
        Cliente creado = clienteService.crear(cliente);
        return ResponseEntity.created(URI.create("/api/clientes/"+creado.getId())).body(creado); //.body adjunta datos del cliente creado en el cuerpo de la respuesta en JSON
    }

    //Tipo put comunmente para updates
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar (@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        try{
            return ResponseEntity.ok(clienteService.actualizar(id, cliente));  //200 ok
        }catch(IllegalArgumentException ex){
            return ResponseEntity.notFound().build();  // 404
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try{
            clienteService.eliminar(id);
            return ResponseEntity.noContent().build();  //Se devuelve status 204
        }catch(IllegalArgumentException ex){
            return ResponseEntity.notFound().build();  // 404
        }
    }

}
