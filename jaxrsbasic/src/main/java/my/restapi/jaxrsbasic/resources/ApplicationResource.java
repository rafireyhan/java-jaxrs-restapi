package my.restapi.jaxrsbasic.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import my.restapi.jaxrsbasic.models.Node;
import my.restapi.jaxrsbasic.models.Node.Sensor;
import my.restapi.jaxrsbasic.models.Node.Sensor.LastMeasurement;
import my.restapi.jaxrsbasic.models.MessageResponse;

//import my.restapi.jaxrsbasic.models.Sensor;
//import my.restapi.jaxrsbasic.models.Sensor.LastMeasurement;
//import my.restapi.jaxrsbasic.models.SensorResponse;

@Path("/sensor")
public class ApplicationResource {
	
	private Connection connect() {
		//Konfig koneksi ke db mariadb
		String url = "jdbc:mariadb://localhost:3306/simpletaskdb";
		String user = "root";
		String password = "";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	@GET
	public Response sayhi() {
		return Response.status(Response.Status.OK)
				.entity("Started up at port 8080!")
				.build();		
	}
	
	@GET
	@Path("/result")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Node> getNode(){
		List<Node> Nodes = new ArrayList<>();
		String sql = "SELECT * FROM sensors WHERE node = \"5faea98f9b2df8001b92dfac\"";
		try(Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			while (rs.next()) {
				Node node = new Node();
				node.setNode(rs.getString("node"));
				Sensor sensor = new Sensor();
				sensor.set_id(rs.getString("id"));
				sensor.setTitle(rs.getString("title"));
				sensor.setUnit(rs.getString("unit"));
				sensor.setSensorType(rs.getString("sensorType"));
				sensor.setIcon(rs.getString("icon"));
				LastMeasurement lastMeasurement = new LastMeasurement();
				lastMeasurement.setCreatedAt(rs.getString("createdAt"));
				lastMeasurement.setValue(String.valueOf(rs.getFloat("value")));
				sensor.setLastMeasurement(lastMeasurement);
				node.setSensor(sensor);
				Nodes.add(node);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return Nodes;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createSensor(Node node) {
		String sql = "INSERT INTO sensors (id, title, unit, sensorType, icon, createdAt, value, node) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, node.getSensor().get_id());
			pstmt.setString(2, node.getSensor().getTitle());
			pstmt.setString(3, node.getSensor().getUnit());
			pstmt.setString(4, node.getSensor().getSensorType());
			pstmt.setString(5, node.getSensor().getIcon());
			pstmt.setString(6, node.getSensor().getLastMeasurement().getCreatedAt());
			pstmt.setString(7, node.getSensor().getLastMeasurement().getValue());
			pstmt.setString(8, node.getNode());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
		String message = "Node berhasil dibuat!";
		MessageResponse responseMessage = new MessageResponse(message);
		return Response.status(Response.Status.CREATED).entity(responseMessage).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSensor(Sensor sensor) {
		String sql = "DELETE FROM sensors where id = ?";
		try(Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, sensor.get_id());
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				String message = "Data berhasil dihapus!";
				MessageResponse responseMessage = new MessageResponse(message);
				return Response.status(Response.Status.OK).entity(responseMessage).build();
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		String message = "Data tidak ditemukan!";
		MessageResponse responseMessage = new MessageResponse(message);
		return Response.status(Response.Status.NOT_FOUND).entity(responseMessage).build();
	}
}
