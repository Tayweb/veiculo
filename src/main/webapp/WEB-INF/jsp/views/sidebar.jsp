<%@ page contentType="text/html ;charset=UTF-8" language="java"%>

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link " href="/home">
          <i class="bi bi-grid"></i>
          <span>Dashboard</span>
        </a>
      </li><!-- End Dashboard Nav -->


      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-journal-text"></i><span>Cadastro</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="forms-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          
          <li>
            <a href="/marca-veiculo">
              <i class="bi bi-circle"></i><span>Cadastrar Marca de Veiculo</span>
            </a>
          </li>
         
          <li>
            <a href="/veiculo">
              <i class="bi bi-circle"></i><span>Cadastrar Veiculo</span>
            </a>
          </li>
        
        </ul>
      </li>
</ul>
  </aside><!-- End Sidebar-->