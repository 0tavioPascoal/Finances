package com.Tavin.Finances.services.releases;

import com.Tavin.Finances.entities.ReleasesModel;
import com.Tavin.Finances.entities.UserModel;
import com.Tavin.Finances.entities.enuns.StatusReleases;
import com.Tavin.Finances.entities.enuns.TypeReleases;
import com.Tavin.Finances.repository.ReleasesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReleasesService {

    private final ReleasesRepository Repository;

    public void saveRelease(ReleasesModel releasesModel) {
         Repository.save(releasesModel);
    }

    public void deleteRelease(ReleasesModel releasesModel) {
        Repository.delete(releasesModel);
    }

    public Page<ReleasesModel> getAllReleases(String Description,
                                              Integer day,
                                              Integer month,
                                              Integer year,
                                              BigDecimal amount,
                                              TypeReleases type,
                                              StatusReleases status,
                                              Integer page,
                                              Integer pageSize) {

        ReleasesModel rm= new ReleasesModel();
        rm.setDescription(Description);
        rm.setDay(day);
        rm.setMonth(month);
        rm.setYear(year);
        rm.setAmount(amount);
        rm.setType(type);
        rm.setStatus(status);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example<ReleasesModel> ex = Example.of(rm, matcher);

        Pageable pageable = PageRequest.of(page, pageSize);
        return Repository.findAll(ex, pageable);

    }

    public Optional<ReleasesModel> findById(UUID id) {
        return Repository.findById(id);
    }

    public void updateRelease(ReleasesModel releasesModel) {
        if(releasesModel.getId()==null) {
            throw new IllegalArgumentException("To update, the releases must already be registered");
        }
        Repository.save(releasesModel);
    }

    public void updatedStatus(ReleasesModel releasesModel, StatusReleases status) {
        releasesModel.setStatus(status);
        Repository.save(releasesModel);
    }

    public void updatedTypeReleases(ReleasesModel releasesModel, TypeReleases type) {
        releasesModel.setType(type);
        Repository.save(releasesModel);
    }


}
