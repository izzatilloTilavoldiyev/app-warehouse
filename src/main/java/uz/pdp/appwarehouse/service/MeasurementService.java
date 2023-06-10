package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Measurement;

import java.util.List;

public interface MeasurementService {
    Response addMeasurement(Measurement measurement);
    List<Measurement> getAllMeasurements();
    Measurement getMeasurement(Integer id);
    Response editeMeasurement(Integer id, Measurement measurement);
    Response deleteMeasurement(Integer id);
}
