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
			<h1>Cadastrar Veiculo</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="/home">Home</a></li>
					<li class="breadcrumb-item">Cadastro</li>
					<li class="breadcrumb-item active">Veículo</li>
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
		
		<section class="section">
      <div class="row">
        <div class="col-lg-6">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Cadastrar Veiculo</h5>

              <!-- General Form Elements -->
              <form>
                <div class="row mb-3">
                  <label for="inputText" class="col-sm-2 col-form-label">Nome</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputEmail" class="col-sm-2 col-form-label">Modelo</label>
                  <div class="col-sm-10">
                    <input type="email" class="form-control">
                  </div>
                </div>
                
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">Marca Ativa</label>
                  <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example">
                      <option selected>Selecione</option>
                      <option value="1">Ford</option>
                      <option value="2">Wolksvagem</option>
                      <option value="3">BMW</option>
                    </select>
                  </div>
                </div>
                
                <div class="row mb-3">
                  <legend class="col-form-label col-sm-2 pt-0">Items do Veículo</legend>
                  <div class="col-sm-10">

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck1">
                      <label class="form-check-label" for="gridCheck1">
                        Radio
                      </label>
                    </div>

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck2">
                      <label class="form-check-label" for="gridCheck2">
                        Painel digital
                      </label>
                    </div>

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck3">
                      <label class="form-check-label" for="gridCheck3">
                        Jogo de roda
                      </label>
                    </div>

                  </div>
                </div>
                

                <div class="text-center">
                  <button type="submit" class="btn btn-primary">Salvar</button>
                  <button type="reset" class="btn btn-secondary">Cancelar</button>
                </div>
              </form>

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
							<h5 class="card-title">Veículos Cadastradas</h5>
							
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
									<c:forEach items='${veiculo}' var="marca">
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