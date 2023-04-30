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
import partnertelecom.api.domain.tecnico.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TecnicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroTecnico> dadosCadastroTecnicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoTecnico> dadosDetalhamentoTecnicoJson;

    @MockBean
    private TecnicoRepository repository;


    @Test
    @DisplayName("Deve devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/tecnico"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new DadosCadastroTecnico(
                "Medico",
                "medico@voll.med",
                "61999999999",
                Setor.TECNICA,
                "34533291805",
                dadosEndereco());

        when(repository.save(any())).thenReturn(new Tecnico(dadosCadastro));

        var response = mvc
                .perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroTecnicoJson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DadosDetalhamentoTecnico(
                null,
                dadosCadastro.nome(),
                dadosCadastro.email(),
                dadosCadastro.telefone(),
                dadosCadastro.setor(),
                dadosCadastro.cpf(),
                new Endereco(dadosCadastro.endereco())
        );
        var jsonEsperado = dadosDetalhamentoTecnicoJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


    private DadosCadastroEndereco dadosEndereco() {
        return new DadosCadastroEndereco(
                "rua XIV de Maio",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}
