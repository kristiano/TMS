package br.com.sigd.controller;

import br.com.sigd.model.FeedBack;
import br.com.sigd.model.Proposta;
import br.com.sigd.model.PropostaStatus;
import br.com.sigd.model.Turma;
import br.com.sigd.repository.PropostaRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.GenericConversationController;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author FernandoChagas
 */
@Named("PropostaController")
@ConversationScoped
@URLMappings(mappings = {
    @URLMapping(id = "proposta-academico", parentId = "academico", pattern = "/propostas", viewId = "/usuarios/academico/_propostas.xhtml"),
    @URLMapping(id = "minhas-propostas-coordenador", parentId = "coordenador", pattern = "/minhas-propostas", viewId = "/usuarios/coordenador/_minhaspropostas.xhtml"),
    @URLMapping(id = "propostas-coordenador", parentId = "coordenador", pattern = "/propostas", viewId = "/usuarios/coordenador/_propostas.xhtml"),
    @URLMapping(id = "propostas-professor", parentId = "professor", pattern = "/propostas-professor", viewId = "/usuarios/professor/_propostas.xhtml")
})
public class PropostaController extends GenericConversationController<Proposta, PropostaRepository> implements Serializable {

    @Inject
    ProjetoController projetoController;
    @Inject
    UsuarioController usuario;
    @Inject
    ProfessorController professor;
    @Inject
    TurmaController turmaController;
    @Inject
    AcademicoController academico;
    @Inject
    PropostaStatusController statusController;
    @Inject
    ProjetoStatusController projetoStatusController;
    private StreamedContent file;
    private String feedBack;
    private Long idTurma;

    public PropostaController() {
        this.entity = new Proposta();
        this.feedBack = "";
    }

    @Override
    public void insert() {      

        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        //inserindo proposta como valor padrão 1
        this.entity.setPropostaStatus(statusController.getById(Long.parseLong("1")));
        this.entity.setAcademico(academico.getLoggedAcademico());
        this.entity.setTurma(turmaController.getById(this.idTurma));
        //atualizar quantidade de orientandos
        this.repository.insert(this.entity);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proposta: " + this.entity.getTitulo() + " criada com sucesso", this.entity.getTitulo()));
        System.out.println("tamanho da lista de status com dois status: " + statusController.getList().size());
        this.endConversation();
    }

    @Override
    public void remove() {
        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.endConversation();
    }

    @Override
    public void update() {
        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        System.out.println(this.entity.getTitulo());

        //caso aprove a proposta cria o projeto
        if (this.entity.getPropostaStatus().getNome().toLowerCase().equals("aprovada")) {
            projetoController.getEntity().setProposta(this.entity);
            projetoController.getEntity().setProjetoStatus(projetoStatusController.getById(Long.parseLong("1")));
            projetoController.insert();
        }
        if (!this.feedBack.equals("")) {
            FeedBack feedback = new FeedBack(this.feedBack, getToday());
            this.entity.getFeedBack().add(feedback);
            this.feedBack = "";
        }
        this.repository.update(this.entity);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.entity.getTitulo(), " Atualizada com sucesso"));

        this.entity = new Proposta();
        this.endConversation();
    }

    public void update2() {
        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        this.entity.setTurma(turmaController.getById(this.idTurma));
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.entity.getTitulo(), " Atualizada com sucesso"));
        this.endConversation();
    }

    public List<Proposta> getByProfessor() {
        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getByProfessor(professor.getProfessorLogado().getId());
    }

    public List<Proposta> getByAcademico() {
        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        List<Proposta> propostasAcademico = this.repository.getPropostaAcademico(usuario.getLoggedUsuario().getId());
        return propostasAcademico;
    }

    /**
     * Método getByTurma Retorna uma lista com as propostas aprovadas de uma
     * determinada turma.
     *
     * @param id id do coordenador
     * @return Lista de turmas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getAprovadasTurma(long id) {
        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getAprovadaTurma(id);
    }

    /**
     * Método getPendenteTurma Retorna uma lista com as propostas pendentes de
     * uma determinadas turmas.
     *
     * @param id do coordenador
     * @return Lista de turmas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getPendenteTurma(long id) {
        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getPendenteTurma(id);
    }

    /**
     * Método getPendentesCoordenador Retorna uma lista com as propostas
     * pendentes de um coordenador.
     *
     *
     * @param id id do coordenador
     * @return Lista de turmas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getPendentesCoordenador() {
        LinkedList<Proposta> propostasPendentes = new LinkedList<Proposta>();
        //busca todas as turmas do coordenador logado
        List<Turma> turmasCoordenador = turmaController.getByCoordenador();
        //enquanto a lista não estiver vazia
        while (!turmasCoordenador.isEmpty()) {
            //adiciona as listas de propostas pendentes de cada turma            
            List<Proposta> propostas = getPendenteTurma(turmasCoordenador.get(0).getId());
            propostasPendentes.addAll(propostas);
            turmasCoordenador.remove(0);
        }
        return propostasPendentes;
    }

    /**
     * Método getAprovadasCoordenador Retorna uma lista com as propostas
     * aprovadas de um coordenador.
     *
     *
     * @param id id do coordenador
     * @return Lista de turmas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getAprovadasCoordenador() {
        LinkedList<Proposta> propostasAprovadas = new LinkedList<Proposta>();
        //busca todas as turmas do coordenador logado
        List<Turma> turmasCoordenador = turmaController.getByCoordenador();
        //enquanto a lista não estiver vazia
        while (!turmasCoordenador.isEmpty()) {
            //adiciona as listas de propostas pendentes de cada turma            
            List<Proposta> propostas = getAprovadasTurma(turmasCoordenador.get(0).getId());
            propostasAprovadas.addAll(propostas);
            turmasCoordenador.remove(0);
        }
        return propostasAprovadas;
    }

    /**
     * Método getAprovadasProfessor Retorna uma lista com as propostas aprovadas
     * de um professor.
     *
     *
     * @param id id do professor
     * @return Lista de propostas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getAprovadasProfessor() {
        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getAprovadasProfessor(professor.getProfessorLogado().getId());
    }

    /**
     * Método getAprovadasAcademico Retorna uma lista com as propostas aprovadas
     * de um acad"emico.
     *
     *
     * @param id do acadêmico
     * @return Lista de propostas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getAprovadasAcademico() {
        this.repository = new PropostaRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getAprovadasAcademico(academico.getLoggedAcademico().getId());
    }

    // função de upar arquivo
    public void upFile(FileUploadEvent event) throws FileNotFoundException, IOException {


        boolean contains = event.getFile().getContentType().toLowerCase().contains("pdf");

        if (!contains) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Formato de arquivo inválido, deve ser .pdf", ""));
            return;
        }

        try {
            this.entity.setUrlArquivo("C:\\sigd\\Propostas\\" + this.entity.getTurma().getAno() + "\\"
                    + this.entity.getTurma().getSemestre() + "\\Turma " + this.entity.getTurma().getCodigoTurma() + "\\"
                    + this.entity.getAcademico().getNome() + "\\");
        } catch (Exception e) {
            try {
                this.entity.setUrlArquivo("\\home\\sigd\\Propostas\\Ano " + this.entity.getTurma().getAno() + "\\"
                        + this.entity.getTurma().getSemestre() + "º Semestre\\Turma " + this.entity.getTurma().getCodigoTurma() + "\\"
                        + this.entity.getAcademico().getNome() + "\\");
                System.err.println("Algum dado está null ou invalido. Erro: "
                        + e.getMessage());
            } catch (Exception a) {
                this.entity.setUrlArquivo("\\home\\");
            }
        }

        //seta o nome do arquivo
        // this.entity.setNomeArquivo(event.getFile().getFileName());
        int contTmp = entity.getCont();
        this.entity.setCont(++contTmp);
        this.entity.setNomeArquivo("proposta." + this.entity.getAcademico().getMatricula() + "." + this.entity.getTitulo() + "_v" + this.entity.getCont() + ".pdf");

        System.out.println("Nome do arquivo: " + event.getFile().getFileName());


        File diretorio = new File(this.entity.getUrlArquivo());
        System.out.println("Url do arquivo: " + this.entity.getUrlArquivo());

        // se o diretorio nao existe, entao cria-se
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        FileOutputStream outPutStream = new FileOutputStream(this.entity.getUrlArquivo() + this.entity.getNomeArquivo());
        outPutStream.write(event.getFile().getContents());

        outPutStream.flush();
        outPutStream.close();

        System.out.println("arquivo enviado");

        //atualiza especificamente a stream da proposta
        update2();

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(facesMessage.SEVERITY_INFO);
        facesMessage.setDetail("Arquivo salvo com sucesso!");
        context.addMessage("Upload", facesMessage);


    }

    // retorna o arquivo
    public StreamedContent getFile() throws FileNotFoundException {

        System.out.println("Arquivo:" + this.entity.getUrlArquivo() + this.entity.getNomeArquivo() + " baixado.");

        try {
            InputStream stream = new FileInputStream(this.entity.getUrlArquivo() + this.entity.getNomeArquivo());
            this.file = new DefaultStreamedContent(stream, "application/pdf", this.entity.getNomeArquivo());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public Date getToday() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    @URLAction(mappingId = "proposta-academico", inheritable = true)
    public String validatePropostaAcademico() {
        this.beginConversation();
        return "validaded";
    }

    @URLAction(mappingId = "minhas-propostas-coordenador", inheritable = true)
    public String validateMinhasPropostasCoordenador() {
        this.beginConversation();
        return "validaded";
    }

    @URLAction(mappingId = "propostas-coordenador", inheritable = true)
    public String validatePropostasCoordenador() {
        this.beginConversation();
        return "validaded";
    }

    @URLAction(mappingId = "propostas-professor", inheritable = true)
    public String validatePropostasProfessor() {
        this.beginConversation();
        return "validaded";
    }
}
