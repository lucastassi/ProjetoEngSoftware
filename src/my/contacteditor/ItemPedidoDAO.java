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
 * @author  Lucas Tassi
 *          Lucas Diniz
 *          Lucas Barbosa
 *          Jhordan Garcia
 */
public class ItemPedidoDAO {
    private int pedCod;
    private int proCod;
    private int quantidade;
    
    public ItemPedidoDAO(int pedCod, int proCod, int quantidade) {
        this.pedCod = pedCod;
        this.proCod = proCod;
        this.quantidade = quantidade;
    }
    
    public int getPedCod() {
        return pedCod;
    }

    public void setPedCod(int pedCod) {
        this.pedCod = pedCod;
    }

    public int getProCod() {
        return proCod;
    }

    public void setProCod(int proCod) {
        this.proCod = proCod;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    /*Utilizado nos testes (retorna true/false para realizar as comparações)*/
    public boolean insereItemPedido() throws entradaInvalidaException, SQLException{
        validaEntrada ve = new validaEntrada();
        conexaoBD conexao = new conexaoBD();
         
        try{
            ve.valida_entrada_itemPedido(quantidade);
            conexao.insereDadosItemPedido(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
    }
    
    /*Utilizado nos testes (retorna true/false para realizar as comparações)*//*
    public boolean atualizaItemPedido() throws entradaInvalidaException {
        validaEntrada ve = new validaEntrada();
        conexaoBD conexao = new conexaoBD();
        
        try{
            ve.valida_entrada_itemPedido(quantidade);
            conexao.atualizaDadosItemPedido(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
       
    }*/
    
}
