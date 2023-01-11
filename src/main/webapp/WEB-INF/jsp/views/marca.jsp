<%@ page contentType="text/html ;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">

<%@ include file="head.jsp"%>

<body>

	<%@ include file="header.jsp"%>

	<%@ include file="sidebar.jsp"%>

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>Cadastrar Marca de Veiculo</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="/home">Home</a></li>
					<li class="breadcrumb-item">Cadastro</li>
					<li class="breadcrumb-item active">Marca</li>
				</ol>
			</nav>
		</div>

		<c:forEach items='${msg}' var="msg">
			<div style="width: 200px; padding: 5px;" class="alert alert-danger"
				role="alert">
				<span>${msg}</span>
			</div>
		</c:forEach>
		<c:forEach items='${sucess}' var="sucess">
			<div style="width: 200px; padding: 5px;" class="alert alert-success"
				role="alert">
				<span>${sucess}</span>
			</div>
		</c:forEach>

		<!-- End Page Title -->
		<section class="section">
			<div class="row">
				<div class="col-lg-6">

					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Cadastrar Marca de Veiculo</h5>

							<!-- Horizontal Form -->
							<form action="salvar-marca-veiculo" method="post">

								<input type="hidden" name="id" value="${marca.id}" />

								<div class="row mb-3">
									<label for="inputEmail3" class="col-sm-2 col-form-label">Nome</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="nome"
											value="${marca.nome}" id="nome">
									</div>
								</div>
								<fieldset class="row mb-3">
									<legend class="col-form-label col-sm-2 pt-0">Ativo</legend>
									<div class="col-sm-10">
									<c:if test="${marca.ativo == 'Sim'}">
										<div class="form-check">
										  
											<input class="form-check-input" type="radio" name="ativo"
												id="gridRadios1" value="Sim" checked>
												 <label
												class="form-check-label" for="gridRadios1"> Sim </label>
										</div>
										</c:if>
										<c:if test="${marca.ativo == null || marca.ativo == 'Nao'}">
										<div class="form-check">
										  
											<input class="form-check-input" type="radio" name="ativo"
												id="gridRadios1" value="Sim" checked>
												 <label
												class="form-check-label" for="gridRadios1"> Sim </label>
										</div>
										</c:if>
										<c:if test="${marca.ativo == 'Nao'}">
										<div class="form-check">
										
											<input class="form-check-input" type="radio" name="ativo"
												id="gridRadios2" value="Nao" checked>
												 <label
												class="form-check-label" for="gridRadios2"> Não </label>
										</div>
										</c:if>
										<c:if test="${marca.ativo == null || marca.ativo == 'Sim'}">
										<div class="form-check">
											<input class="form-check-input" type="radio" name="ativo"
												id="gridRadios2" value="Nao">
												 <label
												class="form-check-label" for="gridRadios2"> Não </label>
										</div>
										</c:if>

									</div>
								</fieldset>
								<div class="text-center">
									<button type="submit" class="btn btn-primary">Salvar</button>
									<button type="reset" class="btn btn-secondary">Cancelar</button>
								</div>
							</form>
							<!-- FIM Horizontal Form -->

						</div>
					</div>

				</div>
			</div>
		</section>

		<section class="section">
			<div class="row">
				<div class="col-lg-6">

					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Marcas de Veículos Cadastradas</h5>
							
							<h6 class="card-title">Filtrar Por</h6>
							<form action="filtro" method="post">

								<div class="input-group mb-3">
									<input type="text" class="form-control"
										placeholder="Nome da Marca"
										aria-describedby="basic-addon2" name="nome" value="${pesquisa.nome}">
									<div class="input-group-append">
										<button class="btn btn-secondary" style="background-color: #012970" type="submit"><i class="bi bi-search"></i></button>
									</div>
								</div>

								<select class="form-select" aria-label="Default select example"
									name="ativo">
									<option disabled="disabled" selected style="color: #70757D">Marca
										Ativa</option>
									<option value="Sim">Sim</option>
									<option value="Nao">Não</option>
								</select>
							</form>	
								
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Nome</th>
										<th scope="col">Ativo</th>
										<th scope="col">Ação</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items='${marcaList}' var="marca">
										<c:url var="updateLink" value="/editar-marca-veiculo">
											<c:param name="id" value="${marca.id}" />
										</c:url>

										<c:url var="deleteLink" value="/deletar-marca-veiculo">
											<c:param name="id" value="${marca.id}" />
										</c:url>

										<tr>
											<td><c:out value="${marca.nome}"></c:out></td>
											<td><c:out value="${marca.ativo}"></c:out></td>
											<td><a href="${updateLink}" style="font-size: 1.2rem"><i
													class="bi bi-pencil-square"></i></a> <a href="${deleteLink}"><i
													class="bi bi-trash" style="color: red; padding: 10px; font-size: 1.2rem"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
					</div>

				</div>
			</div>
		</section>



	</main>
	<!-- End #main -->

	<%@ include file="footer.jsp"%>

	<!--script src="/resources/assets/vendor/echarts/echarts.min.js"></script>
  <script src="/resources/assets/vendor/quill/quill.min.js"></script>
  <script src="/resources/assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="/resources/assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="/resources/assets/vendor/php-email-form/validate.js"></script>
  <script src="/resources/assets/vendor/chart.js/chart.umd.js"></script>-->

</body>

</html>