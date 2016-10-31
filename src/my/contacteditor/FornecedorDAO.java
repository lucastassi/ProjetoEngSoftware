/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.contacteditor;

import my.contacteditor.conexao.conexaoBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author  Lucas Tassi
 *          Lucas Diniz
 *          Lucas Barbosa
 *          Jhordan Garcia
 */
public class FornecedorDAO {
    
    private String cnpj;
    private String razao;
    private String inscEstadual;
    private String telefone1;
    private String telefone2;
    private String nome;
    private String rua;
    private String bairro;
    private String num;
    private String cep;
    private String cidade;
    private String estado;
    
    public FornecedorDAO(String cnpj, String inscEstadual, String FNome, String razao, String FRua, String FBairro, String FCep, String FEndNum,String FCidade,String FEstado,String FTel1, String FTel2) {
        this.cnpj = cnpj;
        this.telefone1 = FTel1;
        this.telefone2 = FTel2;
        this.nome = FNome;
        this.rua = FRua;
        this.bairro = FBairro;
        this.num = FEndNum;
        this.cep = FCep;
        this.inscEstadual = inscEstadual;
        this.razao = razao;
        this.estado = FEstado;
        this.cidade = FCidade;
    }
    

    public String getFEstado() {
        return estado;
    }

    public void setFEstado(String FEstado) {
        this.estado = FEstado;
    }
    
    public String getFCidade() {
        return cidade;
    }

    public void setFCidade(String FCidade) {
        this.cidade = FCidade;
    }
    
    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }
    
    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public FornecedorDAO(){
        /*Utilizado nos testes (Classe PigLatinTest)*/
    }
    
    
    
    /*Utilizado nos testes (retorna true/false para realizar as comparações)*/
    public boolean insereFornecedor() throws entradaInvalidaException, SQLException{
        validaEntrada ve = new validaEntrada();
        conexaoBD conexao = new conexaoBD();
         
        try{
            ve.valida_entrada(cnpj, inscEstadual, nome, razao, rua, bairro, cep, num, cidade, estado, telefone1,telefone2);
            conexao.insereDados(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
    }
    
    /*Utilizado nos testes (retorna true/false para realizar as comparações)*/
    public boolean atualizaFornecedor() throws entradaInvalidaException {
        validaEntrada ve = new validaEntrada();
        conexaoBD conexao = new conexaoBD();
        
        try{
            ve.valida_entrada(cnpj, inscEstadual, nome, razao, rua, bairro, cep, num, cidade, estado, telefone1,telefone2 );
            conexao.atualizarForn(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
       
    }

    
    
}
