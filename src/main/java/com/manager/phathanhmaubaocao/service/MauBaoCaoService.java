package com.manager.phathanhmaubaocao.service;

import com.manager.phathanhmaubaocao.service.dto.CreateMauBaoCaoDTO;
import com.manager.phathanhmaubaocao.service.dto.MauBaoCaoDTO;
import com.manager.phathanhmaubaocao.service.dto.quytrinhdonvi.DuLieuTienTrinhDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MauBaoCao.
 */
public interface MauBaoCaoService {

    /**
     * Save a mauBaoCao.
     *
     * @param mauBaoCaoDTO the entity to save
     * @return the persisted entity
     */
    MauBaoCaoDTO save(MauBaoCaoDTO mauBaoCaoDTO);

    /**
     * Get all the mauBaoCaos.
     *
     * @return the list of entities
     */
    List<MauBaoCaoDTO> findAll();

    List<MauBaoCaoDTO> findAllSigned();

    /**
     * Get the "id" mauBaoCao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MauBaoCaoDTO> findOne(String id);

    /**
     * Get the "mauBaoCaoCode" mauBaoCao.
     *
     * @param mauBaoCaoCode the mauBaoCaoCode of the entity
     * @return the entity
     */
    Optional<MauBaoCaoDTO> findOneByMauPhatHanhCode(String mauBaoCaoCode);
    /**
     * Delete the "id" mauBaoCao.
     *
     * @param id the id of the entity
     */
    void delete(String id);


    MauBaoCaoDTO create(CreateMauBaoCaoDTO createMauBaoCaoDTO);

    Optional<DuLieuTienTrinhDTO> updateQuyTrinh(String mauPhatHanhCode, DuLieuTienTrinhDTO duLieuTienTrinh);
}
