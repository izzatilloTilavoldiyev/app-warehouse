package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.service.MeasurementServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/measurement")
public class MeasurementController {

    @Autowired
    MeasurementServiceImpl measurementServiceImpl;

    //CREATE
    @PostMapping
    public Response addMeasurement(@RequestBody Measurement measurement) {
        return measurementServiceImpl.addMeasurement(measurement);
    }

    //READ ALL
    @GetMapping
    public List<Measurement> getAllMeasurements() {
        return measurementServiceImpl.getAllMeasurements();
    }

    //READ ONE
    @GetMapping("/{id}")
    public Measurement getMeasurement(@PathVariable Integer id) {
        return measurementServiceImpl.getMeasurement(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Response editeMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement) {
        return measurementServiceImpl.editeMeasurement(id, measurement);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public Response deleteMeasurement(@PathVariable Integer id) {
        return measurementServiceImpl.deleteMeasurement(id);
    }
}
