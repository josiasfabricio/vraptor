package br.com.caelum.goodbuy.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.goodbuy.dao.ProdutoDao;
import br.com.caelum.goodbuy.modelo.Produto;
import br.com.caelum.goodbuy.security.AutorizacaoInterceptor.Restrito;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class ProdutosController {

	private ProdutoDao dao;
	private final Result result;
	private final Validator validator;

	public ProdutosController(ProdutoDao dao, Result result, Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	@Get("/produtos")
	public List<Produto> lista() {
		return dao.listaTudo();
	}

	public List<Produto> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	@Get("/produtos/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q))
				.exclude("id", "descricao").serialize();
	}
	
	@Restrito
	@Post("/produtos")
	public void adiciona(final Produto produto) {
		validator.validate(produto);
		validator.onErrorUsePageOf(ProdutosController.class).formulario();

		dao.salvar(produto);
		result.redirectTo(this).lista();
	}
	
	@Restrito
	@Get("/produtos/novo")
	public void formulario() {
	}

	@Restrito
	@Get("/produtos/{id}")
	public Produto edita(Long id) {
		return dao.carregar(id);
	}

	@Restrito
	@Put("/produtos/{produto.id}")
	public void altera(Produto produto) {
		validator.validate(produto);
		validator.onErrorUsePageOf(ProdutosController.class).formulario();

		dao.atualizar(produto);
		result.redirectTo(this).lista();
	}

	@Restrito
	@Delete("/produtos/{id}")
	public void remove(Long id) {
		Produto produto = dao.carregar(id);
		dao.remove(produto);
		result.redirectTo(this).lista();
	}

	
}
