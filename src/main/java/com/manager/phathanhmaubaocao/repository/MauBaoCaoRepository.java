package com.manager.phathanhmaubaocao.repository;

import com.manager.phathanhmaubaocao.domain.MauBaoCao;
import com.manager.phathanhmaubaocao.domain.enumeration.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data MongoDB repository for the MauBaoCao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MauBaoCaoRepository extends MongoRepository<MauBaoCao, String> {
    Optional<MauBaoCao> findByMauBaoCaoCode(String mauBaoCaoCode);

    @Query(fields = "{ 'mauBaoCaoCode' : 1}")
    List<MauBaoCao>  findAllByStatus(Status status);
}
