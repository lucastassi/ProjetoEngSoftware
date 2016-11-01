/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.contacteditor;

import java.sql.SQLException;
import my.contacteditor.conexao.conexaoBD;

/**
 *
 * @author lucas
 */
public class ProdutoDAO {
    
    private int codigo;
    private String nome;
    private String descricao;
    private String descricaoReduzida;
    private String codBarras;
    private String localEstoque;
    private String fabricante;
    private String categoria;
    private String dataFab;
    private String dataVal;
    private String unCompra;
    private int estoqueInicial;
    private float custo;
    private float tributos;
    private String observacoes;
    private String outros;
    
    public ProdutoDAO(int PCodigo, String PNome, String PDesc, String PDescRed, String PCodBarras, String PLocalEstoque,
            String PFabricante, String PCat, String PDataFab, String PDataVal, String PUnCompra, int PEstoqueInicial,
            float PCusto, float PTributos, String PObservacoes, String POutros){
    
        this.codigo = PCodigo;
        this.nome = PNome;
        this.categoria = PCat;
        this.codBarras = PCodBarras;
        this.custo = PCusto;
        this.dataFab = PDataFab;
        this.dataVal = PDataVal;
        this.descricao = PDesc;
        this.descricaoReduzida = PDescRed;
        this.estoqueInicial = PEstoqueInicial;
        this.fabricante = PFabricante;
        this.localEstoque = PLocalEstoque;
        this.observacoes = PObservacoes;
        this.outros = POutros;
        this.tributos = PTributos;
        this.unCompra = PUnCompra;
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoReduzida() {
        return descricaoReduzida;
    }

    public void setDescricaoReduzida(String descricaoReduzida) {
        this.descricaoReduzida = descricaoReduzida;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getLocalEstoque() {
        return localEstoque;
    }

    public void setLocalEstoque(String localEstoque) {
        this.localEstoque = localEstoque;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDataFab() {
        return dataFab;
    }

    public void setDataFab(String dataFab) {
        this.dataFab = dataFab;
    }

    public String getDataVal() {
        return dataVal;
    }

    public void setDataVal(String dataVal) {
        this.dataVal = dataVal;
    }

    public String getUnCompra() {
        return unCompra;
    }

    public void setUnCompra(String unCompra) {
        this.unCompra = unCompra;
    }

    public int getEstoqueInicial() {
        return estoqueInicial;
    }

    public void setEstoqueInicial(int estoqueInicial) {
        this.estoqueInicial = estoqueInicial;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public float getTributos() {
        return tributos;
    }

    public void setTributos(float tributos) {
        this.tributos = tributos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
    }
    
    public ProdutoDAO(){}
    
    
    
        public boolean insereProduto() throws entradaInvalidaException, SQLException{
        validaEntrada va = new validaEntrada();
        conexaoBD conexao = new conexaoBD();
         
        try{
            va.valida_entrada_produto(codigo, nome, descricao, descricaoReduzida, estoqueInicial,
                    custo,tributos, dataFab, dataVal);
            conexao.inserirDadosProduto(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
    }
        
    public boolean atualizaProduto() throws entradaInvalidaException, SQLException {
        conexaoBD conexao = new conexaoBD();
        validaEntrada va = new validaEntrada();
        
        try{
            va.valida_entrada_produto(codigo, nome, descricao, descricaoReduzida, estoqueInicial, custo,tributos, dataFab, dataVal);
            
            conexao.atualizarProduto(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
       
    }
}
