/**
 * 
 */
$(document).ready(function() {
	$('#editCitaModal').on('show.bs.modal', function(event) {
    	var citaData = '${citaData}'; // Obtener los datos de la cita desde el atributo en la solicitud

        if (citaData !== '') {
        	var cita = JSON.parse(citaData.replace(/'/g, '"')); // Convertir la cadena JSON a un objeto JavaScript

            $('#editCitaId').val(cita.id_cita);
            $('#editNombrePaciente').val(cita.nombre_paciente);
            $('#editNombrePersonal').val(cita.nombre_personal);
            $('#editEstado').val(cita.estado);
            $('#editTipoAtencion').val(cita.tipo_atencion);
            $('#editFecha').val(cita.fecha);
            $('#editHora').val(cita.hora);
         }
    });
});