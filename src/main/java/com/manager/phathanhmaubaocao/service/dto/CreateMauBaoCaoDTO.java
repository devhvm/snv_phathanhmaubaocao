package com.manager.phathanhmaubaocao.service.dto;

import com.manager.phathanhmaubaocao.service.dto.common.coquanchutri.TieuChiDetailDTO;
import com.manager.phathanhmaubaocao.service.dto.common.loaibaocao.LoaiBaoCaoDTO;
import com.manager.phathanhmaubaocao.service.dto.quytrinhdonvi.DuLieuTienTrinhDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateMauBaoCaoDTO {


    @NotNull
    private String mauBaoCaoCode;

    @NotNull
    private String name;

    @NotNull
    private CustomTypeDTO kyCongBo;


    @NotNull
    private CustomTypeDTO mauPhatHanh;

    @NotNull
    private LoaiBaoCaoDTO loaiBaoCao;

    @NotNull
    private String coQuanHanhChinhCode;

    @NotNull
    private LocalDateTime hanNopHoSo;


    private List<TieuChiDetailDTO> tieuChiDetails;


    @NotNull
    private String tienTrinhCode;
    @NotNull
    private Long quyTrinhDonViId;
    @NotNull
    private String quyTrinhDonViName;




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

    public CustomTypeDTO getKyCongBo() {
        return kyCongBo;
    }

    public void setKyCongBo(CustomTypeDTO kyCongBo) {
        this.kyCongBo = kyCongBo;
    }

    public CustomTypeDTO getMauPhatHanh() {
        return mauPhatHanh;
    }

    public void setMauPhatHanh(CustomTypeDTO mauPhatHanh) {
        this.mauPhatHanh = mauPhatHanh;
    }

    public LoaiBaoCaoDTO getLoaiBaoCao() {
        return loaiBaoCao;
    }

    public void setLoaiBaoCao(LoaiBaoCaoDTO loaiBaoCao) {
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



    public List<TieuChiDetailDTO> getTieuChiDetails() {
        return tieuChiDetails;
    }

    public void setTieuChiDetails(List<TieuChiDetailDTO> tieuChiDetails) {
        this.tieuChiDetails = tieuChiDetails;
    }


    public String getTienTrinhCode() {
        return tienTrinhCode;
    }

    public void setTienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
    }

    public Long getQuyTrinhDonViId() {
        return quyTrinhDonViId;
    }

    public void setQuyTrinhDonViId(Long quyTrinhDonViId) {
        this.quyTrinhDonViId = quyTrinhDonViId;
    }

    public String getQuyTrinhDonViName() {
        return quyTrinhDonViName;
    }

    public void setQuyTrinhDonViName(String quyTrinhDonViName) {
        this.quyTrinhDonViName = quyTrinhDonViName;
    }


}
