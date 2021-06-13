package br.org.generation.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.farmacia.model.Categoria;
import br.org.generation.farmacia.repository.CategoriaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/*
	 * Buscar todas as categorias
	 */
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}

	/*
	 * Buscar categoria por Id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable long id) {
		return categoriaRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	/*
	 * Buscar categoria pelo nome da categoria
	 */
	@GetMapping("/categoria{categoria}")
	public ResponseEntity<List<Categoria>> getByCategoria(@PathVariable String categoria) {
		return ResponseEntity.ok(categoriaRepository.findAllByCategoriaContainingIgnoreCase(categoria));
	}

	/*
	 * Buscar categoria pela descrição
	 */
	@GetMapping("/categoria{descricao}")
	public ResponseEntity<List<Categoria>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	/*
	 * Criar uma nova categoria
	 */
	@PostMapping
	public ResponseEntity<Categoria> postProduto(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}

	/*
	 * Alterar uma categoria existente
	 */
	@PutMapping
	public ResponseEntity<Categoria> putProduto(@RequestBody Categoria categoria) {
		return ResponseEntity.ok(categoriaRepository.save(categoria));
	}

	/*
	 * Deletar catrgoria por Id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		categoriaRepository.deleteById(id);
	}

}
