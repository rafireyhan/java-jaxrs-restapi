import unittest
import requests

class TestSensorAPI(unittest.TestCase):
    def setUp(self):
        # Setup kondisi awal
        self.base_url = 'http://localhost:8080/api/sensor'

    # Test Create Sensor
    def test_create_sensor(self):
        url = self.base_url
        data = {
            "node": "5faea98f9b2df8001b92dfac",
            "sensors": [
                {
                    "title": "New Humidity",
                    "unit": "%",
                    "sensorType": "BME280",
                    "icon": "osem-humidity",
                    "lastMeasurement": {
                        "value": "73.48"
                    }
                }
            ]
        }
        response = requests.post(url, json=data)
        self.assertEqual(response.status_code, 201)

        #Simpan ID sensor yang baru dibuat
        self.sensor_id = response.json().get('_id')
        print(response.json())

    # Test Get Sensor Structure
    def test_get_sensor_data(self):
        url = self.base_url+'/result'
        response = requests.get(url)
        self.assertEqual(response.status_code, 200)
        sensor_data = response.json()

        # Memeriksa bahwa respons memiliki struktur yang diharapkan
        self.assertIsInstance(sensor_data, list)
        for sensor_item in sensor_data:
            self.assertIsInstance(sensor_item.get('node'), str)
            self.assertIsInstance(sensor_item.get('sensor'), list)
            for sensor in sensor_item['sensor']:
                self.assertIsInstance(sensor.get('_id'), str)
                self.assertIsInstance(sensor.get('title'), str)
                self.assertIsInstance(sensor.get('unit'), str)
                self.assertIsInstance(sensor.get('sensorType'), str)
                self.assertIsInstance(sensor.get('icon'), str)
                self.assertIsInstance(sensor.get('lastMeasurement'), dict)
                self.assertIsInstance(sensor['lastMeasurement'].get('createdAt'), str)
                self.assertIsInstance(sensor['lastMeasurement'].get('value'), str)
    
    # Test Delete Sensor
    def test_delete_sensor_data(self):
        self.test_create_sensor()

        #Memastikan Id Sensor sudah ada
        self.assertIsNotNone(self.sensor_id)

        url = self.base_url
        data = {"_id": self.sensor_id}
        response = requests.delete(url, json=data)

        self.assertEqual(response.status_code, 200)
        print(response.json())
    
    def tearDown(self):
        #Membersihkan setelah pengujian
        if hasattr(self, 'sensor_id'):
            url = self.base_url
            requests.delete(url)

if __name__ == '__main__':
    unittest.main()