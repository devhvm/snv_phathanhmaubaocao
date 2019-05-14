package com.manager.phathanhmaubaocao.service.impl;

import com.manager.phathanhmaubaocao.client.CommonServiceClient;
import com.manager.phathanhmaubaocao.client.QuyTrinhDonViServiceClient;
import com.manager.phathanhmaubaocao.domain.DuLieuTienTrinh;
import com.manager.phathanhmaubaocao.domain.MauBaoCao;
import com.manager.phathanhmaubaocao.security.SecurityUtils;
import com.manager.phathanhmaubaocao.service.MauBaoCaoService;
import com.manager.phathanhmaubaocao.repository.MauBaoCaoRepository;
import com.manager.phathanhmaubaocao.service.dto.MauBaoCaoDTO;
import com.manager.phathanhmaubaocao.service.dto.common.loaibaocao.LoaiBaoCaoDTO;
import com.manager.phathanhmaubaocao.service.dto.common.loaibaocao.PhamViDetailDTO;
import com.manager.phathanhmaubaocao.service.dto.quytrinhdonvi.DuLieuTienTrinhDTO;
import com.manager.phathanhmaubaocao.service.mapper.DuLieuTienTrinhMapper;
import com.manager.phathanhmaubaocao.service.mapper.MauBaoCaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
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

    @Autowired
    @Qualifier("common")
    CommonServiceClient commonServiceClient;
    @Autowired
    @Qualifier("quytrinhdonvi")
    QuyTrinhDonViServiceClient quyTrinhDonViServiceClient;

    @Autowired
    DuLieuTienTrinhMapper duLieuTienTrinhMapper;


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
        Optional<MauBaoCaoDTO> mauBaoCaoDTO1 = findOneByMauPhatHanhCode(mauBaoCaoDTO.getMauBaoCaoCode());

        if (mauBaoCaoDTO1.isPresent()) {
            throw new RuntimeException("Ma Mau da ton tai");
        }
        MauBaoCao mauBaoCao = mauBaoCaoMapper.toEntity(mauBaoCaoDTO);

        DuLieuTienTrinhDTO duLieuTienTrinhDTO = new DuLieuTienTrinhDTO(mauBaoCaoDTO.getName(), mauBaoCaoDTO.getName(), mauBaoCaoDTO.getMauBaoCaoCode(), SecurityUtils.getCurrentUserLogin().get(), "", "Tao moi", mauBaoCaoDTO.getQuyTrinhDonViId(), mauBaoCaoDTO.getQuyTrinhDonViName(), mauBaoCaoDTO.getStatus());

        try {
            //call API thêm quy trình
            quyTrinhDonViServiceClient.createDuLieuTienTrinh(duLieuTienTrinhDTO);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Khong tao duoc quy trinh");
        }

        DuLieuTienTrinh duLieuTienTrinh = duLieuTienTrinhMapper.toEntity(duLieuTienTrinhDTO);

        mauBaoCao.getDuLieuTienTrinhs().add(duLieuTienTrinh);
        mauBaoCao.setStatus(duLieuTienTrinh.getStatus());

        mauBaoCao = mauBaoCaoRepository.save(mauBaoCao);

        return mauBaoCaoMapper.toDto(mauBaoCao);
    }
    /**
     * Get one mauPhatHanh by mauPhatHanhCode.
     *
     * @param mauBaoCaoCode the code of the entity
     * @return the entity
     */
    @Override
    public Optional<MauBaoCaoDTO> findOneByMauPhatHanhCode(String mauBaoCaoCode) {
        log.debug("Request to get mauBaoCaoCode : {}", mauBaoCaoCode);

        return mauBaoCaoRepository.findByMauBaoCaoCode(mauBaoCaoCode)
                .map(mauBaoCaoMapper::toDto);
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
