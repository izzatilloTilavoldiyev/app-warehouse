package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.reqository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    //CREATE
    public Response addMeasurement(Measurement measurement) {
        if (measurementRepository.existsByName(measurement.getName()))
            return new Response("This measurement is exists", false);
        measurementRepository.save(measurement);
        return new Response("Successfully saved", true);
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurement(Integer id) {
        return measurementRepository.findById(id).orElse(null);
    }

    public Response editeMeasurement(Integer id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new Response("This measurement not found", false);
        Measurement editingMeasurement = optionalMeasurement.get();
        editingMeasurement.setName(measurement.getName());
        measurementRepository.save(editingMeasurement);
        return new Response("Successfully edited", true);
    }

    public Response deleteMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new Response("This measurement not found", false);
        measurementRepository.delete(optionalMeasurement.get());
        return new Response("Successfully deleted", true);
    }
}
