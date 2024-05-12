<!DOCTYPE html>
<html>
<head>
    <title>Editar Cita</title>
</head>
<body>
    <h1>Editar Cita</h1>
    <form id="editCitaForm" action="actualizarCita.jsp" method="POST">
        <input type="hidden" id="id_cita" name="id_cita">
        <label for="nombre_paciente">Nombre Paciente:</label>
        <input type="text" id="nombre_paciente" name="nombre_paciente">
        <br>
        <label for="nombre_personal">Nombre Personal:</label>
        <input type="text" id="nombre_personal" name="nombre_personal">
        <br>
        <label for="fecha">Fecha:</label>
        <input type="text" id="fecha" name="fecha">
        <br>
        <label for="hora">Hora:</label>
        <input type="text" id="hora" name="hora">
        <br>
        <label for="estado">Estado:</label>
        <input type="text" id="estado" name="estado">
        <br>
        <label for="tipo_atencion">Tipo Atención:</label>
        <input type="text" id="tipo_atencion" name="tipo_atencion">
        <br>
        <input type="submit" value="Guardar Cambios">
    </form>

    <script>
        function getParameterByName(name, url) {
            if (!url) url = window.location.href;
            name = name.replace(/[\[\]]/g, "\\$&");
            var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, " "));
        }

        document.getElementById("id_cita").value = getParameterByName("id");
        document.getElementById("nombre_paciente").value = getParameterByName("nombrePaciente");
        document.getElementById("nombre_personal").value = getParameterByName("nombrePersonal");
        document.getElementById("fecha").value = getParameterByName("fecha");
        document.getElementById("hora").value = getParameterByName("hora");
        document.getElementById("estado").value = getParameterByName("estado");
        document.getElementById("tipo_atencion").value = getParameterByName("tipoAtencion");
    </script>
</body>
</html>
