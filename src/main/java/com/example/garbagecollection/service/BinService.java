package com.example.garbagecollection.service;

import com.example.garbagecollection.dto.BinRequestDTO;
import com.example.garbagecollection.entity.Bin;
import java.util.List;

public interface BinService {
    List<Bin> getAllBins();
    Bin createBin(BinRequestDTO binRequestDTO);
    Bin getBinById(Long id);
    Bin updateBin(Long id, BinRequestDTO binRequestDTO);
    void deleteBin(Long id);
    Bin patchBin(Long id, BinRequestDTO binRequestDTO);

}
