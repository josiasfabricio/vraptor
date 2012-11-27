package br.com.caelum.goodbuy.testes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.goodbuy.dao.ProdutoDao;
import br.com.caelum.goodbuy.infra.CriadorDeSession;
import br.com.caelum.goodbuy.infra.CriadorDeSessionFactory;
import br.com.caelum.goodbuy.modelo.Produto;

public class AdicaoDeProduto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SessionFactory factory = new CriadorDeSessionFactory().getInstance();
		Session session = new CriadorDeSession(factory).getInstance();
		ProdutoDao dao = new ProdutoDao(session);
				
		// Criação do produto
		Produto produto = criarProduto();
		
		// Adição do produto no banco de dados
		dao.salvar(produto);
	}

	private static Produto criarProduto() {
		Produto produto = new Produto();
		produto.setNome("Prateleira");
		produto.setDescricao("Uma prateleira para colocar livros");		
		produto.setPreco(35.90);
		return produto;
	}

}
