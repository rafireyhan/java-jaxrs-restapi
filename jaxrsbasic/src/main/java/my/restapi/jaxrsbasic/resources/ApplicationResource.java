package my.restapi.jaxrsbasic.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import my.restapi.jaxrsbasic.models.SensorsEntity;
import my.restapi.jaxrsbasic.models.Node;
import my.restapi.jaxrsbasic.models.Node.Sensor;
import my.restapi.jaxrsbasic.models.Node.Sensor.LastMeasurement;
import my.restapi.jaxrsbasic.models.MessageResponse;
import my.restapi.jaxrsbasic.util.DatabaseUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//import my.restapi.jaxrsbasic.models.Sensor;
//import my.restapi.jaxrsbasic.models.Sensor.LastMeasurement;
//import my.restapi.jaxrsbasic.models.SensorResponse;

@Path("/sensor")
public class ApplicationResource {
	
	@GET
	public Response sayhi() {
		return Response.status(Response.Status.OK)
				.entity("Started up at port 8080!")
				.build();		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createSensor(Node node) {
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		EntityManagerFactory emf = DatabaseUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    String id = "";
	    
	    try {
	        tx.begin();

	        for (Sensor sensor : node.getSensor()) {
	            SensorsEntity sensorEntity = new SensorsEntity();
	            id = UUID.randomUUID().toString().replace("-", "").substring(0, 24);
	            sensorEntity.setId(id);
	            sensorEntity.setNode(node.getNode());
	            sensorEntity.setTitle(sensor.getTitle());
	            sensorEntity.setUnit(sensor.getUnit());
	            sensorEntity.setSensorType(sensor.getSensorType());
	            sensorEntity.setIcon(sensor.getIcon());
	            sensorEntity.setCreatedAt(new Date());
	            sensorEntity.setValue(Float.parseFloat(sensor.getLastMeasurement().getValue()));

	            em.persist(sensorEntity);
	            
	            sensor.set_id(id);
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx.isActive()) {
	            tx.rollback();
	        }
	        System.out.println(e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	    } finally {
	        em.close();
	        emf.close();
	    }

	    String message = "Node berhasil dibuat!";
	    //MessageResponse responseMessage = new MessageResponse(message);
	    Map<String, String> responseMap = new HashMap<>();
	    responseMap.put("message", message);
	    responseMap.put("_id", id);
	    return Response.status(Response.Status.CREATED).entity(responseMap).build();
	}
	
	@GET
	@Path("/result")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNode(){
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		EntityManagerFactory emf = DatabaseUtil.getEntityManagerFactory();
	    EntityManager em = emf.createEntityManager();

	    TypedQuery<SensorsEntity> query = em.createQuery("SELECT s FROM SensorsEntity s WHERE s.node = :nodeId", SensorsEntity.class);
	    query.setParameter("nodeId", "5faea98f9b2df8001b92dfac");
	    
	    List<SensorsEntity> sensorEntities = query.getResultList();

	    List<Node> Nodes = new ArrayList<>();
	    Node node = new Node();
	    node.setNode("5faea98f9b2df8001b92dfac");
	    List<Sensor> sensors = new ArrayList<>();
	    for (SensorsEntity sensorEntity : sensorEntities) {
	        Sensor sensor = new Sensor();
	        sensor.set_id(sensorEntity.getId());
	        sensor.setTitle(sensorEntity.getTitle());
	        sensor.setUnit(sensorEntity.getUnit());
	        sensor.setSensorType(sensorEntity.getSensorType());
	        sensor.setIcon(sensorEntity.getIcon());
	        LastMeasurement lastMeasurement = new LastMeasurement();
	        //Timestamp format
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	        String createdAt = sdf.format(sensorEntity.getCreatedAt());
	        lastMeasurement.setCreatedAt(createdAt);
	        //lastMeasurement.setCreatedAt(sensorEntity.getCreatedAt().toString());
	        lastMeasurement.setValue(String.valueOf(sensorEntity.getValue()));
	        sensor.setLastMeasurement(lastMeasurement);
	        sensors.add(sensor);
	    }
	    node.setSensors(sensors);
	    Nodes.add(node);

	    em.close();
	    emf.close();

	    return Response.status(Response.Status.OK).entity(Nodes).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSensor(Sensor sensor) {
	    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	    EntityManagerFactory emf = DatabaseUtil.getEntityManagerFactory();
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

	    try {
	        tx.begin();

	        SensorsEntity sensorEntity = em.find(SensorsEntity.class, sensor.get_id());
	        if (sensorEntity != null) {
	            em.remove(sensorEntity);
	            tx.commit();

	            String message = "Data berhasil dihapus!";
	            MessageResponse responseMessage = new MessageResponse(message);
	            return Response.status(Response.Status.OK).entity(responseMessage).build();
	        }
	    } catch (Exception e) {
	        if (tx.isActive()) {
	            tx.rollback();
	        }
	        System.out.println(e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	    } finally {
	        em.close();
	        emf.close();
	    }

	    String message = "Data tidak ditemukan!";
	    MessageResponse responseMessage = new MessageResponse(message);
	    return Response.status(Response.Status.NOT_FOUND).entity(responseMessage).build();
	}
}
