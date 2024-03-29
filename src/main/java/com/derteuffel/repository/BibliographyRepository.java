package com.derteuffel.repository;

import com.derteuffel.data.Bibliography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by derteuffel on 30/01/2019.
 */
@Repository
public interface BibliographyRepository extends JpaRepository<Bibliography,Long> {

    @Query("select b from Bibliography as b join b.these bt where bt.theseId=:id order by b.bibliographyId desc")
    List<Bibliography> findAllByThese(@Param("id") Long theseId);
    Bibliography findByTitle(String title);
}
