package com.manager.phathanhmaubaocao.service.mapper;


import com.manager.phathanhmaubaocao.domain.enumeration.Status;
import com.manager.phathanhmaubaocao.service.dto.StatusDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity DuLieuTienTrinh and its DTO DuLieuTienTrinhDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatusMapper extends EntityMapper<StatusDTO, Status> {

    default Status fromId(String id) {
        if (id == null) {
            return null;
        }
        Status status = Status.NEW;
        return status;
    }
}
