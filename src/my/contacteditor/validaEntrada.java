/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.contacteditor;

/**
 *
 * @author lucas
 */
public class validaEntrada {
    
    public void valida_entrada(String cnpj, String inscEstadual, String FNome, String razao, String FRua, String FBairro, String FCep, String FEndNum,String FCidade,String FEstado,String FTel1, String FTel2) throws entradaInvalidaException{
        validaCNPJ(cnpj);
        validaInscEst(inscEstadual);
        validaNomeFantasia(FNome);
        validaRazaoSocial(razao);
        validaRua(FRua);
        validaBairro(FBairro);
        validaCep(FCep);
        validaNumRua(FEndNum);
        validaCidade(FCidade);
        validaTelefone(FTel1);
        validaTelefone(FTel2);
        
    }
    
    public void valida_entrada_produto(int codigo, String codBarras, String nome, String descricao, String DescricaoReduzida, String fabricante, String outros, String categoria, String localEstoque, String UnVenda, int estoqueInicial, float custo, float tributos, 
                                        String dataFab, String dataVal, String obs)throws entradaInvalidaException{
    
        validaCodigo(codigo);
        validaNome(nome);
        validaNome(descricao);
        validaNome(DescricaoReduzida);
        validaEstoque(estoqueInicial);
        validaCustoETributos(custo);
        validaCustoETributos(tributos);
        validaDatas(dataFab);
        validaDatas(dataVal);
        
    }
   
    public boolean validaCodigo(int codigo) throws entradaInvalidaException{
        
        if(Integer.toString(codigo).matches("[0-9]{1,}"))
            return true;
        else{
            throw new entradaInvalidaException("Código inválido!\n - Somente números");
        }
    }
    
    public boolean validaNome(String nome)throws entradaInvalidaException{
                
        if(nome.matches("[a-zA-z0-9áéíóú\\-\\.\\/]{2,}(\\s[a-zA-z0-9áéíóú\\-\\.\\/]{2,})*")){
            return true;
        }else{
            throw new entradaInvalidaException("Nome inválido!");
        }
    }
    
    public boolean validaEstoque(int estoque)throws entradaInvalidaException{
                
        if(Integer.toString(estoque).matches("[0-9]{1,}")){
            return true;
        }else{
            throw new entradaInvalidaException("Estoque inicial inválido! \n - Somente números inteiros");
        }
    }
    
     public boolean validaCustoETributos(float estoque)throws entradaInvalidaException{
                
        if(Float.toString(estoque).matches("[0-9]{1,}\\.[0-9]{1,}")){
            return true;
        }else{
            throw new entradaInvalidaException("Custo inválido! \n - Digite números sem pontos (exceto vírgula)");
        }
    }
     
     public boolean validaDatas(String data)throws entradaInvalidaException{
                
        if(data.matches("[0,9]{2}\\[0,9]{2}\\[0,9]{4}")){
            return true;
        }else{
            throw new entradaInvalidaException("Data inválida!");
        }
    } 
    
    
  
    public boolean validaCNPJ(String cnpj) throws entradaInvalidaException{
    
        if(cnpj.matches("[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")){
            return true;
        }else{
            throw new entradaInvalidaException("CNPJ Inválido!");
        }
    }
    
    
    public boolean validaNomeFantasia(String nf) throws entradaInvalidaException{
        
        if(nf.matches("[a-zA-z0-9áéíóú\\-\\.\\/]{2,}(\\s[a-zA-z0-9áéíóú\\-\\.\\/]{2,})*")){
            return true;
        }else{
            throw new entradaInvalidaException("Nome Fantasia inválido!");
        }
    }
    
    public boolean validaRazaoSocial(String rs) throws entradaInvalidaException{
        
        if(rs.matches("[a-zA-záéíóú\\-\\.\\/]{2,}(\\s[a-zA-záéíóú\\-\\.\\/]{2,})*")){
            return true;
        }else{
            throw new entradaInvalidaException("Razão social inválido!");
        }
    }
    
    public boolean validaInscEst(String ie) throws entradaInvalidaException{
        
        if(ie.matches("[0-9]{9,14}")){
            return true;
        }else{
            throw new entradaInvalidaException("Inscrição Estadual inválida (digite somente números)");
        }
    }
    
    public boolean validaRua(String rua) throws entradaInvalidaException{
        
        if(rua.matches("[a-zA-z0-9áéíóú]{2,}\\.?(\\s[a-zA-z0-9áéíóú]+)*")){
            return true;
        }else{
            throw new entradaInvalidaException("Rua inválida");
        }
    }
    
    public boolean validaNumRua(String nRua) throws entradaInvalidaException{
        
        if(nRua.matches("([a-zA-Z]{1})?[0-9]{1,4}([a-zA-Z]{1})?")){ //Pode ser somente número ou A1111 ou 1111B por exemplo.
            return true;
        }else{
            throw new entradaInvalidaException("Número do endereço inválido!");
        }
    }
    
    public boolean validaBairro(String rua) throws entradaInvalidaException{
        
        if(rua.matches("[a-zA-z]{2,}\\.?(\\s[a-zA-z]{2,})*")){
            return true;
        }else{
            throw new entradaInvalidaException("Bairro inválido!");
        }
    }
    
    public boolean validaCep(String cep) throws entradaInvalidaException{
        
        if(cep.matches("[0-9]{5}\\-[0-9]{3}")){
            return true;
        }else{
            throw new entradaInvalidaException("CEP inválido!");
        }
    }
    
    public boolean validaCidade(String cid) throws entradaInvalidaException{
        
        if(cid.matches("[a-zA-záéíóúãõ]{2,}(\\s[a-zA-záéíóúãõ]{2,})*")){
            return true;
        }else{
            throw new entradaInvalidaException("Cidade inválida!");
        }
    }
    
    public boolean validaTelefone(String tel) throws entradaInvalidaException{
        
        if(tel.matches("\\([1-9]{2}\\)[0-9]{4}\\-[0-9]{4}")){
            return true;
        }else{
            throw new entradaInvalidaException("Telefone inválido!");
        }
    }
    
    /*
    public boolean validaEstado(String est) throws entradaInvalidaException{
        
        if(est.matches("[RS]|[SC]|[PR]|[SP]|[MG]|[RJ]|[ES]|[BA]|[SE]|[AL]|[PE]|"
                + "[PB]|[RN]|[PI]|[MA]|[CE]|[GO]|[TO]|[MT]|[MS]|[DF]|[AM]|[AC]|"
                + "[PA]|[RO]|[RR]|[AP]")){
            return true;
        }else{
            throw new entradaInvalidaException("Verifique o Estado!");
        }
    }    
    */
}
