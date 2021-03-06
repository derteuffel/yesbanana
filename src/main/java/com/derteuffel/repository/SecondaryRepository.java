package com.derteuffel.repository;

import com.derteuffel.data.Secondary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by derteuffel on 27/12/2018.
 */
@Repository
public interface SecondaryRepository extends JpaRepository<Secondary, Long> {

    @Query("select s from Secondary as s join s.region sr where sr.regionId=:id order by s.educationId desc")
    List<Secondary> findAllByRegion(@Param("id") Long regionId);
}

