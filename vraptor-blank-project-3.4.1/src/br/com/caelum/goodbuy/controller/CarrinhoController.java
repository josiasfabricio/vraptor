package br.com.caelum.goodbuy.controller;

import br.com.caelum.goodbuy.dao.ProdutoDao;
import br.com.caelum.goodbuy.modelo.Carrinho;
import br.com.caelum.goodbuy.modelo.Item;
import br.com.caelum.goodbuy.security.AutorizacaoInterceptor.Restrito;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class CarrinhoController {

	private final Carrinho carrinho;
	private final ProdutoDao dao;
	private final Result result;

	public CarrinhoController(Carrinho carrinho, ProdutoDao dao, Result result) {
		this.carrinho = carrinho;
		this.dao = dao;
		this.result = result;
	}

	@Restrito
	@Post
	@Path("/carrinho")
	public void adiciona(Item item) {
		dao.recarrega(item.getProduto());
		carrinho.adiciona(item);

		result.redirectTo(this).visualiza();
	}

	@Get
	@Path("/carrinho")
	public void visualiza() {
	}
	
	@Delete("/carrinho/{indiceItem}")
	public void remove(int indiceItem) {
		carrinho.remove(indiceItem);
		result.redirectTo(this).visualiza();
	}

}
