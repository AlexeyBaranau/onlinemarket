package by.finalproject.controller;

import by.finalproject.controller.request.manufacturer.ManufacturerChange;
import by.finalproject.controller.request.manufacturer.ManufacturerCreate;
import by.finalproject.converter.manufacturer.ManufacturerMapper;
import by.finalproject.domain.Manufacturer;
import by.finalproject.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {

  private final ManufacturerService manufacturerService;
  private final ManufacturerMapper manufacturerMapper;

  @GetMapping
  public ResponseEntity<List<Manufacturer>> findAll() {
    return new ResponseEntity<>(manufacturerService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Manufacturer> findById(@PathVariable Long id) {
    return new ResponseEntity<>(manufacturerService.findById(id), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<Manufacturer> manufacturerCreate(
      @RequestBody ManufacturerCreate manufacturerCreate) {
    Manufacturer manufacturer =
        manufacturerMapper.manufacturerDTOtoManufacturer(manufacturerCreate);
    return new ResponseEntity<>(manufacturerService.create(manufacturer), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  public ResponseEntity<Manufacturer> manufacturerUpdate(
      @RequestBody ManufacturerChange manufacturerChange) {
    Manufacturer m = manufacturerService.findById(manufacturerChange.getId());
    Manufacturer manufacturer =
        manufacturerMapper.manufacturerDTOtoManufacturer(manufacturerChange, m);
    return new ResponseEntity<>(manufacturerService.update(manufacturer), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Manufacturer> manufacturerDelete(@PathVariable Long id) {
    manufacturerService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
