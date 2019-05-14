package com.manager.phathanhmaubaocao.service.mapper;

import com.manager.phathanhmaubaocao.domain.DuLieuTienTrinh;
import com.manager.phathanhmaubaocao.service.dto.quytrinhdonvi.DuLieuTienTrinhDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity DuLieuTienTrinh and its DTO DuLieuTienTrinhDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DuLieuTienTrinhMapper extends EntityMapper<DuLieuTienTrinhDTO, DuLieuTienTrinh> {

    default DuLieuTienTrinh fromId(String id) {
        if (id == null) {
            return null;
        }
        DuLieuTienTrinh duLieuTienTrinh = new DuLieuTienTrinh();
        return duLieuTienTrinh;
    }
}
