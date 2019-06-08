package com.manager.phathanhmaubaocao.web.rest;
import com.manager.phathanhmaubaocao.service.MauBaoCaoService;
import com.manager.phathanhmaubaocao.service.dto.CreateMauBaoCaoDTO;
import com.manager.phathanhmaubaocao.service.dto.quytrinhdonvi.DuLieuTienTrinhDTO;
import com.manager.phathanhmaubaocao.web.rest.errors.BadRequestAlertException;
import com.manager.phathanhmaubaocao.web.rest.util.HeaderUtil;
import com.manager.phathanhmaubaocao.service.dto.MauBaoCaoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MauBaoCao.
 */
@RestController
@RequestMapping("/api")
public class MauBaoCaoResource {

    private final Logger log = LoggerFactory.getLogger(MauBaoCaoResource.class);

    private static final String ENTITY_NAME = "phathanhmaubaocaoMauBaoCao";

    private final MauBaoCaoService mauBaoCaoService;

    public MauBaoCaoResource(MauBaoCaoService mauBaoCaoService) {
        this.mauBaoCaoService = mauBaoCaoService;
    }

    /**
     * POST  /mau-bao-caos : Create a new mauBaoCao.
     *
     * @param createMauBaoCaoDTO the mauBaoCaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mauBaoCaoDTO, or with status 400 (Bad Request) if the mauBaoCao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tao-mau-bao-cao")
    public ResponseEntity<MauBaoCaoDTO> createMauBaoCao(@Valid @RequestBody CreateMauBaoCaoDTO createMauBaoCaoDTO) throws URISyntaxException {
        log.debug("REST request to save MauBaoCao : {}", createMauBaoCaoDTO);

        MauBaoCaoDTO result = mauBaoCaoService.create(createMauBaoCaoDTO);
        return ResponseEntity.created(new URI("/api/mau-bao-caos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    @PutMapping("/mau-bao-cao/{mauBaoCaoCode}/cap-nhat-quy-trinh")
    public ResponseEntity<DuLieuTienTrinhDTO> updateMauPhatHanh(@PathVariable String mauBaoCaoCode, @Valid @RequestBody DuLieuTienTrinhDTO duLieuTienTrinh) {
        log.debug("REST request to  update MauBaoCao : {}", mauBaoCaoCode);

        DuLieuTienTrinhDTO result = mauBaoCaoService.updateQuyTrinh(mauBaoCaoCode, duLieuTienTrinh).get();

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("DuLieuTienTrinh", result.getId().toString()))
                .body(result);

    }
    /**
     * PUT  /mau-bao-caos : Updates an existing mauBaoCao.
     *
     * @param mauBaoCaoDTO the mauBaoCaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mauBaoCaoDTO,
     * or with status 400 (Bad Request) if the mauBaoCaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the mauBaoCaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mau-bao-caos")
    public ResponseEntity<MauBaoCaoDTO> updateMauBaoCao(@Valid @RequestBody MauBaoCaoDTO mauBaoCaoDTO) throws URISyntaxException {
        log.debug("REST request to update MauBaoCao : {}", mauBaoCaoDTO);
        if (mauBaoCaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MauBaoCaoDTO result = mauBaoCaoService.save(mauBaoCaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mauBaoCaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mau-bao-caos : get all the mauBaoCaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mauBaoCaos in body
     */
    @GetMapping("/mau-bao-caos")
    public List<MauBaoCaoDTO> getAllMauBaoCaos() {
        log.debug("REST request to get all MauBaoCaos");
        return mauBaoCaoService.findAll();
    }
    /**
     * GET  /mau-bao-caos : get all the mauBaoCaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mauBaoCaos in body
     */
    @GetMapping("/mau-bao-cao/signed")
    public List<MauBaoCaoDTO> getAllMauBaoCaoSigned() {
        log.debug("REST request to get all MauBaoCaos");
        return mauBaoCaoService.findAllSigned();
    }
    /**
     * GET  /mau-bao-caos/:id : get the "id" mauBaoCao.
     *
     * @param id the id of the mauBaoCaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mauBaoCaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mau-bao-caos/{id}")
    public ResponseEntity<MauBaoCaoDTO> getMauBaoCao(@PathVariable String id) {
        log.debug("REST request to get MauBaoCao : {}", id);
        Optional<MauBaoCaoDTO> mauBaoCaoDTO = mauBaoCaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mauBaoCaoDTO);
    }

    /**
     * DELETE  /mau-bao-caos/:id : delete the "id" mauBaoCao.
     *
     * @param id the id of the mauBaoCaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mau-bao-caos/{id}")
    public ResponseEntity<Void> deleteMauBaoCao(@PathVariable String id) {
        log.debug("REST request to delete MauBaoCao : {}", id);
        mauBaoCaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
