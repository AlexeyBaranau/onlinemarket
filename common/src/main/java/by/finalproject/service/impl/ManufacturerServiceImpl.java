package by.finalproject.service.impl;

import by.finalproject.domain.Manufacturer;
import by.finalproject.exception.EntityNotFoundException;
import by.finalproject.repository.ManufacturerRepository;
import by.finalproject.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

  private final ManufacturerRepository manufacturerRepository;

  @Override
  public List<Manufacturer> findAll() {
    return manufacturerRepository.findAll();
  }

  @Override
  public Manufacturer findById(Long id) {
    return manufacturerRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(format("Not found manufacturer with id %d", id)));
  }

  @Override
  public Manufacturer create(Manufacturer manufacturer) {
    return manufacturerRepository.save(manufacturer);
  }

  @Override
  public Manufacturer update(Manufacturer manufacturer) {
    return manufacturerRepository.save(manufacturer);
  }

  @Override
  public void delete(Long manufacturerId) {
    manufacturerRepository.deleteById(manufacturerId);
  }
}
