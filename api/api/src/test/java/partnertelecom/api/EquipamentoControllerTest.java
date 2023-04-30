package partnertelecom.api;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import partnertelecom.api.domain.endereco.DadosCadastroEndereco;
import partnertelecom.api.domain.endereco.Endereco;
import partnertelecom.api.domain.equipamento.DadosCadastroEquipamento;
import partnertelecom.api.domain.equipamento.DadosDetalhamentoEquipamento;
import partnertelecom.api.domain.equipamento.Equipamento;
import partnertelecom.api.domain.equipamento.EquipamentoRepository;
import partnertelecom.api.domain.tecnico.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class EquipamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroEquipamento> dadosCadastroEquipamentoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoEquipamento> dadosDetalhamentoEquipamentoJson;

    @MockBean
    private EquipamentoRepository repository;


    @Test
    @DisplayName("Deve devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/equipamento"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new DadosCadastroEquipamento(
                "modelo",
                "fabricante",
                "61999999999");


        when(repository.save(any())).thenReturn(new Equipamento(dadosCadastro));

        var response = mvc
                .perform(post("/equipamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroEquipamentoJson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DadosDetalhamentoEquipamento(
                null,
                dadosCadastro.modelo(),
                dadosCadastro.fabricante(),
                dadosCadastro.numerodeserie()
        );
        var jsonEsperado = dadosDetalhamentoEquipamentoJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }




}
