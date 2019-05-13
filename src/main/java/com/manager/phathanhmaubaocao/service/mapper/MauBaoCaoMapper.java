package com.manager.phathanhmaubaocao.service.mapper;

import com.manager.phathanhmaubaocao.domain.*;
import com.manager.phathanhmaubaocao.service.dto.MauBaoCaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MauBaoCao and its DTO MauBaoCaoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MauBaoCaoMapper extends EntityMapper<MauBaoCaoDTO, MauBaoCao> {



    default MauBaoCao fromId(String id) {
        if (id == null) {
            return null;
        }
        MauBaoCao mauBaoCao = new MauBaoCao();
        mauBaoCao.setId(id);
        return mauBaoCao;
    }
}
