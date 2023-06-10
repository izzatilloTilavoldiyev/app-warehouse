package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.reqository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements MeasurementService{

    @Autowired
    MeasurementRepository measurementRepository;

    //CREATE
    @Override
    public Response addMeasurement(Measurement measurement) {
        if (measurementRepository.existsByName(measurement.getName()))
            return new Response("This measurement is exists", false);
        measurementRepository.save(measurement);
        return new Response("Successfully saved", true);
    }

    @Override
    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    @Override
    public Measurement getMeasurement(Integer id) {
        return measurementRepository.findById(id).orElse(null);
    }

    @Override
    public Response editeMeasurement(Integer id, Measurement measurement) {
        if (measurementRepository.existsByName(measurement.getName()))
            return new Response("This measurement is exists", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new Response("This measurement not found", false);
        Measurement editingMeasurement = optionalMeasurement.get();
        editingMeasurement.setName(measurement.getName());
        measurementRepository.save(editingMeasurement);
        return new Response("Successfully edited", true);
    }

    @Override
    public Response deleteMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new Response("This measurement not found", false);
        measurementRepository.delete(optionalMeasurement.get());
        return new Response("Successfully deleted", true);
    }
}
