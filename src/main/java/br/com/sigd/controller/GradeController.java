package br.com.sigd.controller;

import Enum.GradeType;
import br.com.sigd.model.Academico;
import br.com.sigd.model.Banca;
import br.com.sigd.model.Grade;
import br.com.sigd.model.Turma;
import br.com.sigd.repository.GradeRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author FernandoChagas
 */
@URLMappings(mappings = {
    @URLMapping(parentId = "coordenador", id = "grade-index", pattern = "grade", viewId = "/usuarios/coordenador/grade/index.xhtml"),
    @URLMapping(parentId = "grade-index", id = "grade-banca", pattern = "/grade-banca", viewId = "/usuarios/coordenador/grade/grade-banca.xhtml"),
    @URLMapping(parentId = "grade-index", id = "grade-turma", pattern = "/grade-turma", viewId = "/usuarios/coordenador/grade/grade-turma.xhtml"),
    @URLMapping(parentId = "grade-index", id = "linha-do-tempo", pattern = "/linha-do-tempo", viewId = "/usuarios/coordenador/grade/linha-do-tempo.xhtml"),
    @URLMapping(parentId = "professor", id = "grade-index-professor", pattern = "/grade", viewId = "/usuarios/coordenador/grade/grade-banca.xhtml"),
    @URLMapping(parentId = "academico", id = "grade-index-academico", pattern = "/grade", viewId = "/usuarios/academico/grade/index.xhtml"),
    @URLMapping(parentId = "grade-index-academico", id = "avaliar-turmas", pattern = "/avaliar-turmas", viewId = "/usuarios/academico/grade/avaliar-turmas.xhtml"),
    @URLMapping(parentId = "grade-index-academico", id = "minha-banca", pattern = "/minha-banca", viewId = "/usuarios/academico/grade/minha-banca.xhtml"),})
@Named("GradeController")
@SessionScoped
public class GradeController extends BasicBeanContent<Grade, GradeRepository> implements Serializable {

    @Inject
    UsuarioController usuarioController;
    @Inject
    BancaController bancaController;
    private Academico auxAcademico;
    private Banca auxBanca;
    private Turma auxTurma;
    private Long idBanca;
    private CartesianChartModel linhaDoTempo;

    public GradeController() {
        this.entity = new Grade();
        System.out.println("teste" + this.entity);
        criarLinhaDoTempo();
    }

    @Override
    public void insert() {
        this.repository = new GradeRepository(ApplicationUtilies.catchEntityManager());
        this.entity.setDataDeCriacao(new Date());
        this.entity.setIdAvaliador(usuarioController.getLoggedUsuario().getId());
        this.repository.insert(this.entity);
        limparController();
    }

    @Override
    public void remove() {
        this.repository = new GradeRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        limparController();
    }

    @Override
    public void update() {
        this.repository = new GradeRepository(ApplicationUtilies.catchEntityManager());
        this.entity.setDataDeAtualizacao(new Date());
        this.repository.update(this.entity);
        limparController();
    }

    public List<Grade> getList() {
        this.repository = new GradeRepository(ApplicationUtilies.catchEntityManager());
        this.listOfEntities = this.repository.getListOfElements();
        return this.listOfEntities;
    }

    /*
     * Método de inserção da nota de uma determinada BANCA (tipo, avaliado)
     * por COORDENADOR e/ou PROFESSOR (Avaliador)
     * Busca: todos as bancas defendidas
     */
    public void avaliarBanca() {
        Long somatorio = this.entity.getValor();
        for (int i = 0; i < this.auxBanca.getAvaliadores().size(); i++) {
            Grade aux = carregarPorAvaliador(this.auxBanca.getAvaliadores().get(i).getId());
            if (aux != null) {
                somatorio += aux.getValor();
            }
        }
        this.auxBanca.setNotaMedia(Double.parseDouble(Long.toString(somatorio / this.auxBanca.getAvaliadores().size())));
        //this.bancaController.setEntity(this.auxBanca);
        //this.bancaController.update();
        this.entity.setTipo(GradeType.BANCA);
        this.entity.setIdAvaliado(this.auxBanca.getId());
        insert();
    }

    /*
     * Método de avaliação da TURMA (Tipo, avaliado) por um ALUNO (Avaliador)
     * Busca: todos as turmas que o aluno faz parte
     */
    public void avaliarTurmaPorAluno() {
        this.entity.setTipo(GradeType.TURMAALUNO);
        this.entity.setIdAvaliado(this.auxTurma.getId());
        insert();
    }

    /*
     * Método de avaliação de um ALUNO (Avaliado) de uma TURMA(Tipo) por um COORDENADOR (Avaliador).
     * Busca: todas as turmas de um coordenador, todos os alunos de uma turma
     */
    public void avaliarAlunoTurma() {
        this.entity.setTipo(GradeType.TURMACOORDENADOR);
        this.entity.setIdAvaliado(this.auxAcademico.getId());
        this.entity.setIdTipoAvaliado(this.auxTurma.getId());
        insert();
    }

    /*
     * Método de inserção de nota na LINHA DO TEMPO (Tipo) de um ALUNO (Avaliado) por um COOORDENADOR (Avaliador)
     * Busca: Autocomplete com todos os alunos de um curso
     */
    public void inserirAvaliacaoLinhaTempo() {
        this.entity.setTipo(GradeType.LINHADOTEMPO);
        this.entity.setIdAvaliado(this.auxAcademico.getId());
        insert();
    }

    public List<Grade> getPorBanca() {
        this.repository = new GradeRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getListaPorAvaliado(this.auxBanca.getId());
    }

    public Grade carregarPorAvaliador(Long umId) {
        this.repository = new GradeRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getListaPorAvaliador(umId);
    }

    public List<Grade> carregarPorAvaliado(Long umId) {
        this.repository = new GradeRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getListaPorAvaliado(umId);
    }

    public Long getTotal() {
        Long somador = 0L;
        if (this.auxAcademico != null) {
            List<Grade> aux = carregarPorAvaliado(this.auxAcademico.getId());
            for (int i = 0; i < aux.size(); i++) {
                somador += aux.get(i).getValor();
            }
        }

        return somador;
    }

    public void criarLinhaDoTempo() {
        linhaDoTempo = new CartesianChartModel();

        LineChartSeries series1 = new LineChartSeries();
        if (this.auxAcademico != null) {
            series1.setLabel(this.auxAcademico.getNome());
        } else {
            series1.setLabel("teste");
        }

        List<Grade> aux = null;
        try {
            aux = carregarPorAvaliado(this.auxAcademico.getId());
        } catch (NullPointerException n) {
            System.err.println("erro");
        }

        if (aux != null) {

            Long somatorio = 0L;

            for (int i = 0; i < aux.size(); i++) {
                somatorio += aux.get(i).getValor();
                series1.set(aux.get(i).getDataDeCriacao(), somatorio);
            }

        }

        linhaDoTempo.addSeries(series1);
    }

    public void limparController() {
        this.entity = new Grade();
        this.auxAcademico = null;
        this.auxBanca = null;
        this.auxTurma = null;
        this.listOfEntities.clear();
    }

    /* Getters and Setters */
    public Academico getAuxAcademico() {
        return auxAcademico;
    }

    public void setAuxAcademico(Academico auxAcademico) {
        this.auxAcademico = auxAcademico;
    }

    public Banca getAuxBanca() {
        return auxBanca;
    }

    public void setAuxBanca(Banca auxBanca) {
        this.auxBanca = auxBanca;
    }

    public Turma getAuxTurma() {
        return auxTurma;
    }

    public void setAuxTurma(Turma auxTurma) {
        this.auxTurma = auxTurma;
    }

    public Long getIdBanca() {
        return idBanca;
    }

    public void setIdBanca(Long idBanca) {
        this.idBanca = idBanca;
    }

    public CartesianChartModel getLinhaDoTempo() {
        return linhaDoTempo;
    }

    public void setLinhaDoTempo(CartesianChartModel linhaDoTempo) {
        this.linhaDoTempo = linhaDoTempo;
    }
}
