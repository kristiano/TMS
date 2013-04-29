/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import Enum.EstadoAvaliacao;
import br.com.sigd.model.Academico;
import br.com.sigd.model.AvaliacaoCapitulo;
import br.com.sigd.model.Capitulo;
import br.com.sigd.model.Grade;
import br.com.sigd.model.Turma;
import br.com.sigd.repository.AvaliacaoCapituloRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.GenericController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Klimaco
 */
@Named("AvaliacaoCapituloController")
@SessionScoped
public class AvaliacaoCapituloController extends GenericController<AvaliacaoCapitulo, AvaliacaoCapituloRepository> implements Serializable {

    private Turma auxTurma;
    private AvaliacaoCapitulo auxAvaliacaoCapitulo;
    private Capitulo auxCapitulo;
    private Double auxMedia;
    private Double auxVariancia;
    @Inject
    private TurmaController turmaController;
    private CartesianChartModel linhaDoTempo;

    public AvaliacaoCapituloController() {
        this.entity = new AvaliacaoCapitulo();
        this.auxTurma = new Turma();
        this.auxAvaliacaoCapitulo = new AvaliacaoCapitulo();
        this.auxCapitulo = new Capitulo();
    }

    public Capitulo getAuxCapitulo() {
        return auxCapitulo;
    }

    public void setAuxCapitulo(Capitulo auxCapitulo) {
        this.auxCapitulo = auxCapitulo;
    }

    public Turma getAuxTurma() {
        return auxTurma;
    }

    public void setAuxTurma(Turma auxTurma) {
        this.auxTurma = auxTurma;
    }

    public AvaliacaoCapitulo getAuxAvaliacaoCapitulo() {
        return auxAvaliacaoCapitulo;
    }

    public void setAuxAvaliacaoCapitulo(AvaliacaoCapitulo auxAvaliacaoCapitulo) {
        this.auxAvaliacaoCapitulo = auxAvaliacaoCapitulo;
    }

    public Double getAuxMedia() {
        return auxMedia;
    }

    public void setAuxMedia(Double auxMedia) {
        this.auxMedia = auxMedia;
    }

    public Double getAuxVariancia() {
        return auxVariancia;
    }

    public void setAuxVariancia(Double auxVariancia) {
        this.auxVariancia = auxVariancia;
    }

    public CartesianChartModel getLinhaDoTempo() {
        return linhaDoTempo;
    }

    public void setLinhaDoTempo(CartesianChartModel linhaDoTempo) {
        this.linhaDoTempo = linhaDoTempo;
    }

    @Override
    public void insert() {
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new AvaliacaoCapitulo();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new AvaliacaoCapitulo();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        this.entity.setEstadoAvaliacao(EstadoAvaliacao.NAO);
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Avaliado com sucesso ", ""));
        this.entity = new AvaliacaoCapitulo();
        this.listOfEntities.clear();
    }

    public List<AvaliacaoCapitulo> getList() {
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    public void carregarLiberadoPorCapitulo() {
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        this.entity = this.repository.getLiberadoPorCapitulo(this.auxCapitulo.getId());
    }

    public Double carregarNotaAvaliadoPorCapitulo(Long idCapitulo) {
        System.out.println("Cap: " + idCapitulo);
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        this.auxAvaliacaoCapitulo = this.repository.getPorCapitulo(idCapitulo);
        if (this.auxAvaliacaoCapitulo.getNota() == null) {
            return 0.0;
        }
        return this.auxAvaliacaoCapitulo.getNota();
    }

    public List<AvaliacaoCapitulo> carregarAvaliadosByAcademico(Long idAcademico) {
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getAvaliadosByAcademico(idAcademico);
        return listOfEntities;
    }

    public List<AvaliacaoCapitulo> carregarAvaliadosByTurma(Long idTurma) {
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getAvaliadosByTurma(idTurma);
        return listOfEntities;
    }

    public void calcularMediaEVariancia(Long idTurma) {
        List<AvaliacaoCapitulo> aux = carregarAvaliadosByTurma(idTurma);
        Double somatorio = 0.0;
        //calculo da média
        for (int i = 0; i < aux.size(); i++) {
            somatorio += aux.get(i).getNota();
        }
        this.auxMedia = somatorio / aux.size();

        //cálculo da variância
        somatorio = 0.0;
        for (int i = 0; i < aux.size(); i++) {
            somatorio += Math.pow(this.auxMedia - aux.get(i).getNota(), 2);
        }

        this.auxVariancia = somatorio / aux.size();
    }

    public List<AvaliacaoCapitulo> carregarDisponivelAcademico(Long idAcademico) {
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getByDisponivelAcademico(idAcademico);
        return listOfEntities;
    }

    public void carregarAvaliaçãoPorId() {
        this.repository = new AvaliacaoCapituloRepository(ApplicationUtilies.catchEntityManager());
        this.entity = this.repository.getById(this.auxAvaliacaoCapitulo.getId());
    }

    public void criarLinhaDoTempo(Long idTurma) {
        System.out.println("entrou");
        this.linhaDoTempo = new CartesianChartModel();
        //cria lista de series
        //List<LineChartSeries> auxiliarSeries = new ArrayList<LineChartSeries>();
        //carrega todos os academicos da turma
        List<Academico> auxAcademicos = this.turmaController.carregarAcadPorTurma(idTurma);
        if (auxAcademicos == null) {
            System.out.println("null");
        }
        System.out.println(auxAcademicos.size());
        System.out.println(this.auxTurma.getId());

        for (int i = 0; i < auxAcademicos.size(); i++) {
            //carrega todas as avaliacoes liberadas
            List<AvaliacaoCapitulo> auxAvaliacaoCapitulos = carregarAvaliadosByAcademico(auxAcademicos.get(i).getId());
            System.out.println(auxAvaliacaoCapitulos.size());

            //seta o label
             LineChartSeries auxSerie = new LineChartSeries();  
             
            auxSerie.setLabel(auxAcademicos.get(i).getNome());

            //auxiliarSeries.get(i).setLabel(auxAcademicos.get(i).getNome());
            //seta numero da série e a nota
            for (int j = 0; j < auxAvaliacaoCapitulos.size(); j++) {
                auxSerie.set(auxAvaliacaoCapitulos.get(j).getCapitulo().getNome(), auxAvaliacaoCapitulos.get(j).getNota());
            }
            //add a serie 
            this.linhaDoTempo.addSeries(auxSerie);

        }
        //media
        LineChartSeries auxSerie = new LineChartSeries();  
        auxSerie.setLabel("Media");
        auxSerie.setMarkerStyle("diamond");
        
        
        for (int i = 1; i < 8; i++) {
            //carrega todos os cap (1,2.. 7) de uma turma
            List<AvaliacaoCapitulo> auxAvaliacaoCapitulos = this.repository.getAvaliadosPorNomeCapituloETurma(String.valueOf(i), idTurma);
            Double auxSomatorio = 0.0;
            //somatório por capitulo
            for (int j = 0; j < auxAvaliacaoCapitulos.size(); j++) {
                auxSomatorio+=auxAvaliacaoCapitulos.get(j).getNota();
            }
            auxSerie.set(i, auxSomatorio/auxAvaliacaoCapitulos.size());
        }
        
        this.linhaDoTempo.addSeries(auxSerie);
        
       // System.out.println(auxiliarSeries.size());
        if (this.linhaDoTempo == null) {
            System.out.println("null");
        }
    }
}
