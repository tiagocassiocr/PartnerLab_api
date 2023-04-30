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
import partnertelecom.api.domain.cliente.Cliente;
import partnertelecom.api.domain.cliente.ClienteRepository;
import partnertelecom.api.domain.cliente.DadosCadastroCliente;
import partnertelecom.api.domain.cliente.DadosDetalhamentoCliente;
import partnertelecom.api.domain.endereco.DadosCadastroEndereco;
import partnertelecom.api.domain.endereco.Endereco;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroCliente> dadosCadastroClienteJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoCliente> dadosDetalhamentoClienteJson;

    @MockBean
    private ClienteRepository repository;


    @Test
    @DisplayName("Deve devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/cliente"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new DadosCadastroCliente(
                "Cliente",
                "cliente@cliente.com",
                "61999999999",
                "34533291805",
                 dadosEndereco());

        when(repository.save(any())).thenReturn(new Cliente(dadosCadastro));

        var response = mvc
                .perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroClienteJson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DadosDetalhamentoCliente(
                null,
                dadosCadastro.nome(),
                dadosCadastro.email(),
                dadosCadastro.telefone(),
                dadosCadastro.cnpj(),
                new Endereco(dadosCadastro.endereco())
        );
        var jsonEsperado = dadosDetalhamentoClienteJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


    private DadosCadastroEndereco dadosEndereco() {
        return new DadosCadastroEndereco(
                "rua XIV de Maio",
                "bairro",
                "00000000",
                "Sao Paulo",
                "DF",
                null,
                null
        );
    }

}
