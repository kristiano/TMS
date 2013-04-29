package br.com.sigd.controller;

import br.com.sigd.model.AvaliacaoCapitulo;
import br.com.sigd.model.Capitulo;
import br.com.sigd.model.FeedBack;
import br.com.sigd.model.Projeto;
import br.com.sigd.model.ProjetoStatus;
import br.com.sigd.model.Proposta;
import br.com.sigd.model.PropostaStatus;
import br.com.sigd.repository.ProjetoRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
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
@Named("ProjetoController")
@ApplicationScoped
public class ProjetoController extends BasicBeanContent<Projeto, ProjetoRepository> implements Serializable {

    @Inject
    PropostaController propostaController;
    @Inject
    ProjetoStatusController statusController;
    @Inject
    private AvaliacaoCapituloController avaliacaoCapituloController;
    @Inject 
    private AcademicoController academicoController;
    private StreamedContent file;
    private Long idProjetoStatus;
    private String feedBack;
    @Inject
    private CapituloController capituloController;

    public ProjetoController() {
        this.entity = new Projeto();
        capituloController = new CapituloController();
    }

    public void mostrarCapitulos() {
        capituloController.setIdProjetoBusca(this.entity.getId());
        System.out.println("Misteeerreeeemme...");
    }

    @Override
    public void insert() {

        //conferindo se no banco de dados há os status
        if (statusController.getList().size() < 3) {
            statusController.setListOfEntities(new ArrayList<ProjetoStatus>());
            statusController.update();
            statusController.setEntity(new ProjetoStatus());
            statusController.getEntity().setNome("Pendente");
            statusController.insert();
            statusController.setEntity(new ProjetoStatus());
            statusController.getEntity().setNome("Aprovado");
            statusController.insert();
        }

        this.repository = new ProjetoRepository(ApplicationUtilies.catchEntityManager());
        for (Integer i = 1; i < 8; i++) {
            Capitulo capitulo = new Capitulo();
            capitulo.setNome(i.toString());
            capitulo.setStatus("Pendente");
            capitulo.setProjeto(this.entity);
            this.entity.getCapitulos().add(capitulo);
        }
        this.repository.insert(this.entity);
        
        //criando avaliacões       
        Projeto auxProjeto = this.repository.getByProposta(this.entity.getProposta().getId());
        for (int i = 0; i < auxProjeto.getCapitulos().size(); i++) {
            this.avaliacaoCapituloController.setEntity(new AvaliacaoCapitulo(auxProjeto.getCapitulos().get(i), this.entity.getProposta().getTurma(), this.entity.getProposta().getAcademico()));
            this.avaliacaoCapituloController.insert();
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Projeto inserido com sucesso: ", ""));
        this.entity = new Projeto();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new ProjetoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Projeto();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new ProjetoRepository(ApplicationUtilies.catchEntityManager());
        if (!this.feedBack.equals("")) {
            FeedBack feedback = new FeedBack(this.feedBack, getToday());
            this.entity.getFeedBack().add(feedback);
            this.feedBack = "";
        }
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Projeto atualizado com sucesso: ", ""));
        this.entity = new Projeto();
        this.listOfEntities.clear();
    }

    public void update2() {
        this.repository = new ProjetoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Projeto();
        this.listOfEntities.clear();
    }

    public List<Projeto> getList() {
        this.repository = new ProjetoRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    /**
     * Método getByCoordenador Retorna uma lista com os projetos de um
     * coordenador.
     *
     * @return projetosCoordenador Lista de projetos
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Projeto> getByCoordenador() {
        List<Projeto> projetosCoordenador = new ArrayList<Projeto>();

        if (propostaController == null) {
            System.out.println("nulo");
        }
        //pega todas as propostas já aprovadas
        List<Proposta> propostasProfessor = propostaController.getAprovadasCoordenador();

        //enquanto houverem propostas, adicionar o projeto a lista
        while (!propostasProfessor.isEmpty()) {
            this.repository = new ProjetoRepository(ApplicationUtilies.catchEntityManager());
            Projeto aux = this.repository.getByProposta(propostasProfessor.get(0).getId());
            projetosCoordenador.add(aux);
            propostasProfessor.remove(0);
        }
        return projetosCoordenador;
    }

    public List<Projeto> getByProfessor() {
        System.out.println("projetos de professor");
        List<Projeto> projetos = new ArrayList<Projeto>();
        //pega todas as propostas já aprovadas
        List<Proposta> propostasAprovadas = propostaController.getAprovadasProfessor();
        while (!propostasAprovadas.isEmpty()) {
            this.repository = new ProjetoRepository(ApplicationUtilies.catchEntityManager());
            Projeto aux = this.repository.getByProposta(propostasAprovadas.get(0).getId());
            projetos.add(aux);
            propostasAprovadas.remove(0);
        }
        return projetos;
    }

    public List<Projeto> getByAcademico() {
        List<Projeto> projetos = new ArrayList<Projeto>();
        //pega todas as propostas já aprovadas         
        List<Proposta> propostasAprovadas = propostaController.getAprovadasAcademico();
        while (!propostasAprovadas.isEmpty()) {
            this.repository = new ProjetoRepository(ApplicationUtilies.catchEntityManager());
            Projeto aux = this.repository.getByProposta(propostasAprovadas.get(0).getId());
            projetos.add(aux);
            propostasAprovadas.remove(0);
        }
        return projetos;
    }

    // função de upar arquivo
    public void upFile(FileUploadEvent event) throws FileNotFoundException, IOException {


        try {
            this.entity.setUrlArquivo("C:\\sigd\\Projetos\\" + this.entity.getProposta().getTurma().getAno() + "\\"
                    + this.entity.getProposta().getTurma().getSemestre() + "\\Projeto " + this.entity.getProposta().getTurma().getCodigoTurma() + "\\"
                    + this.entity.getProposta().getAcademico().getNome() + "\\");
        } catch (Exception e) {
            try {
                this.entity.setUrlArquivo("\\home\\sigd\\Projetos\\Ano " + this.entity.getProposta().getTurma().getAno() + "\\"
                        + this.entity.getProposta().getTurma().getSemestre() + "º Semestre\\Projeto " + this.entity.getProposta().getTurma().getCodigoTurma() + "\\"
                        + this.entity.getProposta().getAcademico().getNome() + "\\");

            } catch (Exception a) {
                this.entity.setUrlArquivo("\\home\\");
            }
        }

        //seta o nome do arquivo
        // this.entity.setNomeArquivo(event.getFile().getFileName());
        int contTmp = entity.getCont();
        this.entity.setCont(++contTmp);
        this.entity.setNomeArquivo("projeto_" + this.entity.getProposta().getAcademico().getMatricula() + "_v" + this.entity.getCont() + ".pdf");

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

        //faz o update
        update2();

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(facesMessage.SEVERITY_INFO);
        facesMessage.setDetail("Projeto salvo com sucesso");
        context.addMessage("Upload", facesMessage);


    }

    // retorna o arquivo
    public StreamedContent getFile() throws FileNotFoundException {
        System.out.println("ARQUIVO:" + this.entity.getUrlArquivo() + this.entity.getNomeArquivo());
        InputStream stream = new FileInputStream(this.entity.getUrlArquivo() + this.entity.getNomeArquivo());
        this.file = new DefaultStreamedContent(stream, "application/pdf", this.entity.getNomeArquivo());
        return file;
    }

    public Projeto getById(Long id) {
        this.repository = new ProjetoRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getById(id);
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public int getQuantProjetos() {
        return getByCoordenador().size();
    }

    public Long getIdProjetoStatus() {
        return idProjetoStatus;
    }

    public void setIdProjetoStatus(Long idProjetoStatus) {
        this.idProjetoStatus = idProjetoStatus;
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

    public CapituloController getCapituloController() {
        return capituloController;
    }

    public void setCapituloController(CapituloController capituloController) {
        this.capituloController = capituloController;
    }
}
