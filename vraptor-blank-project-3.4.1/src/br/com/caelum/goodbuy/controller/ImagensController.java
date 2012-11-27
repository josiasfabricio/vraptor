package br.com.caelum.goodbuy.controller;

import java.io.File;

import br.com.caelum.goodbuy.imagens.Imagens;
import br.com.caelum.goodbuy.modelo.Produto;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.Validations;
import static org.hamcrest.Matchers.*;   

@Resource
public class ImagensController {

	
	private final Validator validator;
	private final Imagens imagens;
	private final Result result;
	

	public ImagensController(Validator validator, Imagens imagens, Result result) {
		this.validator = validator;
		this.imagens = imagens;
		this.result = result;
	}

	@Post("/produtos/{produto.id}/imagem")
	public void upload(Produto produto, final UploadedFile imagem) {
		validator.checking(new Validations() {
			{
				if (that(imagem, is(notNullValue()), "imagem", "imagem.nula")) {
					that(imagem.getContentType(), startsWith("image"),
							"imagem", "nao.eh.imagem");
				}
			}
		});
		validator.onErrorRedirectTo(ProdutosController.class).edita(
				produto.getId());
		imagens.salva(imagem, produto);
		result.redirectTo(ProdutosController.class).edita(produto.getId());
	}

	@Get("/produtos/{produto.id}/imagem")
	public File download(Produto produto) {
		return imagens.mostra(produto);
	}	

}
