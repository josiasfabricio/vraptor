package br.com.caelum.goodbuy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.goodbuy.modelo.Produto;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ProdutoDao {

	private final Session session;

	public ProdutoDao(Session session) {
		// Aquisição da sessão
		// this.session = CriadorDeSession.getSession();
		this.session = session;
	}

	public void salvar(Produto produto) {

		Transaction tx = session.beginTransaction();
		session.save(produto);
		tx.commit();
	}

	public void alterar() {
		// Carrega o produto do banco de dados
		Produto produto = (Produto) session.load(Produto.class, 2L);

		Transaction tx = session.beginTransaction();
		produto.setPreco(42.50);
		session.update(produto);
		tx.commit();
	}

	public Produto carregar(Long id) {
		return (Produto) this.session.load(Produto.class, id);
	}

	public void atualizar(Produto produto) {
		Transaction tx = session.beginTransaction();
		this.session.update(produto);
		tx.commit();
	}

	public void remove(Produto produto) {
		Transaction tx = session.beginTransaction();
		this.session.delete(produto);
		tx.commit();
	}

	public void excluir() {
		// carrega o produto do banco de dados
		Produto produto = (Produto) session.load(Produto.class, 2L);

		Transaction tx = session.beginTransaction();
		session.delete(produto);
		tx.commit();
	}

	public List<Produto> listaTudo() {
		return this.session.createCriteria(Produto.class).list();
	}

	public List<Produto> busca(String nome) {
		return session.createCriteria(Produto.class)
				.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE))
				.list();
	}

	public void recarrega(Produto produto) {
		session.refresh(produto);

	}

}
