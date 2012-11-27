<form action="<c:url value="/produtos/${produto.id }"/>"
method="POST">
	<fieldset>
		<legend>Editar Produto</legend>
		<label for="nome">Nome:</label> <input id="nome" type="text"
			name="produto.nome" value="${produto.nome }" /> <label
			for="descricao">Descrição:</label>
		<textarea id="descricao" name="produto.descricao">
${produto.descricao }
</textarea>
		<label for="preco">Preço:</label> <input id="preco" type="text"
			name="produto.preco" value="${produto.preco }" />
			<img src="<c:url value="/produtos/${produto.id}/imagem"/>"
width="100" height="100"/>
		<!-- vvvvvvv vvv -->
		<button type="submit" name="_method" value="PUT">Enviar</button>
	</fieldset>
</form>
<form action="<c:url value="/produtos/${produto.id }/imagem"/>"
	method="POST" enctype="multipart/form-data">
	<fieldset>
		<legend>Upload de Imagem</legend>
		<input type="file" name="imagem" />
		<button type="submit">Enviar</button>
	</fieldset>
</form>
