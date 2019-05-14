package com.manager.phathanhmaubaocao.domain;


import com.manager.phathanhmaubaocao.domain.common.coquanchutri.TieuChiDetail;
import com.manager.phathanhmaubaocao.domain.common.loaibaocao.LoaiBaoCao;
import com.manager.phathanhmaubaocao.domain.enumeration.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A MauPhatHanh.
 */
@Document(collection = "mau_phat_bao_cao")
public class MauBaoCao extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("mau_bao_cao_code")
    private String mauBaoCaoCode;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("ky_cong_bo")
    private CustomType kyCongBo;


    @NotNull
    @Field("mau_phat_hanh")
    private CustomType mauPhatHanh;

    //@NotNull
    @Field("loai_bao_cao")
    private LoaiBaoCao loaiBaoCao;

    @NotNull
    @Field("co_quan_hanh_chinh_code")
    private String coQuanHanhChinhCode;

    @NotNull
    @Field("han_nop_ho_so")
    private LocalDateTime hanNopHoSo;

    @NotNull
    @Field("status")
    private Status status;

    @NotNull
    @Field("tieu_chi_mau_bao_cao")
    private List<TieuChiDetail> tieuChiDetails;

    @Field("du_lieu_tien_trinh")
    private List<DuLieuTienTrinh> duLieuTienTrinhs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMauBaoCaoCode() {
        return mauBaoCaoCode;
    }

    public void setMauBaoCaoCode(String mauBaoCaoCode) {
        this.mauBaoCaoCode = mauBaoCaoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomType getKyCongBo() {
        return kyCongBo;
    }

    public void setKyCongBo(CustomType kyCongBo) {
        this.kyCongBo = kyCongBo;
    }

    public CustomType getMauPhatHanh() {
        return mauPhatHanh;
    }

    public void setMauPhatHanh(CustomType mauPhatHanh) {
        this.mauPhatHanh = mauPhatHanh;
    }

    public LoaiBaoCao getLoaiBaoCao() {
        return loaiBaoCao;
    }

    public void setLoaiBaoCao(LoaiBaoCao loaiBaoCao) {
        this.loaiBaoCao = loaiBaoCao;
    }

    public String getCoQuanHanhChinhCode() {
        return coQuanHanhChinhCode;
    }

    public void setCoQuanHanhChinhCode(String coQuanHanhChinhCode) {
        this.coQuanHanhChinhCode = coQuanHanhChinhCode;
    }

    public LocalDateTime getHanNopHoSo() {
        return hanNopHoSo;
    }

    public void setHanNopHoSo(LocalDateTime hanNopHoSo) {
        this.hanNopHoSo = hanNopHoSo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<TieuChiDetail> getTieuChiDetails() {
        return tieuChiDetails;
    }

    public void setTieuChiDetails(List<TieuChiDetail> tieuChiDetails) {
        this.tieuChiDetails = tieuChiDetails;
    }

    public List<DuLieuTienTrinh> getDuLieuTienTrinhs() {
        return duLieuTienTrinhs;
    }

    public void setDuLieuTienTrinhs(List<DuLieuTienTrinh> duLieuTienTrinhs) {
        this.duLieuTienTrinhs = duLieuTienTrinhs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MauBaoCao mauPhatHanh = (MauBaoCao) o;
        if (mauPhatHanh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mauPhatHanh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
