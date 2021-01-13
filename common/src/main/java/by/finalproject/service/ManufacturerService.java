package by.finalproject.service;

import by.finalproject.domain.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> findAll();

    Manufacturer findById(Long id);

    Manufacturer create(Manufacturer manufacturer);

    Manufacturer update(Manufacturer manufacturer);

    void delete(Long manufacturerId);
}
