package com.manager.phathanhmaubaocao.service.impl;

import com.manager.phathanhmaubaocao.service.MauBaoCaoService;
import com.manager.phathanhmaubaocao.domain.MauBaoCao;
import com.manager.phathanhmaubaocao.repository.MauBaoCaoRepository;
import com.manager.phathanhmaubaocao.service.dto.MauBaoCaoDTO;
import com.manager.phathanhmaubaocao.service.mapper.MauBaoCaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing MauBaoCao.
 */
@Service
public class MauBaoCaoServiceImpl implements MauBaoCaoService {

    private final Logger log = LoggerFactory.getLogger(MauBaoCaoServiceImpl.class);

    private final MauBaoCaoRepository mauBaoCaoRepository;

    private final MauBaoCaoMapper mauBaoCaoMapper;

    public MauBaoCaoServiceImpl(MauBaoCaoRepository mauBaoCaoRepository, MauBaoCaoMapper mauBaoCaoMapper) {
        this.mauBaoCaoRepository = mauBaoCaoRepository;
        this.mauBaoCaoMapper = mauBaoCaoMapper;
    }

    /**
     * Save a mauBaoCao.
     *
     * @param mauBaoCaoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MauBaoCaoDTO save(MauBaoCaoDTO mauBaoCaoDTO) {
        log.debug("Request to save MauBaoCao : {}", mauBaoCaoDTO);
        MauBaoCao mauBaoCao = mauBaoCaoMapper.toEntity(mauBaoCaoDTO);
        mauBaoCao = mauBaoCaoRepository.save(mauBaoCao);
        return mauBaoCaoMapper.toDto(mauBaoCao);
    }

    /**
     * Get all the mauBaoCaos.
     *
     * @return the list of entities
     */
    @Override
    public List<MauBaoCaoDTO> findAll() {
        log.debug("Request to get all MauBaoCaos");
        return mauBaoCaoRepository.findAll().stream()
            .map(mauBaoCaoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one mauBaoCao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<MauBaoCaoDTO> findOne(String id) {
        log.debug("Request to get MauBaoCao : {}", id);
        return mauBaoCaoRepository.findById(id)
            .map(mauBaoCaoMapper::toDto);
    }

    /**
     * Delete the mauBaoCao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete MauBaoCao : {}", id);
        mauBaoCaoRepository.deleteById(id);
    }
}
