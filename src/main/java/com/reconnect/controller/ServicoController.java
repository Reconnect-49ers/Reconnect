package com.reconnect.controller;

import java.util.List;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reconnect.model.Servico;
import com.reconnect.repository.ServicoRepository;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public List<Servico> getAllServicos() {
        return servicoRepository.findAll();
    }

    @PostMapping
    public Servico createServico(@RequestBody Servico servico) {
        return servicoRepository.save(servico);
    }

    @GetMapping("/{id}")
    public Servico getServicoById(@PathVariable int id) {
        return servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servico not found with id " + id));
    }

    @PutMapping("/{id}")
    public Servico updateServico(@PathVariable int id, @RequestBody Servico servicoDetails) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servico not found with id " + id));

        servico.setData(servicoDetails.getData());
        servico.setEndereco(servicoDetails.getEndereco());
        servico.setTipo(servicoDetails.getTipo());
        servico.setHorario(servicoDetails.getHorario());

        return servicoRepository.save(servico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServico(@PathVariable int id) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Servico not found with id " + id));

        servicoRepository.delete(servico);

        return ResponseEntity.ok().build();
    }
}
