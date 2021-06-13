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

import br.org.generation.farmacia.model.Produto;
import br.org.generation.farmacia.repository.ProdutoRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	/*
	 * Buscar todos os produtos
	 */
	@GetMapping
	public ResponseEntity<List<Produto>> GetAll(){ 
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	/*
	 * Buscar produto por Id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable long id){
		return produtoRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	/*
	 * Buscar produto pelo nome
	 */
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	/*
	 * Buscar produto pela descrição
	 */
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> GetByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(produtoRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	/*
	 * Registrar um novo produto
	 */
	@PostMapping 
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){ 
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	/*
	 * Alterar dados de um produto
	 */
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){ 
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)); 
	}
	
	/*
	 * Deletar um produto por Id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		produtoRepository.deleteById(id);
	}

}
