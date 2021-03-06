package com.medicai.pillpal.repository;

import com.medicai.pillpal.domain.AppInfoSideEffect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the AppInfoSideEffect entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppInfoSideEffectRepository extends JpaRepository<AppInfoSideEffect, Long> {

    @Query("select appInfoSideEffect from AppInfoSideEffect appInfoSideEffect " +
        "inner join appInfoSideEffect.applicationInfo ai  " +
        "inner join appInfoSideEffect.sideEffect se " +
        "where ai.genericName = :genericName ")
    Optional<AppInfoSideEffect> findByGenericName(@Param("genericName") String genericName);

    @Query("select appInfoSideEffect from AppInfoSideEffect appInfoSideEffect " +
        "inner join appInfoSideEffect.applicationInfo ai  " +
        "inner join appInfoSideEffect.sideEffect se " +
        "where ai.genericName in :genericNameList ")
    Page<AppInfoSideEffect> findByGenericNameList(Pageable pageable,
                                                  @Param("genericNameList") List<String> genericNameList);

}
