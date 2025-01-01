package com.example.garbagecollection.service;

import com.example.garbagecollection.dto.BinRequestDTO;
import com.example.garbagecollection.entity.Bin;
import com.example.garbagecollection.repository.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    @Autowired
    private BinRepository binRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public Bin createBin(BinRequestDTO binRequestDTO) {
        Bin bin = new Bin();
        bin.setLatitude(binRequestDTO.getLatitude());
        bin.setLongitude(binRequestDTO.getLongitude());
        bin.setStatus((binRequestDTO.getStatus()));
        try {
            String sensorDataJson = objectMapper.writeValueAsString(binRequestDTO.getSensorData());
            bin.setSensorData(sensorDataJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert sensor data to JSON", e);
        }
        bin.setLocationName(binRequestDTO.getLocationName()); // Set location name
        bin.setLastUpdated(LocalDateTime.now());
        return binRepository.save(bin);
    }


    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bin not found with ID: " + id));
    }

    @Override
    public Bin updateBin(Long id, BinRequestDTO binRequestDTO) {
        Bin bin = getBinById(id);
        bin.setLatitude(binRequestDTO.getLatitude());
        bin.setLongitude(binRequestDTO.getLongitude());
        bin.setStatus((binRequestDTO.getStatus()));
        try {
            String sensorDataJson = objectMapper.writeValueAsString(binRequestDTO.getSensorData());
            bin.setSensorData(sensorDataJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert sensor data to JSON", e);
        }
        bin.setLocationName(binRequestDTO.getLocationName()); // Update location name
        bin.setLastUpdated(LocalDateTime.now());
        return binRepository.save(bin);
    }

    @Override
    public void deleteBin(Long id) {
        binRepository.deleteById(id);
    }
    @Override
    public Bin patchBin(Long id, BinRequestDTO binRequestDTO) {
        Bin bin = getBinById(id);

        // Update status if provided in the request
        if (binRequestDTO.getStatus() == (int) Double.NaN) {
            bin.setStatus(binRequestDTO.getStatus());
        }

        // Update sensorData if provided in the request
        if (binRequestDTO.getSensorData() != null) {
            try {
                String sensorDataJson = objectMapper.writeValueAsString(binRequestDTO.getSensorData());
                bin.setSensorData(sensorDataJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to convert sensor data to JSON", e);
            }
        }

        // Update the last updated timestamp
        bin.setLastUpdated(LocalDateTime.now());

        return binRepository.save(bin);
    }


}