<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
    <title>Clinica Odontologica</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico"/>
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
		<link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
		<link href="plugins/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" type="text/css" />
		<!-- END GLOBAL MANDATORY STYLES -->
	
		<!--  BEGIN CUSTOM STYLE FILE  -->
		<link href="assets/css/apps/invoice-add.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="plugins/dropify/dropify.min.css">
		<link rel="stylesheet" type="text/css" href="assets/css/forms/theme-checkbox-radio.css">
		<link href="plugins/flatpickr/flatpickr.css" rel="stylesheet" type="text/css">
		<link href="plugins/flatpickr/custom-flatpickr.css" rel="stylesheet" type="text/css">
		<!--  END CUSTOM STYLE FILE  -->
</head>
<body class="sidebar-noneoverflow">
    
    <!--  BEGIN NAVBAR  -->
    <div class="header-container fixed-top">
        <header class="header navbar navbar-expand-sm">
            <a href="javascript:void(0);" class="sidebarCollapse" data-placement="bottom"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-menu"><line x1="3" y1="12" x2="21" y2="12"></line><line x1="3" y1="6" x2="21" y2="6"></line><line x1="3" y1="18" x2="21" y2="18"></line></svg></a>
            
            <ul class="navbar-item flex-row">
                <li class="nav-item align-self-center page-heading">
                    <div class="page-header">
                        <div class="page-title">
                            <h3>REGISTRAR UN PACIENTE</h3>
                        </div>
                    </div>
                </li>
            </ul>
            
        </header>
    </div>
    <!--  END NAVBAR  -->

    <!--  BEGIN MAIN CONTAINER  -->
    <div class="main-container" id="container">

        <div class="overlay"></div>
        <div class="search-overlay"></div>
    <!--  END CONTAINER  -->

        <!--  BEGIN SIDEBAR  -->
        <div class="sidebar-wrapper sidebar-theme">
            
            <nav id="sidebar">

                <ul class="navbar-nav theme-brand flex-row  text-center">
                    <li class="nav-item theme-text">
                        <a href="home.jsp" class="nav-link">Clinica Odontologica</a>
                    </li>
                    <li class="nav-item toggle-sidebar">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather sidebarCollapse feather-chevrons-left"><polyline points="11 17 6 12 11 7"></polyline><polyline points="18 17 13 12 18 7"></polyline></svg>
                    </li>
                </ul>

                <div class="shadow-bottom"></div>
                <ul class="list-unstyled menu-categories" id="accordionExample">
                    

                    <li class="menu">
                        <a href="home.jsp" aria-expanded="false" class="dropdown-toggle">
                            <div class="">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>                                
                                <span>Dashboard</span>
                            </div>
                        </a>
                    </li>                                    

                    <li class="menu active">
                        <a href="regPacientes.jsp" aria-expanded="true" class="dropdown-toggle">
                            <div class="">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-book"><path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path><path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path></svg>
                                <span>Registro de Citas</span>
                            </div>
                        </a>
                    </li>

                    <li class="menu">
                        <a href="regPacientes.jsp" aria-expanded="false" class="dropdown-toggle">
                            <div class="">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user-plus"><path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="8.5" cy="7" r="4"></circle><line x1="20" y1="8" x2="20" y2="14"></line><line x1="23" y1="11" x2="17" y2="11"></line></svg>                                
                                <span>Registro de Pacientes</span>
                            </div>
                        </a>
                    </li>
                    
                    <li class="menu">
                        <a target="_blank" href="../../documentation/index.html" aria-expanded="false" class="dropdown-toggle">
                            <div class="">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-log-out"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>                                
                                <span>Cerrar  Sesion</span>
                            </div>
                        </a>
                    </li>

                </ul>
                
            </nav>

        </div>
        <!--  END SIDEBAR  -->

<!--  BEGIN CONTENT AREA  -->
<div id="content" class="main-content">
	<div class="layout-px-spacing">

		<div class="row invoice layout-top-spacing layout-spacing">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				
				<div class="doc-container">

					<div class="row">
						<div class="col-xl-12">

							<div class="invoice-content">

								<div class="invoice-detail-body">
									<div class="invoice-detail-header">
									<form action="PacienteServlet?tipo=regist" method="post">
										<div class="row justify-content-between">
											<div class="col-xl-5 invoice-address-company">

												<h4>Formulario:</h4>

												<div class="invoice-address-company-fields">

                                                        <div class="form-group row">
                                                            <label for="document-number"class="col-sm-3 col-form-label col-form-label-sm">Documento de identidad</label>
															<div class="col-md-9">
                                                            	<input type="number" class="form-control form-control-sm" id="document-number" placeholder="Ingrese Doc." name="txtDocIdentidad" required>
                                                        	</div>
                                                    	</div>
                                                    	
                                                    	<div class="form-group row">
                                                            <label for="full-names"class="col-sm-3 col-form-label col-form-label-sm">Nombres y apellidos</label>
															<div class="col-md-9">
                                                            	<input type="text" class="form-control form-control-sm" id="full-names" placeholder="Ingrese nombres" name="txtNombresCompletos" required>
                                                        	</div>
                                                    	</div>

													
                                                        <div class="form-group row">
                                                            <label for="birth-date"class="col-sm-3 col-form-label col-form-label-sm">Fecha de Nacimiento</label>
															<div class="col-md-9">
                                                            	<input type="date" class="form-control form-control-sm" id="birth-date" placeholder="Elija fecha" name="txtFechaNacimiento" required>
                                                        	</div>
                                                    	</div>
													
														<div class="form-group row">
															<label for="pacient-phone" class="col-sm-3 col-form-label col-form-label-sm">Teléfono</label>
															<div class="col-sm-9">
																<input type="text" class="form-control form-control-sm" id="pacient-phone" placeholder="Ingrese telefono" name="txtTelefono" required>
															</div>
														</div>     
														
														<div class="form-group row">
															<label for="pacient-mail" class="col-sm-3 col-form-label col-form-label-sm">Correo</label>
															<div class="col-sm-9">
																<input type="mail" class="form-control form-control-sm" id="pacient-mail" placeholder="Ingrese correo" name="txtCorreo" required>
															</div>
														</div>                                                               
													
													</div>
												
												</div>


											<div class="col-xl-5 invoice-address-client">
												
											</div>
											
											
										</div>
										<div class="button">
											<input type="submit" class="btn btn-primary" value="Registrar">
										</div>
										</form>
										
									</div>	
									
								</div>
								
							</div>
							
						</div>
					</div>
					
					
				</div>

			</div>
		</div>
	</div>
	<div class="footer-wrapper">
		<div class="footer-section f-section-1">
			<p class="">Copyright 2024 <a target="_blank" href="https://designreset.com">Clinica Odontologica</a>, All rights reserved.</p>
		</div>
		<div class="footer-section f-section-2">
			<p class="">Coded with <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg></p>
		</div>
	</div>
</div>
<!--  END CONTENT AREA  -->

</div>
<!-- END MAIN CONTAINER -->

<!-- BEGIN GLOBAL MANDATORY SCRIPTS -->
<script src="assets/js/libs/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script src="assets/js/app.js"></script>

<script>
	$(document).ready(function() {
		App.init();
	});
</script>
<script src="assets/js/custom.js"></script>
<!-- END GLOBAL MANDATORY SCRIPTS -->

<script src="plugins/dropify/dropify.min.js"></script>
<script src="plugins/flatpickr/flatpickr.js"></script>
<script src="assets/js/apps/invoice-add.js"></script>
</body>
</html>