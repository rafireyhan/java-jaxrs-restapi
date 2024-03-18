package my.restapi.jaxrsbasic.models;

public class Sensor {	
    private String _id;
    private String title;
    private String unit;
    private String sensorType;
    private String icon;
    private LastMeasurement lastMeasurement;

    public Sensor() {
    }

    public Sensor(String _id, String title, String unit, String sensorType, String icon, LastMeasurement lastMeasurement) {
        this._id = _id;
        this.title = title;
        this.unit = unit;
        this.sensorType = sensorType;
        this.icon = icon;
        this.lastMeasurement = lastMeasurement;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LastMeasurement getLastMeasurement() {
        return lastMeasurement;
    }

    public void setLastMeasurement(LastMeasurement lastMeasurement) {
        this.lastMeasurement = lastMeasurement;
    }
    
    public static class LastMeasurement {
        private String createdAt;
        private String value;

        public LastMeasurement() {
        }

        public LastMeasurement(String createdAt, String value) {
            this.createdAt = createdAt;
            this.value = value;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}


