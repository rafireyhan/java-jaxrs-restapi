package my.restapi.jaxrsbasic.models;

import java.util.ArrayList;

public class SensorResponse {
    private String node;
    private ArrayList<Sensor> sensors;

    public SensorResponse() {
    }

    public SensorResponse(String node, ArrayList<Sensor> sensors) {
        this.node = node;
        this.sensors = sensors;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(ArrayList<Sensor> sensors) {
        this.sensors = sensors;
    }
}