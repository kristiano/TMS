/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

/**
 *
 * @author Glaubos
 */
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
public class TreeBean {

    private TreeNode root;
    @Inject
    private AreaAtuacaoController areaController;

    public TreeBean() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Node 0", root);
        TreeNode node1 = new DefaultTreeNode("Node 1", root);
        TreeNode node2 = new DefaultTreeNode("Node 2", root);

        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);

        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
        TreeNode node11 = new DefaultTreeNode("Node 1.1", node1);

        TreeNode node000 = new DefaultTreeNode("Node 0.0.0", node00);
        TreeNode node001 = new DefaultTreeNode("Node 0.0.1", node00);
        TreeNode node010 = new DefaultTreeNode("Node 0.1.0", node01);

        TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);
    }

    public void criarArvore() {
        //lista geral de todos os elementos
        List<TreeNode> listaFilhos = new ArrayList();
        //lista dos pais
        List<TreeNode> listaPai = new ArrayList();
        for (int i = 0; i < areaController.getList().size(); i++) {
            TreeNode novo = new DefaultTreeNode();
            if (areaController.getList().get(i).getPai() == null) {
                listaPai.add(novo);
            } else {
                listaFilhos.add(novo);
            }
        }

    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public AreaAtuacaoController getAreaController() {
        return areaController;
    }

    public void setAreaController(AreaAtuacaoController areaController) {
        this.areaController = areaController;
    }
}
