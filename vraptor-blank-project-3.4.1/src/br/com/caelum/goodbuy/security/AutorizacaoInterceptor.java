package br.com.caelum.goodbuy.security;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.caelum.goodbuy.controller.UsuariosController;
import br.com.caelum.goodbuy.modelo.UsuarioWeb;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class AutorizacaoInterceptor implements Interceptor {

	private final UsuarioWeb usuario;
	private final Result result;

	public AutorizacaoInterceptor(UsuarioWeb usuario, Result result) {
		this.usuario = usuario;
		this.result = result;
	}

	public boolean accepts(ResourceMethod method) {
		return !usuario.isLogado() && method.containsAnnotation(Restrito.class);
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		result.redirectTo(UsuariosController.class).login();
	}

	// a anotação vai ficar disponível em tempo de execucao
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	// anotação para métodos
	public @interface Restrito {
	}

}
