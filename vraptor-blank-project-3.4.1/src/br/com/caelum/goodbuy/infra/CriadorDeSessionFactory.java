package br.com.caelum.goodbuy.infra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class CriadorDeSessionFactory implements
		ComponentFactory<SessionFactory> {

	private SessionFactory factory;

	@PostConstruct
	public void abre() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();
		this.factory = configuration.buildSessionFactory();
		System.out.println("CriadorDeSessionFactory Abre");

	}

	public SessionFactory getInstance() {
		System.out.println("CriadorDeSessionFactory getInstance");
		return this.factory;
		
	}

	@PreDestroy
	public void fecha() {
		this.factory.close();
		System.out.println("CriadorDeSessionFactory Fecha");
	}

}
