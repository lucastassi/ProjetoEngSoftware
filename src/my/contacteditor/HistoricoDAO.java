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
 * @author Lucas A
 */
public class HistoricoDAO {
    
    private int hcodigo;
    private String Data;
    private int Quantidade;
    private int PCod;
    private String FCod;
    private String PNome;
    private String FNome;
    
    public HistoricoDAO(int codigocompra, String datacompra, int quantidade, int PCod, String CNPJFornecedor) {
        
        this.hcodigo = codigocompra;
        this.Data = datacompra;
        this.Quantidade = quantidade;
        this.PCod = PCod;
        this.FCod = CNPJFornecedor;
    }

    public HistoricoDAO() {
    
    }



    /**
     * @return the hcodigo
     */
    public int getHcodigo() {
        return hcodigo;
    }

    /**
     * @param hcodigo the hcodigo to set
     */
    public void setHcodigo(int hcodigo) {
        this.hcodigo = hcodigo;
    }

    /**
     * @return the Data
     */
    public String getData() {
        return Data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData(String Data) {
        this.Data = Data;
    }

    /**
     * @return the Quantidade
     */
    public int getQuantidade() {
        return Quantidade;
    }

    /**
     * @param Quantidade the Quantidade to set
     */
    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    /**
     * @return the PCod
     */
    public int getPCod() {
        return PCod;
    }

    /**
     * @param PCod the PCod to set
     */
    public void setPCod(int PCod) {
        this.PCod = PCod;
    }

    public boolean insereHistorico() throws entradaInvalidaException, SQLException{
        validaEntrada va = new validaEntrada();
        conexaoBD conexao = new conexaoBD();
         
        try{
            va.valida_entrada_historico(hcodigo, Data, Quantidade, PCod, FCod);
            conexao.inserirDadosHistorico(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
    }
    
    public boolean insereHistorico2() throws entradaInvalidaException, SQLException{
        validaEntrada va = new validaEntrada();
        conexaoBD conexao = new conexaoBD();
         
        try{
            va.valida_entrada_historico(hcodigo, Data, Quantidade, PCod, FCod);
            conexao.inserirDadosHistorico2(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
    }

    /**
     * @return the FCod
     */
    public String getFCod() {
        return FCod;
    }

    /**
     * @param FCod the FCod to set
     */
    public void setFCod(String FCod) {
        this.FCod = FCod;
    }

    /**
     * @return the PNome
     */
    public String getPNome() {
        return PNome;
    }

    /**
     * @return the FNome
     */
    public String getFNome() {
        return FNome;
    }

    /**
     * @param PNome the PNome to set
     */
    public void setPNome(String PNome) {
        this.PNome = PNome;
    }

    /**
     * @param FNome the FNome to set
     */
    public void setFNome(String FNome) {
        this.FNome = FNome;
    }
    
    

}
