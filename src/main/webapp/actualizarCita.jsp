<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.*" %>

<%
    // Llamar al procedimiento almacenado para obtener los datos de las citas
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_clinicadental", "root", "");
        
        // Llamar al procedimiento almacenado
        CallableStatement cs = con.prepareCall("{call ObtenerDatosCitas()}");
        ResultSet rs = cs.executeQuery();
        
        // Realizar la actualización en la tabla de citas
        while (rs.next()) {
	    String idCita = rs.getString("id_cita");
	    int idPaciente = rs.getInt("id_paciente");
	    int idPersonal = rs.getInt("id_personal"); // Obtener el valor numérico del id_personal
	    String fecha = rs.getString("fecha");
	    String hora = rs.getString("hora");
	    String estado = rs.getString("estado");
	    String tipoAtencion = rs.getString("tipo_atencion");
	
	    // Realizar la actualización en la base de datos
	    PreparedStatement ps = con.prepareCall("UPDATE Citas SET id_paciente=?, id_personal=?, fecha=?, hora=?, estado=?, tipo_atencion=? WHERE id_cita=?");
	    ps.setInt(1, idPaciente);
	    ps.setInt(2, idPersonal); // Asignar el valor numérico correcto para id_personal
	    ps.setString(3, fecha);
	    ps.setString(4, hora);
	    ps.setString(5, estado);
	    ps.setString(6, tipoAtencion);
	    ps.setString(7, idCita);
	    ps.executeUpdate();
	}


        con.close();
        response.sendRedirect("home.jsp"); // Redirigir de vuelta a la página de inicio
    } catch(Exception e) {
        out.println(e);
    }
%>
