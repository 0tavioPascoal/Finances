package com.Tavin.Finances.controllers;

import com.Tavin.Finances.entities.ReleasesModel;
import com.Tavin.Finances.entities.enuns.StatusReleases;
import com.Tavin.Finances.entities.enuns.TypeReleases;
import com.Tavin.Finances.infra.dto.releasesdto.ReleasesRequestDto;
import com.Tavin.Finances.infra.dto.releasesdto.ReleasesResponseDto;
import com.Tavin.Finances.infra.dto.releasesdto.ReleasesStatusDto;
import com.Tavin.Finances.infra.dto.releasesdto.ReleasesTypeDto;
import com.Tavin.Finances.infra.header.GeneratedHeader;
import com.Tavin.Finances.infra.mappers.releases.ReleasesMapper;
import com.Tavin.Finances.services.releases.ReleasesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("releases")
public class ReleasesController implements GeneratedHeader {

    private final ReleasesService Service;
    private final ReleasesMapper Mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ReleasesRequestDto requestDto){
       var dto = Mapper.releasesModel(requestDto);
       Service.saveRelease(dto);

        URI location = genertatedURI(dto.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody @Valid ReleasesRequestDto requestDto,
                                         @RequestParam String id){
        return Service.findById(UUID.fromString(id))
                .map(ReleasesModel -> {
                    ReleasesModel rmAux = Mapper.releasesModel(requestDto);
                    ReleasesModel.setAmount(rmAux.getAmount());
                    ReleasesModel.setType(rmAux.getType());
                    ReleasesModel.setStatus(rmAux.getStatus());
                    ReleasesModel.setDay(rmAux.getDay());
                    ReleasesModel.setMonth(rmAux.getMonth());
                    ReleasesModel.setYear(rmAux.getYear());
                    ReleasesModel.setUser(rmAux.getUser());
                    ReleasesModel.setDescription(rmAux.getDescription());
                 Service.updateRelease(ReleasesModel);
                 return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/status")
    public ResponseEntity<Object> updatedStatus(@RequestBody @Valid ReleasesStatusDto status
            , @RequestParam String id){
        return Service.findById(UUID.fromString(id))
                .map(ReleasesModel -> {
                    Service.updatedStatus(ReleasesModel, status.status());
                    return ResponseEntity.ok().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/type")
    public ResponseEntity<Object> updatedType(@RequestBody @Valid ReleasesTypeDto typeDto,
                                              @RequestParam String id){
        return Service.findById(UUID.fromString(id))
                .map(ReleasesModel -> {
                    Service.updatedTypeReleases(ReleasesModel, typeDto.type());
                    return ResponseEntity.ok().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping()
    public ResponseEntity<Object> delete(@RequestParam String id){
        return Service.findById(UUID.fromString(id))
                .map(ReleasesModel -> {
                    Service.deleteRelease(ReleasesModel);
                    return ResponseEntity.ok().build();
                }).orElseGet(()-> ResponseEntity.noContent().build());
    }

    @GetMapping()
    public ResponseEntity<Page<ReleasesResponseDto>> getAllReleases(
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "amout", required = false)BigDecimal amount,
            @RequestParam(value = "day", required = false) Integer day,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "type", required = false) TypeReleases type,
            @RequestParam(value = "status", required = false) StatusReleases status,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "sizePage", defaultValue = "10") Integer sizePage
            ){
        Page<ReleasesModel> result = Service.getAllReleases(description,day,month,year,amount
       ,type, status, page, sizePage);

        Page<ReleasesResponseDto> releasesResult = result.map(Mapper::releasesModelResponse);
        return ResponseEntity.ok(releasesResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReleasesResponseDto> findById(@PathVariable String id){
        return Service.findById(UUID.fromString(id))
                .map(ReleasesModel ->
                        ResponseEntity.ok().body(Mapper.releasesModelResponse(ReleasesModel)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/amount")
    public ResponseEntity<BigDecimal> getAmount(@RequestParam String id, @RequestParam TypeReleases type){
        return Service.findById(UUID.fromString(id))
                .map(ReleasesModel -> {
                    Service.getTotalAmount(type, ReleasesModel);
                    return ResponseEntity.ok().body(ReleasesModel.getAmount());
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }
}
