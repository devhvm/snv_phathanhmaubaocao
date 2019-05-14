package com.manager.phathanhmaubaocao.web.rest;

import com.manager.phathanhmaubaocao.PhathanhmaubaocaoApp;

import com.manager.phathanhmaubaocao.repository.MauBaoCaoRepository;
import com.manager.phathanhmaubaocao.service.MauBaoCaoService;
import com.manager.phathanhmaubaocao.service.dto.MauBaoCaoDTO;
import com.manager.phathanhmaubaocao.service.mapper.MauBaoCaoMapper;
import com.manager.phathanhmaubaocao.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.List;


import static com.manager.phathanhmaubaocao.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.manager.phathanhmaubaocao.domain.enumeration.Status;
/**
 * Test class for the MauBaoCaoResource REST controller.
 *
 * @see MauBaoCaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhathanhmaubaocaoApp.class)
public class MauBaoCaoResourceIntTest {

    private static final String DEFAULT_MAU_PHAT_HANH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MAU_PHAT_HANH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.NEW;
    private static final Status UPDATED_STATUS = Status.ACTIVE;

    @Autowired
    private MauBaoCaoRepository mauBaoCaoRepository;

    @Autowired
    private MauBaoCaoMapper mauBaoCaoMapper;

    @Autowired
    private MauBaoCaoService mauBaoCaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restMauBaoCaoMockMvc;

    private MauBaoCao mauBaoCao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MauBaoCaoResource mauBaoCaoResource = new MauBaoCaoResource(mauBaoCaoService);
        this.restMauBaoCaoMockMvc = MockMvcBuilders.standaloneSetup(mauBaoCaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MauBaoCao createEntity() {
        MauBaoCao mauBaoCao = new MauBaoCao()
            .mauPhatHanhCode(DEFAULT_MAU_PHAT_HANH_CODE)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return mauBaoCao;
    }

    @Before
    public void initTest() {
        mauBaoCaoRepository.deleteAll();
        mauBaoCao = createEntity();
    }

    @Test
    public void createMauBaoCao() throws Exception {
        int databaseSizeBeforeCreate = mauBaoCaoRepository.findAll().size();

        // Create the MauBaoCao
        MauBaoCaoDTO mauBaoCaoDTO = mauBaoCaoMapper.toDto(mauBaoCao);
        restMauBaoCaoMockMvc.perform(post("/api/mau-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauBaoCaoDTO)))
            .andExpect(status().isCreated());

        // Validate the MauBaoCao in the database
        List<MauBaoCao> mauBaoCaoList = mauBaoCaoRepository.findAll();
        assertThat(mauBaoCaoList).hasSize(databaseSizeBeforeCreate + 1);
        MauBaoCao testMauBaoCao = mauBaoCaoList.get(mauBaoCaoList.size() - 1);
        assertThat(testMauBaoCao.getMauPhatHanhCode()).isEqualTo(DEFAULT_MAU_PHAT_HANH_CODE);
        assertThat(testMauBaoCao.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMauBaoCao.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createMauBaoCaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mauBaoCaoRepository.findAll().size();

        // Create the MauBaoCao with an existing ID
        mauBaoCao.setId("existing_id");
        MauBaoCaoDTO mauBaoCaoDTO = mauBaoCaoMapper.toDto(mauBaoCao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMauBaoCaoMockMvc.perform(post("/api/mau-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MauBaoCao in the database
        List<MauBaoCao> mauBaoCaoList = mauBaoCaoRepository.findAll();
        assertThat(mauBaoCaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkMauPhatHanhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mauBaoCaoRepository.findAll().size();
        // set the field null
        mauBaoCao.setMauPhatHanhCode(null);

        // Create the MauBaoCao, which fails.
        MauBaoCaoDTO mauBaoCaoDTO = mauBaoCaoMapper.toDto(mauBaoCao);

        restMauBaoCaoMockMvc.perform(post("/api/mau-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<MauBaoCao> mauBaoCaoList = mauBaoCaoRepository.findAll();
        assertThat(mauBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = mauBaoCaoRepository.findAll().size();
        // set the field null
        mauBaoCao.setName(null);

        // Create the MauBaoCao, which fails.
        MauBaoCaoDTO mauBaoCaoDTO = mauBaoCaoMapper.toDto(mauBaoCao);

        restMauBaoCaoMockMvc.perform(post("/api/mau-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<MauBaoCao> mauBaoCaoList = mauBaoCaoRepository.findAll();
        assertThat(mauBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = mauBaoCaoRepository.findAll().size();
        // set the field null
        mauBaoCao.setStatus(null);

        // Create the MauBaoCao, which fails.
        MauBaoCaoDTO mauBaoCaoDTO = mauBaoCaoMapper.toDto(mauBaoCao);

        restMauBaoCaoMockMvc.perform(post("/api/mau-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<MauBaoCao> mauBaoCaoList = mauBaoCaoRepository.findAll();
        assertThat(mauBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllMauBaoCaos() throws Exception {
        // Initialize the database
        mauBaoCaoRepository.save(mauBaoCao);

        // Get all the mauBaoCaoList
        restMauBaoCaoMockMvc.perform(get("/api/mau-bao-caos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mauBaoCao.getId())))
            .andExpect(jsonPath("$.[*].mauPhatHanhCode").value(hasItem(DEFAULT_MAU_PHAT_HANH_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    public void getMauBaoCao() throws Exception {
        // Initialize the database
        mauBaoCaoRepository.save(mauBaoCao);

        // Get the mauBaoCao
        restMauBaoCaoMockMvc.perform(get("/api/mau-bao-caos/{id}", mauBaoCao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mauBaoCao.getId()))
            .andExpect(jsonPath("$.mauPhatHanhCode").value(DEFAULT_MAU_PHAT_HANH_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    public void getNonExistingMauBaoCao() throws Exception {
        // Get the mauBaoCao
        restMauBaoCaoMockMvc.perform(get("/api/mau-bao-caos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMauBaoCao() throws Exception {
        // Initialize the database
        mauBaoCaoRepository.save(mauBaoCao);

        int databaseSizeBeforeUpdate = mauBaoCaoRepository.findAll().size();

        // Update the mauBaoCao
        MauBaoCao updatedMauBaoCao = mauBaoCaoRepository.findById(mauBaoCao.getId()).get();
        updatedMauBaoCao
            .mauPhatHanhCode(UPDATED_MAU_PHAT_HANH_CODE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        MauBaoCaoDTO mauBaoCaoDTO = mauBaoCaoMapper.toDto(updatedMauBaoCao);

        restMauBaoCaoMockMvc.perform(put("/api/mau-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauBaoCaoDTO)))
            .andExpect(status().isOk());

        // Validate the MauBaoCao in the database
        List<MauBaoCao> mauBaoCaoList = mauBaoCaoRepository.findAll();
        assertThat(mauBaoCaoList).hasSize(databaseSizeBeforeUpdate);
        MauBaoCao testMauBaoCao = mauBaoCaoList.get(mauBaoCaoList.size() - 1);
        assertThat(testMauBaoCao.getMauPhatHanhCode()).isEqualTo(UPDATED_MAU_PHAT_HANH_CODE);
        assertThat(testMauBaoCao.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMauBaoCao.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingMauBaoCao() throws Exception {
        int databaseSizeBeforeUpdate = mauBaoCaoRepository.findAll().size();

        // Create the MauBaoCao
        MauBaoCaoDTO mauBaoCaoDTO = mauBaoCaoMapper.toDto(mauBaoCao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMauBaoCaoMockMvc.perform(put("/api/mau-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mauBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MauBaoCao in the database
        List<MauBaoCao> mauBaoCaoList = mauBaoCaoRepository.findAll();
        assertThat(mauBaoCaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMauBaoCao() throws Exception {
        // Initialize the database
        mauBaoCaoRepository.save(mauBaoCao);

        int databaseSizeBeforeDelete = mauBaoCaoRepository.findAll().size();

        // Delete the mauBaoCao
        restMauBaoCaoMockMvc.perform(delete("/api/mau-bao-caos/{id}", mauBaoCao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MauBaoCao> mauBaoCaoList = mauBaoCaoRepository.findAll();
        assertThat(mauBaoCaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MauBaoCao.class);
        MauBaoCao mauBaoCao1 = new MauBaoCao();
        mauBaoCao1.setId("id1");
        MauBaoCao mauBaoCao2 = new MauBaoCao();
        mauBaoCao2.setId(mauBaoCao1.getId());
        assertThat(mauBaoCao1).isEqualTo(mauBaoCao2);
        mauBaoCao2.setId("id2");
        assertThat(mauBaoCao1).isNotEqualTo(mauBaoCao2);
        mauBaoCao1.setId(null);
        assertThat(mauBaoCao1).isNotEqualTo(mauBaoCao2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MauBaoCaoDTO.class);
        MauBaoCaoDTO mauBaoCaoDTO1 = new MauBaoCaoDTO();
        mauBaoCaoDTO1.setId("id1");
        MauBaoCaoDTO mauBaoCaoDTO2 = new MauBaoCaoDTO();
        assertThat(mauBaoCaoDTO1).isNotEqualTo(mauBaoCaoDTO2);
        mauBaoCaoDTO2.setId(mauBaoCaoDTO1.getId());
        assertThat(mauBaoCaoDTO1).isEqualTo(mauBaoCaoDTO2);
        mauBaoCaoDTO2.setId("id2");
        assertThat(mauBaoCaoDTO1).isNotEqualTo(mauBaoCaoDTO2);
        mauBaoCaoDTO1.setId(null);
        assertThat(mauBaoCaoDTO1).isNotEqualTo(mauBaoCaoDTO2);
    }
}
