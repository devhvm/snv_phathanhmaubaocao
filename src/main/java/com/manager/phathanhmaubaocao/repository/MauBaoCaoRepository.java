package com.manager.phathanhmaubaocao.repository;

import com.manager.phathanhmaubaocao.domain.MauBaoCao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the MauBaoCao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MauBaoCaoRepository extends MongoRepository<MauBaoCao, String> {

}
