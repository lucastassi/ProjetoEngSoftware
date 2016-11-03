/*
aulas
https://www.youtube.com/watch?v=O4BdT0q-740
https://www.youtube.com/watch?v=WJ3700ZIxBc
https://www.youtube.com/watch?v=K_1KTYZBt9c
https://www.youtube.com/watch?v=nVZHH0B4t4w
https://www.youtube.com/watch?v=dXr83K4nH94
https://www.youtube.com/watch?v=DjNLLozpmaA
https://www.youtube.com/watch?v=WJ3700ZIxBc
 */
package my.contacteditor.conexao;
import java.util.ArrayList;
import my.contacteditor.FornecedorDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import my.contacteditor.ClienteDAO;
import my.contacteditor.HistoricoDAO;
import my.contacteditor.ProdutoDAO;
import my.contacteditor.entradaInvalidaException;
import my.contacteditor.validaEntrada;
/**
 *
 * @author  Lucas Tassi
 *          Lucas Diniz
 *          Lucas Barbosa
 *          Jhordan Garcia
 */
public class conexaoBD {
    Connection conexao;
    private Connection oConn;
    private Statement state;
    
    public conexaoBD(){
    }
    
    /*Realiza a conexão no BD*/
    public Connection conectarBD(){
        try{
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String driver = "org.postgresql.Driver";
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,"postgres","2402");
            System.out.println("Conexão efetuada com sucesso.");
            return conexao;    
        }
        catch(Exception ex){
            System.out.println("Erro ao conectar"+ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    /*Desconecta do BD*/
    public void desconBD(){
        try{
            conexao.close();
            System.out.println("Conexão finalizada com sucesso");
        }
        catch(Exception ex){
            System.out.println("Erro ao desconectar: "+ex.getMessage());
        }
    }
    
    /*Exclui o fornecedor do BD através do CNPJ*/
    public boolean excluirFornecedor(String cnpj) throws SQLException{
        conectarBD();
        state = conexao.createStatement();
        state.executeUpdate("delete from FORNECEDOR where cnpj = '"+cnpj+"'");
        desconBD(); 
        return true;
    }
    
    public boolean excluirCliente(String cnpj) throws SQLException{
        conectarBD();
        state = conexao.createStatement();
        state.executeUpdate("delete from CLIENTE where CCNPJ = '"+cnpj+"'");
        desconBD(); 
        return true;
    }
    
    /*Atualiza os dados dos fornecedores no BD*/ 
    /*Nessa classe, é feita uma verificação dos campos utilizando a classe validaEntrada.
    Se houver problema, as exceções são lançadas.*/
    public void atualizarForn(FornecedorDAO f) throws SQLException, entradaInvalidaException{
        conectarBD();
        state = conexao.createStatement();
 
            state.executeUpdate("update FORNECEDOR set FNome = '"+f.getNome()+"',"
                    + "FRua = '"+f.getRua()+"', FBairro = '"+f.getBairro()+"', FCep = '"+f.getCep()+"',"
                    + "FEndNum = '"+f.getNum()+"',FRazao= '"+f.getRazao()+"',FCidade = '"+f.getFCidade()+"', FEstado = '"+f.getFEstado()+"',FTel1= '"+f.getTelefone1()+"', FTel2 = '"+f.getTelefone2()+"'"
                    + " where cnpj= '"+f.getCnpj()+"';");                        
        desconBD();
    }
    
    public void atualizarCliente(ClienteDAO c) throws SQLException, entradaInvalidaException{
        conectarBD();
        state = conexao.createStatement();
 
            state.executeUpdate("update CLIENTE set CNome = '"+c.getNome()+"',"
                    + "CRua = '"+c.getRua()+"', CBairro = '"+c.getBairro()+"', CCep = '"+c.getCep()+"',"
                    + "CEndNum = '"+c.getNum()+"',CRazao= '"+c.getRazao()+"',CCidade = '"+c.getCCidade()+"', CEstado = '"+c.getCEstado()+"',CTel1= '"+c.getTelefone1()+"', CTel2 = '"+c.getTelefone2()+"'"
                    + " where CCnpj= '"+c.getCnpj()+"';");
            
             
        desconBD();
    }
    
    public void atualizarProduto(ProdutoDAO p) throws SQLException, entradaInvalidaException{
        conectarBD();
        state = conexao.createStatement();
 
            state.executeUpdate("update PRODUTOS set PNome = '"+p.getNome()+"', PDescricao = '"+p.getDescricao()+"', PDescReduzida = '"+p.getDescricaoReduzida()+"',"
                    + "PLocalEstoque = '"+p.getLocalEstoque()+"', PCBarras= '"+p.getCodBarras()+"', PFabricante = '"+p.getFabricante()+"', POutros = '"+p.getOutros()+"', PCategoria= '"+p.getCategoria()+"', PFabri = '"+p.getDataFab()+"',"
                    + "PValidade = '"+p.getDataVal()+"', PUnVenda = '"+p.getUnCompra()+"',PEstoque = '"+p.getEstoqueInicial()+"',"
                    + "PCusto = '"+p.getCusto()+"', PTributos = '"+p.getTributos()+"',PObservacoes = '"+p.getObservacoes()+"'   where PCod= '"+p.getCodigo()+"';"); 
    
        desconBD();
    }
    
    /*Insere os dados no BD*/
    /*Nessa classe, é feita uma verificação dos campos utilizando a classe validaEntrada.
    Se houver problema, as exceções são lançadas.*/
    
    public void insereDados(FornecedorDAO f) throws SQLException, entradaInvalidaException{
        conectarBD();
        state = conexao.createStatement();
        
        state.executeUpdate("insert into FORNECEDOR (CNPJ,InscEst,FNome,FRazao,FRua,FBairro,FCep,FEndNum,FCidade,FEstado,FTel1,FTel2)"
                    + "values ('"+f.getCnpj()+"','"+f.getInscEstadual()+"','"+f.getNome()+"','"+f.getRazao()+"','"+f.getRua()+"','"+f.getBairro()+"','"+f.getCep()+"',"
                    + "'"+f.getNum()+"','"+f.getFCidade()+"','"+f.getFEstado()+"','"+f.getTelefone1()+"','"+f.getTelefone2()+"');");

        desconBD();
    }
    
    
    /*VERIFICAR SE FUNCIONA*/
    public void insereDadosCliente(ClienteDAO c) throws SQLException, entradaInvalidaException{
        conectarBD();
        state = conexao.createStatement();
        
        state.executeUpdate("insert into CLIENTE (CCNPJ,CInscEst,CNome,CRazao,CRua,CBairro,CCep,CEndNum,CCidade,CEstado,CTel1,CTel2)"
                    + "values ('"+c.getCnpj()+"','"+c.getInscEstadual()+"','"+c.getNome()+"','"+c.getRazao()+"','"+c.getRua()+"','"+c.getBairro()+"','"+c.getCep()+"',"
                    + "'"+c.getNum()+"','"+c.getCCidade()+"','"+c.getCEstado()+"','"+c.getTelefone1()+"','"+c.getTelefone2()+"');");

        desconBD();
    }
    
    public void inserirDadosProduto(ProdutoDAO p)throws SQLException{
        conectarBD();
        state = conexao.createStatement();

        state.executeUpdate("insert into PRODUTOS (PCod, PNome, PDescricao, PDescReduzida, PLocalEstoque, PCBarras, PFabricante,POutros, PCategoria, PFabri, PValidade, PUnVenda, PEstoque, PCusto, PTributos, PObservacoes)"
                    + "values ('"+p.getCodigo()+"','"+p.getNome()+"','"+p.getDescricao()+"','"+p.getDescricaoReduzida()+"','"+p.getLocalEstoque()+"',"
                    + "'"+p.getCodBarras()+"','"+p.getFabricante()+"',"
                    + "'"+p.getOutros()+"','"+p.getCategoria()+"','"+p.getDataFab()+"','"+p.getDataVal()+"','"+p.getUnCompra()+"','"+p.getEstoqueInicial()+"','"+p.getCusto()+"','"+p.getTributos()+"','"+p.getObservacoes()+"');");
    
        desconBD();
    }
  
    /*Realiza um consulta no BD através do CNPJ*/
    public FornecedorDAO consultarFornecedor(String cnpj) throws SQLException{
        FornecedorDAO f = new FornecedorDAO();
        conectarBD();
        Statement state = conexao.createStatement();
        try(PreparedStatement p = conexao.prepareStatement("select * from FORNECEDOR where cnpj = '"+cnpj+"'")){
            ResultSet resSet = p.executeQuery();
            if(resSet.next()){
                f.setCnpj(resSet.getString("cnpj"));
                f.setInscEstadual(resSet.getString("inscEst"));
                f.setNome(resSet.getString("Fnome"));
                f.setRazao(resSet.getString("FRazao"));
                f.setTelefone1(resSet.getString("FTel1"));
                f.setTelefone2(resSet.getString("FTel2"));
                f.setBairro(resSet.getString("FBairro"));
                f.setRua(resSet.getString("FRua"));
                f.setNum(resSet.getString("FEndNum"));
                f.setCep(resSet.getString("FCep"));
                f.setFCidade(resSet.getString("FCidade"));
                f.setFEstado(resSet.getString("FEstado"));
            }
        }
        
       desconBD();          
       return f;
    }

    public ClienteDAO consultarCliente(String cnpj) throws SQLException{
        ClienteDAO c = new ClienteDAO();
        conectarBD();
        Statement state = conexao.createStatement();
        try(PreparedStatement p = conexao.prepareStatement("select * from CLIENTE where CCNPJ = '"+cnpj+"'")){
            ResultSet resSet = p.executeQuery();
            if(resSet.next()){
                c.setCnpj(resSet.getString("CCNPJ"));
                c.setInscEstadual(resSet.getString("CinscEst"));
                c.setNome(resSet.getString("Cnome"));
                c.setRazao(resSet.getString("CRazao"));
                c.setTelefone1(resSet.getString("CTel1"));
                c.setTelefone2(resSet.getString("CTel2"));
                c.setBairro(resSet.getString("CBairro"));
                c.setRua(resSet.getString("CRua"));
                c.setNum(resSet.getString("CEndNum"));
                c.setCep(resSet.getString("CCep"));
                c.setCCidade(resSet.getString("CCidade"));
                c.setCEstado(resSet.getString("CEstado"));
            }
        }
        
       desconBD();          
       return c;
    }
    
    public List<ProdutoDAO> consultaTodosProdutos() throws SQLException{
        
        List<ProdutoDAO> clis = new ArrayList<>();
        conectarBD();
        
        try (PreparedStatement pstm = conexao.prepareStatement("select PCod, PNome, PDescReduzida, PFabricante, PCusto, PTributos from PRODUTOS")) {
            ResultSet rs = pstm.executeQuery();
           
            while(rs.next()){              
                ProdutoDAO p = new ProdutoDAO();
                
                p.setCodigo(rs.getInt("PCod"));
                p.setNome(rs.getString("PNome"));
                p.setDescricaoReduzida(rs.getString("PDescReduzida"));
                p.setFabricante(rs.getString("PFabricante"));
                p.setCusto(rs.getFloat("PCusto"));
                p.setTributos(rs.getFloat("PTributos"));
                
            clis.add(p);
            }   
        }
        
       desconBD();
        
       return clis;    
    }
    
    
    public List<ProdutoDAO> consultaTodosProdutosPorNome(String nome) throws SQLException{
        
    List<ProdutoDAO> clis = new ArrayList<>();
        conectarBD();
        
        try (PreparedStatement pstm = conexao.prepareStatement("select PCod, PNome, PDescReduzida, PFabricante, PCusto, PTributos from PRODUTOS where PNome like '%"+nome+"%'")) {
            ResultSet rs = pstm.executeQuery();
           
            while(rs.next()){              
                ProdutoDAO p = new ProdutoDAO();
                
                p.setCodigo(rs.getInt("PCod"));
                p.setNome(rs.getString("PNome"));
                p.setDescricaoReduzida(rs.getString("PDescReduzida"));
                p.setFabricante(rs.getString("PFabricante"));
                p.setCusto(rs.getFloat("PCusto"));
                p.setTributos(rs.getFloat("PTributos"));
                
            clis.add(p);
            }   
        }
        
       desconBD();
        
       return clis; 
        
    }
    
    public List<ProdutoDAO> consultaTodosProdutosPorCodigo(String codigo) throws SQLException{
        
    List<ProdutoDAO> clis = new ArrayList<>();
        conectarBD();
        
        try (PreparedStatement pstm = conexao.prepareStatement("select PCod, PNome, PDescReduzida, PFabricante, PCusto, PTributos from PRODUTOS where PCod = "+codigo+"")) {
            ResultSet rs = pstm.executeQuery();
           
            while(rs.next()){              
                ProdutoDAO p = new ProdutoDAO();
                
                p.setCodigo(rs.getInt("PCod"));
                p.setNome(rs.getString("PNome"));
                p.setDescricaoReduzida(rs.getString("PDescReduzida"));
                p.setFabricante(rs.getString("PFabricante"));
                p.setCusto(rs.getFloat("PCusto"));
                p.setTributos(rs.getFloat("PTributos"));
                
            clis.add(p);
            }   
        }
        
       desconBD();
        
       return clis; 
        
    }
    
    public ProdutoDAO consultaTodosProdutosPorCodigo2(String codigo) throws SQLException{
        ProdutoDAO p = new ProdutoDAO();
        conectarBD();
        
        try (PreparedStatement pstm = conexao.prepareStatement("select PCod, PNome, PDescReduzida, PFabricante, PCusto, PTributos from PRODUTOS where PCod = "+codigo+"")) {
            ResultSet rs = pstm.executeQuery();
           
            while(rs.next()){              
                p.setCodigo(rs.getInt("PCod"));
                p.setNome(rs.getString("PNome"));
                p.setDescricaoReduzida(rs.getString("PDescReduzida"));
                p.setFabricante(rs.getString("PFabricante"));
                p.setCusto(rs.getFloat("PCusto"));
                p.setTributos(rs.getFloat("PTributos"));
            }   
        }
        
       desconBD();
        
       return p; 
        
    }
    
    
    public boolean excluirProduto(String codigo) throws SQLException{
        conectarBD();
        state = conexao.createStatement();
        state.executeUpdate("delete from PRODUTOS where PCod = '"+codigo+"'");
        desconBD(); 
        return true;
    }
    
    
    
    public ProdutoDAO consultarProduto(String codigo) throws SQLException{
        ProdutoDAO f = new ProdutoDAO();
        conectarBD();
        Statement state = conexao.createStatement();
        try(PreparedStatement p = conexao.prepareStatement("select * from PRODUTOS where PCod = '"+codigo+"'")){
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                f.setCodigo(rs.getInt("PCod"));
                f.setCodBarras(rs.getString("PCBarras"));
                f.setNome(rs.getString("PNome"));
                f.setDescricao(rs.getString("PDescricao"));
                f.setDescricaoReduzida(rs.getString("PDescReduzida"));
                f.setLocalEstoque(rs.getString("PLocalEstoque"));
                f.setFabricante(rs.getString("PFabricante"));
                f.setOutros(rs.getString("POutros"));
                f.setCategoria(rs.getString("PCategoria"));
                f.setDataFab(rs.getString("PFabri"));
                f.setDataVal(rs.getString("PValidade"));
                f.setUnCompra(rs.getString("PUnVenda"));
                f.setEstoqueInicial(rs.getInt("PEstoque"));
                f.setCusto(rs.getFloat("PCusto"));
                f.setTributos(rs.getFloat("PTributos"));
                f.setObservacoes(rs.getString("PObservacoes"));
            }
        }
      
       desconBD();          
       return f;
    }
    
    
    //inserido pelo DINIZ
    public List<HistoricoDAO> consultaTodosHistoricos() throws SQLException{
        
        List<HistoricoDAO> clis = new ArrayList<>();
        conectarBD();
        
        try (PreparedStatement pstm = conexao.prepareStatement("select A.HCod,A.Hdt ,B.PCod,P.PNome, B.Quantidade, F.FNome from HISTORICO_COMPRA A "
                + "join PRODUTO_HISTORICO B on  B.HCod=A.HCod join FORNECEDOR F on B.CNPJ=F.CNPJ JOIN PRODUTOS P "
                + "on B.PCod=P.PCod order by B.HCod")) {
            ResultSet rs = pstm.executeQuery();
           
            while(rs.next()){              
                HistoricoDAO f = new HistoricoDAO();
                
                f.setHcodigo(rs.getInt("HCod"));
                f.setData(rs.getString("Hdt"));
                f.setPCod(rs.getInt("PCod"));
                f.setPNome(rs.getString("PNome"));
                f.setQuantidade(rs.getInt("Quantidade"));
                f.setFNome(rs.getString("Fnome"));
                
            clis.add(f);
            }   
        }
        
       desconBD();
        
       return clis;    
    }
    //inserido pelo DINIZ
    public List<HistoricoDAO> consultarHistoricosCODIGO(String codigo) throws SQLException{
        
        List<HistoricoDAO> clis = new ArrayList<>();
        conectarBD();
        
        try (PreparedStatement pstm = conexao.prepareStatement("select A.HCod,A.Hdt ,B.PCod,P.PNome, B.Quantidade, F.FNome from HISTORICO_COMPRA A "
                + "join PRODUTO_HISTORICO B on  B.HCod=A.HCod join FORNECEDOR F on B.CNPJ=F.CNPJ JOIN PRODUTOS P "
                + "on B.PCod=P.PCod where B.HCod="+codigo+" order by B.HCod")) {
            ResultSet rs = pstm.executeQuery();
           
            while(rs.next()){              
                HistoricoDAO f = new HistoricoDAO();
                
                f.setHcodigo(rs.getInt("HCod"));
                f.setData(rs.getString("Hdt"));
                f.setPCod(rs.getInt("PCod"));
                f.setPNome(rs.getString("PNome"));
                f.setQuantidade(rs.getInt("Quantidade"));
                f.setFNome(rs.getString("Fnome"));
                
            clis.add(f);
            }   
        }
        
       desconBD();
        
       return clis;    
    }
    //inserido pelo DINIZ
    public void inserirDadosHistorico(HistoricoDAO h)throws SQLException{
        conectarBD();
        state = conexao.createStatement();

        state.executeUpdate("insert into HISTORICO_COMPRA (HCod, Hdt)"
                  + "values ('"+h.getHcodigo()+"','"+h.getData()+"');");
        state.executeUpdate("insert into PRODUTO_HISTORICO (PCod, HCod, Quantidade, CNPJ)"
                  + "values ('"+h.getPCod()+"','"+h.getHcodigo()+"','"+h.getQuantidade()+"','"+h.getFCod()+"');");

        desconBD();
    }
    //inserido pelo DINIZ
    public void inserirDadosHistorico2(HistoricoDAO h)throws SQLException{
        conectarBD();
        state = conexao.createStatement();

        state.executeUpdate("insert into PRODUTO_HISTORICO (PCod, HCod, Quantidade, CNPJ)"
                  + "values ('"+h.getPCod()+"','"+h.getHcodigo()+"','"+h.getQuantidade()+"','"+h.getFCod()+"');");

        desconBD();
    }
    //inserido pelo DINIZ
    public HistoricoDAO consultarHistorico(int HCodigo) throws SQLException{
        HistoricoDAO f = new HistoricoDAO();
        conectarBD();
        Statement state = conexao.createStatement();
        try(PreparedStatement p = conexao.prepareStatement("select A.HCod,A.Hdt ,B.PCod,P.PNome, B.Quantidade, F.FNome from HISTORICO_COMPRA A "
                + "join PRODUTO_HISTORICO B on  B.HCod=A.HCod join FORNECEDOR F on B.CNPJ=F.CNPJ JOIN PRODUTOS P "
                + "on B.PCod=P.PCod where A.HCod= '"+HCodigo+"' order by B.HCod;")){
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                f.setHcodigo(rs.getInt("HCod"));
                f.setData(rs.getString("Hdt"));
                f.setPCod(rs.getInt("PCod"));
                f.setPNome(rs.getString("PNome"));
                f.setQuantidade(rs.getInt("Quantidade"));
                f.setFNome(rs.getString("Fnome"));
                 
            }
        }
      
       desconBD();          
       return f;
    }
    //inserido pelo DINIZ
    public boolean excluirHistorico(String codigo) throws SQLException{
            conectarBD();
            state = conexao.createStatement();
            state.executeUpdate("delete from PRODUTO_HISTORICO where HCod = '"+codigo+"'");
            state.executeUpdate("delete from HISTORICO_COMPRA where HCod = '"+codigo+"'");
            desconBD(); 
            return true;
        }    
}
