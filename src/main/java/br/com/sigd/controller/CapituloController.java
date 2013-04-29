package br.com.sigd.controller;

import br.com.sigd.model.Capitulo;
import br.com.sigd.model.FeedBack;
import br.com.sigd.repository.CapituloRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.*;
import java.sql.Date;
import java.util.List;
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
@Named("CapituloController")
@SessionScoped
public class CapituloController extends BasicBeanContent<Capitulo, CapituloRepository> implements Serializable {

    @Inject
    ProjetoController projetoController;
   private StreamedContent file;
    //id do projeto para que a busca dos capitulos seja feita   
    private Long idProjetoBusca;
    private String feedBack;

    public CapituloController() {
        this.entity = new Capitulo();
    }

    @Override
    public void insert() {
        this.repository = new CapituloRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", entity.getNome()));
        this.entity = new Capitulo();
    }

    @Override
    public void remove() {
        this.repository = new CapituloRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Capitulo();
    }

    @Override
    public void update() {
        this.repository = new CapituloRepository(ApplicationUtilies.catchEntityManager());
        if (!this.feedBack.equals("")) {
            FeedBack feedback = new FeedBack(this.feedBack, getToday());
            this.entity.getFeedBack().add(feedback);
            this.feedBack = "";
        }
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", entity.getNome()));
        this.entity = new Capitulo();
    }

    public void update2() {
        this.repository = new CapituloRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Capitulo();
    }

    public List<Capitulo> getByProjeto() {
        this.repository = new CapituloRepository(ApplicationUtilies.catchEntityManager());        
        List<Capitulo> byProjeto = this.repository.getByProjeto(this.idProjetoBusca);
        for(int i=0;i<byProjeto.size();i++){
            System.out.println(byProjeto.get(i).getNome());
        }
                return byProjeto;
    }

    // função de upar arquivo
    public void upFile(FileUploadEvent event) throws FileNotFoundException, IOException {


        try {
            this.entity.setUrlArquivo("C:\\sigd\\Capitulos\\" + this.entity.getProjeto().getProposta().getTurma().getAno() + "\\"
                    + this.entity.getProjeto().getProposta().getTurma().getSemestre() + "\\Turma " + this.entity.getProjeto().getProposta().getTurma().getCodigoTurma() + "\\"
                    + this.entity.getProjeto().getProposta().getAcademico().getNome() + "\\");
        } catch (Exception e) {
            try {
                this.entity.setUrlArquivo("\\home\\sigd\\Capitulos\\Ano" + this.entity.getProjeto().getProposta().getTurma().getAno() + "\\"
                        + this.entity.getProjeto().getProposta().getTurma().getSemestre() + "º Semestre\\Turma " + this.entity.getProjeto().getProposta().getTurma().getCodigoTurma() + "\\"
                        + this.entity.getProjeto().getProposta().getAcademico().getNome() + "\\");

            } catch (Exception a) {
                this.entity.setUrlArquivo("\\home\\");
            }
        }

        //seta o nome do arquivo
        // this.entity.setNomeArquivo(event.getFile().getFileName());
        int contTmp = entity.getCont();
        this.entity.setCont(++contTmp);
        this.entity.setNomeArquivo("capitulo." + this.entity.getProjeto().getProposta().getAcademico().getMatricula() +"."+this.entity.getNome()+".v" + this.entity.getCont() + ".pdf");
        
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

        update2();

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(facesMessage.SEVERITY_INFO);
        facesMessage.setDetail("Phase: success");
        context.addMessage("Upload", facesMessage);

    }

    public List<Capitulo> getList() {
        this.repository = new CapituloRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    // retorna o arquivo
    public StreamedContent getFile() throws FileNotFoundException {
        System.out.println("File:" + this.entity.getUrlArquivo() + this.entity.getNomeArquivo());
        InputStream stream = new FileInputStream(this.entity.getUrlArquivo() + this.entity.getNomeArquivo());
        this.file = new DefaultStreamedContent(stream, "application/pdf", this.entity.getNomeArquivo());
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public Long getIdProjetoBusca() {
        return idProjetoBusca;
    }

    public void setIdProjetoBusca(Long idProjetoBusca) {
        this.idProjetoBusca = idProjetoBusca;
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
}
