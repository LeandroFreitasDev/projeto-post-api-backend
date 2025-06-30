package br.com.sdvweb.backend.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.sdvweb.backend.DTO.PostagemDTO;
import br.com.sdvweb.backend.entity.Postagem;
import br.com.sdvweb.backend.entity.User;
import br.com.sdvweb.backend.repository.PostagemRepository;
import br.com.sdvweb.backend.repository.UserRepository;
import br.com.sdvweb.backend.service.PostagemService;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/postagem")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public List<PostagemDTO> listarTodos() {
        return postagemService.listarTodos();
    }

    @PostMapping("/form")
    public ResponseEntity<?> salvarComImagem(
        @RequestParam("evento") String evento,
        @RequestParam("descricao") String descricao,
        @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
        @RequestParam("hora") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime hora,
        @RequestParam("local") String local,
        @RequestParam("imagem") MultipartFile imagem,
        @RequestParam("usuarioId") Long usuarioId
    ) throws IOException {
        User usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Postagem postagem = new Postagem();
        postagem.setEvento(evento);
        postagem.setDescricao(descricao);
        postagem.setData(data);
        postagem.setHora(hora);
        postagem.setLocal(local);
        postagem.setImagem(imagem.getBytes());
        postagem.setUsuario(usuario);

        postagemRepository.save(postagem);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/form/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> editarComImagem(
        @PathVariable Long id,
        @RequestParam("evento") String evento,
        @RequestParam("descricao") String descricao,
        @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
        @RequestParam("hora") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime hora,
        @RequestParam("local") String local,
        @RequestParam("usuarioId") Long usuarioId,
        @RequestParam(value = "imagem", required = false) MultipartFile imagem
    ) throws IOException {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada"));

        postagem.setEvento(evento);
        postagem.setDescricao(descricao);
        postagem.setData(data);
        postagem.setHora(hora);
        postagem.setLocal(local);
        postagem.setUsuario(userRepository.findById(usuarioId).orElseThrow());

        if (imagem != null && !imagem.isEmpty()) {
            postagem.setImagem(imagem.getBytes());
        }

        postagemRepository.save(postagem);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/imagem")
    public ResponseEntity<byte[]> obterImagem(@PathVariable Long id) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada"));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(postagem.getImagem());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        postagemService.excluir(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/buscar-por-data")
    public ResponseEntity<List<PostagemDTO>> buscarPorData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<PostagemDTO> eventos = postagemService.buscarPorData(data);
        return ResponseEntity.ok(eventos);
    }
}
