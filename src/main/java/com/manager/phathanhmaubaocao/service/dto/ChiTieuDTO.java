package com.manager.phathanhmaubaocao.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ChiTieu entity.
 */
public class ChiTieuDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String chiTieuCode;

    @NotNull
    private String name;

    @NotNull
    private StatusDTO status;


    private Long nhomChiTieuId;

    private String nhomChiTieuName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChiTieuCode() {
        return chiTieuCode;
    }

    public void setChiTieuCode(String chiTieuCode) {
        this.chiTieuCode = chiTieuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public Long getNhomChiTieuId() {
        return nhomChiTieuId;
    }

    public void setNhomChiTieuId(Long nhomChiTieuId) {
        this.nhomChiTieuId = nhomChiTieuId;
    }

    public String getNhomChiTieuName() {
        return nhomChiTieuName;
    }

    public void setNhomChiTieuName(String nhomChiTieuName) {
        this.nhomChiTieuName = nhomChiTieuName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChiTieuDTO chiTieuDTO = (ChiTieuDTO) o;
        if (chiTieuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chiTieuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChiTieuDTO{" +
            "id=" + getId() +
            ", chiTieuCode='" + getChiTieuCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", nhomChiTieu=" + getNhomChiTieuId() +
            ", nhomChiTieu='" + getNhomChiTieuName() + "'" +
            "}";
    }
}
