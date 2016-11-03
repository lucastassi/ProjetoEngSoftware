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
public class PedidoDAO {
    
    private int codigo;
    private float valor;
    private String dataEntrega;
    private boolean pago;
    
    public PedidoDAO(float valor, String dataEntrega, boolean pago){
        this.valor=valor;
        this.dataEntrega=dataEntrega;
        this.pago=pago;
    }

    public PedidoDAO() {
        
    }
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
    
     /*Utilizado nos testes (retorna true/false para realizar as comparações)*/
    public boolean inserePedido() throws entradaInvalidaException, SQLException{
        validaEntrada ve = new validaEntrada();
        conexaoBD conexao = new conexaoBD();
         
        try{
            ve.valida_entrada_pedido(valor,dataEntrega,pago);
            conexao.insereDadosPedido(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
    }
    
    /*Utilizado nos testes (retorna true/false para realizar as comparações)*/
    public boolean atualizaPedido() throws entradaInvalidaException {
        validaEntrada ve = new validaEntrada();
        conexaoBD conexao = new conexaoBD();
        
        try{
            ve.valida_entrada_pedido(valor,dataEntrega,pago);
            conexao.atualizarPedido(this);
            return true;
        }
        catch(Exception ex){
            throw new entradaInvalidaException(""+ex);
        }
       
    }
    
    
}
