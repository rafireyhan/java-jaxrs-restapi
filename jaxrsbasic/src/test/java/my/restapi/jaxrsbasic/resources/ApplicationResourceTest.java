package my.restapi.jaxrsbasic.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import my.restapi.jaxrsbasic.models.Node;
import my.restapi.jaxrsbasic.models.Node.Sensor;
import my.restapi.jaxrsbasic.models.Node.Sensor.LastMeasurement;

public class ApplicationResourceTest {
	private ApplicationResource applicationResource;

    @BeforeEach
    public void setUp() {
        applicationResource = new ApplicationResource();
    }

    @Test
    public void testCreateSensor() {
    	Node node = new Node();
        node.setNode("5faea98f9b2df8001b92dfac");

        List<Sensor> sensors = new ArrayList<>();
        Sensor sensor = new Sensor();
        sensor.setTitle("Newest 2 Pollutant Measurement");
        sensor.setUnit("µg/m³");
        sensor.setSensorType("SDS 022");
        sensor.setIcon("osem-cloud");

        LastMeasurement lastMeasurement = new LastMeasurement();
        lastMeasurement.setCreatedAt("2024-02-05T02:56:40.838Z");
        lastMeasurement.setValue("7.63");
        sensor.setLastMeasurement(lastMeasurement);

        sensors.add(sensor);
        node.setSensors(sensors);

        Response response = applicationResource.createSensor(node);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void testGetNode() {
        Response response = applicationResource.getNode();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteSensor() {
        Sensor sensor = new Sensor();
        sensor.set_id("sensorId");
        Response response = applicationResource.deleteSensor(sensor);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}
