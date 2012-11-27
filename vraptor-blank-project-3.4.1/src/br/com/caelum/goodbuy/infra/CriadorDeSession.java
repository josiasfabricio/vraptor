package br.com.caelum.goodbuy.infra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class CriadorDeSession implements ComponentFactory<Session> {

	private final SessionFactory factory;
	private Session session;

	public CriadorDeSession(SessionFactory factory) {
		this.factory = factory;
		System.out.println("CriadorDeSession");
	}

	@PostConstruct
	public void abre() {
		this.session = factory.openSession();
		System.out.println("CriadorDeSession abre");
	}

	public Session getInstance() {
		System.out.println("CriadorDeSession getInstance");
		return factory.openSession();
		
	}

	@PreDestroy
	public void fecha() {
		this.session.close();
		System.out.println("CriadorDeSession fecha");
	}

}
