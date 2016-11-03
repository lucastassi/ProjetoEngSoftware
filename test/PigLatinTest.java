
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.contacteditor.ClienteDAO;
import my.contacteditor.FornecedorDAO;
import my.contacteditor.PedidoDAO;
import my.contacteditor.ProdutoDAO;
import my.contacteditor.conexao.conexaoBD;
import my.contacteditor.validaEntrada;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author  Lucas Tassi
 *          Lucas Diniz
 *          Lucas Barbosa
 *          Jhordan Garcia
 */
public class PigLatinTest {
    
    validaEntrada ve = new validaEntrada();
    
    public PigLatinTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
        
        int codigo = 12345; 
        conexaoBD conexao = new conexaoBD();
        ProdutoDAO p = new ProdutoDAO();
        p.setCodigo(12345);
        assertEquals("Refrigerante de Testes", conexao.consultarProduto(Integer.toString(p.getCodigo())).getNome());

         assertEquals(true, conexao.excluirProduto(Integer.toString(codigo)));
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
/*
    @Test
    public void testaFalhaBanco(){
        Connection conexao;
        
        try{
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String driver = "org.postgresql.Driver";
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,"postgres","xxxx");
            System.out.println("Conexão efetuada com sucesso.");
            fail("Deu errado");
        }
        catch(Exception ex){
             assertTrue(ex.getMessage().equalsIgnoreCase(""+ex));
        }
    }
    */
    
       @Test
    public void testeInsercaoFornOk() throws SQLException, Exception{
     
     String cnpj=("00.000.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(11)1111-6666");
     String nome = ("Deu certo");
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("321");
     String cep = ("86800-000"); 
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
     FornecedorDAO forn = new FornecedorDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
     
     assertTrue(forn.insereFornecedor());
     }//Testa para ver se está sendo inserido com sucesso. Todos os campos obrigatórios estão preenchidos.
    
    
    @Test
    public void testeInsercaoFornFalha() throws entradaInvalidaException{
    
        
     String cnpj=("00.020.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(43)3422-2222");
     String nome = "1"; 
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("456"); //Somente aceita inteiro.
     String cep = ("86800-000"); 
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
     FornecedorDAO forn = new FornecedorDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);

     try{
        forn.insereFornecedor();
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Nome Fantasia inválido!"));
     }
    }
    
    
    @Test
    public void testeConsultaForn1() throws SQLException, Exception{
        conexaoBD conexao = new conexaoBD();
        FornecedorDAO forn = new FornecedorDAO();
        
        forn.setCnpj("00.000.000/0000-00");
        forn.setNome("Distribuidora Teste atualizado");
        forn.setTelefone1("(43)3422-2222");
        forn.setRua("Das flores");
        forn.setNum("123");
        
        try{
            assertEquals(forn.getNome(),conexao.consultarFornecedor(forn.getCnpj()).getNome());
        }
        catch(SQLException ex){
            System.out.println("Falha ao consultar. Erro: "+ex);
        } 

    }//Teste de consulta validando por nome
    
    @Test
    public void testeConsultaForn2() throws SQLException, Exception{
        conexaoBD conexao = new conexaoBD();
        FornecedorDAO forn = new FornecedorDAO();
        
        forn.setCnpj("00.000.000/0000-00");
        forn.setNome("Distribuidora Teste");
        forn.setTelefone1("(43)3422-2222");
        forn.setRua("Das flores");
        forn.setNum("123");
        
        
        try{
            assertEquals(forn.getTelefone1(),conexao.consultarFornecedor(forn.getCnpj()).getTelefone1());
        }
        catch(SQLException ex){
            System.out.println("Falha ao consultar. Erro: "+ex);
        }    

    }//Teste de consulta validando por telefone
    
    @Test
    public void testeInsercaoFornErradoCNPJ() throws entradaInvalidaException{
        
        String cnpj="";
        String telefone1=("(43)3422-2222");
        String telefone2 = ("(43)4321-7894");
        String nome = ("Distribuidora Teste");
        String rua =("Rua dos Testes");
        String bairro = ("Jd dos Testes");
        String num = ("123");
        String cep = ("86800-000"); 
        String razao = ("joselino LTDA");
        String inscricao = ("123456789");
        String cidade = ("Apucarana");
        String estado = ("PR");
        
        FornecedorDAO forn = new FornecedorDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
        
     try{
        forn.insereFornecedor();
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: CNPJ Inválido!"));
     }
    }//Teste para falhar caso CNPJ seja null
    
     @Test
    public void testeInsercaoFornErradoNome() throws SQLException, Exception{
    
	String cnpj=("11.111.111/1111-44");
	String telefone1=("(43)3422-2222");
	String telefone2 = ("(43)4321-7894");
	String nome = "Olá refri";
	String rua = ("Rua dos Testes");
	String bairro = ("Jd dos Testes");
	String num = ("123");
	String cep = ("86800-000");
        String razao = ("1");
        String inscricao = ("123456789");
        String cidade = ("Apucarana");
        String estado = ("PR");
	   
	FornecedorDAO forn = new FornecedorDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
	
     try{
        forn.insereFornecedor();
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Razão social inválido!"));
     }
	     
    }//Teste para falhar caso nome seja null
    


    @Test
    public void testeInsercaoFornErradoNumLetra() throws entradaInvalidaException{
    
     String cnpj=("00.000.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(43)4321-7894");
     String nome = ("Distribuidora Teste");
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("abcAAAAAAAA");
     String cep = ("86800-000"); 
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
     FornecedorDAO forn = new FornecedorDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
     
     try{
        assertTrue(forn.insereFornecedor());
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Número do endereço inválido!"));
     }
   

    }  //Teste para falhar caso entre uma string no atributo Numero
    
    @Test
    public void testeInsercaoFornErradoNumNull() throws SQLException, Exception{
    
	String cnpj=("11.111.111/1111-33");
	String telefone1=("(43)3422-2222");
	String telefone2 = ("(43)4321-7894");
	String nome = ("Distribuidora Teste");
	String rua =("Rua dos Testes");
	String bairro = ("Jd dos Testes");
	String num = "";
	String cep = ("86800-000"); 
        String razao = ("joselino LTDA");
        String inscricao = ("123456789");
        String cidade = ("Apucarana");
        String estado = ("PR");
        
        FornecedorDAO forn = new FornecedorDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
        
     try{
        assertTrue(forn.insereFornecedor());
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Número do endereço inválido!"));
     }
	     
    }//Teste para falhar caso entre null no atributo Numero
    
    @Test
    public void testeInsercaoFornErradoRua() throws SQLException, Exception{
    
	String cnpj=("11.111.111/1111-66");
	String telefone1=("(43)3422-2222");
	String telefone2 = ("(43)4321-7894");
	String nome = ("Distribuidora Teste");
	String rua = "";
	String bairro = ("Jd dos Testes");
	String num = ("123");
	String cep = ("86800-000"); 
        String razao = ("joselino LTDA");
        String inscricao = ("123456789");
        String cidade = ("Apucarana");
        String estado = ("PR");
	     
	FornecedorDAO forn = new FornecedorDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);

     try{
        assertTrue(forn.insereFornecedor());
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Rua inválida"));
     }
	     
    }//Teste para falhar caso Rua seja null
    
    @Test
    public void atualizaFornecedorTesteOk() throws SQLException, Exception{
     String cnpj=("00.000.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(43)4321-7894");
     String nome = ("Distribuidora Teste atualizado");
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("123");
     String cep = ("86800-000"); 
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
     FornecedorDAO forn = new FornecedorDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
     assertEquals(true, forn.atualizaFornecedor());
    }//Teste para verificar se a atulização funciona
    
    
    @Test
    public void atualizaFornecedorTesteFalha() throws SQLException, Exception{
     String cnpj=("00.000.000/0000-00");
     String telefone1="";
     String telefone2 = "";
     String nome = "";
     String rua ="";
     String bairro = "";
     String num = "";
     String cep = "";  
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
     FornecedorDAO forn = new FornecedorDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
     
     try{
        assertTrue(forn.insereFornecedor());
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Nome fantasia inválido!"));
     }
    }//Teste para verificar se a atulização nao funciona

    @Test
    public void deletaForn() throws SQLException{
     String cnpj=("00.000.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(43)4321-7894");
     String nome = ("Distribuidora Teste");
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("123");
     String cep = ("86.800-000"); 
       
     conexaoBD conexao = new conexaoBD();
     //Fornecedor forn = new Fornecedor(cnpj,nome,rua,bairro,cep,num,telefone1,telefone2);
     assertEquals(true, conexao.excluirFornecedor(cnpj));
    }//Teste para verificar se a deleção funciona
    
    
    /*Utiliza a classe entradaInvalida para validas as entradas.*/
    @Test
    public void testaCNPJ() throws Exception{
        String teste = "22.456.789/0000-96";
        
        assertTrue(ve.validaCNPJ(teste));
    }

    @Test
    public void testaNomeFantasia() throws Exception{
        String teste = "Refrigerante 333 Ki-Sabor";
        
        assertTrue(ve.validaNomeFantasia(teste));
    }

    @Test
    public void testaRazaoSocial() throws Exception{
        String teste = "Coca-Cola do Brasil S/A";
        
        assertTrue(ve.validaRazaoSocial(teste));
    }    

    @Test
    public void testaInscEst() throws Exception{
        String teste = "123456789";
        
        assertTrue(ve.validaInscEst(teste));
    }

    @Test
    public void testaRua() throws Exception{
        String teste = "Rua dos Passaros de 2 anos";
        
        assertTrue(ve.validaRua(teste));
    }    

    @Test
    public void testaNumRua() throws Exception{
        String teste = "1042a";
        
        assertTrue(ve.validaNumRua(teste));
    }

    @Test
    public void testaBairro() throws Exception{
        String teste = "Jardim Guanabara";
        
        assertTrue(ve.validaBairro(teste));
    }

    @Test
    public void testeCEP() throws Exception{
        String teste = "00000-080";
        
        assertTrue(ve.validaCep(teste));
    }

    @Test
    public void testeCidade() throws Exception{
        String teste = "Cornélio Procópio";
        
        assertTrue(ve.validaCidade(teste));
    }
    
    @Test
    public void testeTelefone() throws Exception{
        String teste = "(43)0000-9999";
        
        assertTrue(ve.validaTelefone(teste));
    }
    
    @Test
    public void testeCNPJFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaCNPJ(""));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("CNPJ Inválido!"));
        }
    }

    @Test
    public void testeCNPJFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaCNPJ("99.000.96a/7777-96"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("CNPJ Inválido!"));
        }
    }
    
    @Test
    public void testeNomeFantasiaFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaNomeFantasia(""));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Nome Fantasia inválido!"));
        }
    }
    
    @Test
    public void testeNomeFantasiaFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaNomeFantasia("z*"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Nome Fantasia inválido!"));
        }
    }
    
    @Test
    public void testeRazaoSocialFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaRazaoSocial("Refrigerantes 3 Corações S/A"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Razão social inválido!"));
        }
    }
    
    @Test
    public void testeRazaoSocialFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaRazaoSocial(""));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Razão social inválido!"));
        }
    }
 
    @Test
    public void testeInscEstFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaInscEst("11111111"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Inscrição Estadual inválida (digite somente números)"));
        }
    }
    
    @Test
    public void testeInscEstFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaInscEst("111111111111111"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Inscrição Estadual inválida (digite somente números)"));
        }
    }
    
    @Test
    public void testeInscEstFalha3() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaInscEst("a5644854"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Inscrição Estadual inválida (digite somente números)"));
        }
    }
    
    @Test
    public void testeRuaFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaRua("Rua * Benedito"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Rua inválida"));
        }
    }
    
    @Test
    public void testeRuaFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaRua("Av. Deus eh +"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Rua inválida"));
        }
    }

    @Test
    public void testeNumRuaFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaNumRua("Cinco"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Número do endereço inválido!"));
        }
    }
    
    @Test
    public void testeNumRuaFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaNumRua("."));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Número do endereço inválido!"));
        }
    }
    
    @Test
    public void testeBairroFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaBairro("Jd. + ou -"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Bairro inválido!"));
        }
    }

    @Test
    public void testeBairroFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaBairro("Vila 5"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Bairro inválido!"));
        }
    }

    @Test
    public void testeCepFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaCep("a"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("CEP inválido!"));
        }
    }
 
    @Test
    public void testeCepFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaCep(""));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("CEP inválido!"));
        }
    }
    
    @Test
    public void testeCepFalha3() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaCep("00000-0000"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("CEP inválido!"));
        }
    }
    
    @Test
    public void testeCepFalha4() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaCep("000"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("CEP inválido!"));
        }
    }
    
    @Test
    public void testeCidadeFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaCidade("L0ndr1n4"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Cidade inválida!"));
        }
    }
    
    @Test
    public void testeCidadeFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaCidade("p"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Cidade inválida!"));
        }
    }
    
    @Test
    public void testeCidadeFalha3() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaCidade(""));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Cidade inválida!"));
        }
    }
    
    @Test
    public void testeTelefoneFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaTelefone("(043)0000-0000"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Telefone inválido!"));
        }
    }
    
    @Test
    public void testeTelefoneFalha2() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaTelefone("(43)0000-00000"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Telefone inválido!"));
        }
    }
    
    @Test
    public void testeTelefoneFalha3() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaTelefone("(43)0000-000x"));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Telefone inválido!"));
        }
    }
    
     @Test
    public void testeTelefoneFalha4() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaTelefone(""));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Telefone inválido!"));
        }
    }
    
    
    
    /*----------------------------------------------------------------------------------------------------------------
                                                     TESTE PARA CLIENTE
   ---------------------------------------------------------------------------------------------------------------- */
    
    
    
    @Test
    public void testeInsercaoClienteOk() throws SQLException, Exception{
     
     String cnpj=("00.000.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(11)1111-6666");
     String nome = ("Deu certo");
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("321");
     String cep = ("86800-000"); 
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
     ClienteDAO cliente = new ClienteDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
     
     assertTrue(cliente.insereCliente());
     }//Testa para ver se está sendo inserido com sucesso. Todos os campos obrigatórios estão preenchidos.
    
    
    @Test
    public void testeInsercaoClienteFalha() throws entradaInvalidaException{
        
     String cnpj=("00.020.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(43)3422-2222");
     String nome = "1"; 
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("456"); //Somente aceita inteiro.
     String cep = ("86800-000"); 
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
     ClienteDAO cliente = new ClienteDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);

     try{
        cliente.insereCliente();
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Nome Fantasia inválido!"));
     }
    }
    
    
    @Test
    public void testeConsultaCliente2() throws SQLException, Exception{
        conexaoBD conexao = new conexaoBD();
         ClienteDAO cliente = new ClienteDAO();
        
        cliente.setCnpj("00.000.000/0000-00");
        cliente.setNome("Distribuidora Teste");
        cliente.setTelefone1("(43)3422-2222");
        cliente.setRua("Das flores");
        cliente.setNum("123");
        
        
        try{
            assertEquals(cliente.getTelefone1(),conexao.consultarCliente(cliente.getCnpj()).getTelefone1());
        }
        catch(SQLException ex){
            System.out.println("Falha ao consultar. Erro: "+ex);
        }    

    }//Teste de consulta validando por telefone
    
    @Test
    public void testeInsercaoClienteErradoCNPJ() throws entradaInvalidaException{
        
        String cnpj="";
        String telefone1=("(43)3422-2222");
        String telefone2 = ("(43)4321-7894");
        String nome = ("Distribuidora Teste");
        String rua =("Rua dos Testes");
        String bairro = ("Jd dos Testes");
        String num = ("123");
        String cep = ("86800-000"); 
        String razao = ("joselino LTDA");
        String inscricao = ("123456789");
        String cidade = ("Apucarana");
        String estado = ("PR");
        
         ClienteDAO cliente = new ClienteDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
        
     try{
        cliente.insereCliente();
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: CNPJ Inválido!"));
     }
    }//Teste para falhar caso CNPJ seja null
    
     @Test
    public void testeInsercaoClienteErradoNome() throws SQLException, Exception{
    
	String cnpj=("11.111.111/1111-44");
	String telefone1=("(43)3422-2222");
	String telefone2 = ("(43)4321-7894");
	String nome = "Olá refri";
	String rua = ("Rua dos Testes");
	String bairro = ("Jd dos Testes");
	String num = ("123");
	String cep = ("86800-000");
        String razao = ("1");
        String inscricao = ("123456789");
        String cidade = ("Apucarana");
        String estado = ("PR");
	   
	 ClienteDAO cliente = new ClienteDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
	
     try{
        cliente.insereCliente();
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Razão social inválido!"));
     }
	     
    }//Teste para falhar caso nome seja null
    


    @Test
    public void testeInsercaoClienteErradoNumLetra() throws entradaInvalidaException{
    
     String cnpj=("00.000.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(43)4321-7894");
     String nome = ("Distribuidora Teste");
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("abcAAAAAAAA");
     String cep = ("86800-000"); 
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
      ClienteDAO cliente = new ClienteDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
     
     try{
        assertTrue(cliente.insereCliente());
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Número do endereço inválido!"));
     }
   

    }  //Teste para falhar caso entre uma string no atributo Numero
    
    @Test
    public void testeInsercaoClienteErradoNumNull() throws SQLException, Exception{
    
	String cnpj=("11.111.111/1111-33");
	String telefone1=("(43)3422-2222");
	String telefone2 = ("(43)4321-7894");
	String nome = ("Distribuidora Teste");
	String rua =("Rua dos Testes");
	String bairro = ("Jd dos Testes");
	String num = "";
	String cep = ("86800-000"); 
        String razao = ("joselino LTDA");
        String inscricao = ("123456789");
        String cidade = ("Apucarana");
        String estado = ("PR");
        
         ClienteDAO cliente = new ClienteDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
        
     try{
        assertTrue(cliente.insereCliente());
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Número do endereço inválido!"));
     }
	     
    }//Teste para falhar caso entre null no atributo Numero
    
    @Test
    public void testeInsercaoClienteErradoRua() throws SQLException, Exception{
    
	String cnpj=("11.111.111/1111-66");
	String telefone1=("(43)3422-2222");
	String telefone2 = ("(43)4321-7894");
	String nome = ("Distribuidora Teste");
	String rua = "";
	String bairro = ("Jd dos Testes");
	String num = ("123");
	String cep = ("86800-000"); 
        String razao = ("joselino LTDA");
        String inscricao = ("123456789");
        String cidade = ("Apucarana");
        String estado = ("PR");
	     
	 ClienteDAO cliente = new ClienteDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);

     try{
        assertTrue(cliente.insereCliente());
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Rua inválida"));
     }
	     
    }//Teste para falhar caso Rua seja null
    
    @Test
    public void atualizaClienteTesteOk() throws SQLException, Exception{
     String cnpj=("00.000.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(43)4321-7894");
     String nome = ("Distribuidora Teste atualizado");
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("123");
     String cep = ("86800-000"); 
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
    ClienteDAO cliente = new ClienteDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
     assertEquals(true, cliente.atualizaCliente());
    }//Teste para verificar se a atulização funciona
    
    
    @Test
    public void atualizaClienteTesteFalha() throws SQLException, Exception{
     String cnpj=("00.000.000/0000-00");
     String telefone1="";
     String telefone2 = "";
     String nome = "";
     String rua ="";
     String bairro = "";
     String num = "";
     String cep = "";  
     String razao = ("joselino LTDA");
     String inscricao = ("123456789");
     String cidade = ("Apucarana");
     String estado = ("PR");
     
     ClienteDAO cliente = new ClienteDAO(cnpj,inscricao,nome,razao,rua,bairro,cep,num,cidade,estado,telefone1,telefone2);
     
     try{
        assertTrue(cliente.insereCliente());
        fail("Era pra ter entrado na exceção!");
     }catch(Exception ex){
         assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Nome fantasia inválido!"));
     }
    }//Teste para verificar se a atulização nao funciona

    @Test
    public void deletaCliente() throws SQLException{
     String cnpj=("00.000.000/0000-00");
     String telefone1=("(43)3422-2222");
     String telefone2 = ("(43)4321-7894");
     String nome = ("Distribuidora Teste");
     String rua =("Rua dos Testes");
     String bairro = ("Jd dos Testes");
     String num = ("123");
     String cep = ("86.800-000"); 
       
     conexaoBD conexao = new conexaoBD();
     assertEquals(true, conexao.excluirCliente(cnpj));
    }//Teste para verificar se a deleção funciona
    
     /*----------------------------------------------------------------------------------------------------------------
                                                     TESTE PARA PRODUTOS (LUCAS TASSI)
   ---------------------------------------------------------------------------------------------------------------- */
    
        
    @Test
    public void testeInsercaoProdutoOk() throws SQLException, Exception{
     
     int codigo = 12345;
     String nome=("Refrigerante de Testes");
     String descricao = ("descricao");
     String descricaoReduzida = ("DescReduzida");
     String codigoBarras =("12312123");
     String PLocalEstoque = ("");
     String fabricante = ("Fabricante");
     String categoria = ("Refrigerante"); 
     String dataFabricacao = ("22/22/2222");
     String dataValidade = ("22/22/2222");
     String unCompra = ("UN - Unidade");
     int estoque = 30;
     float custo = (float) 32.50;
     float tributos = (float) 33.33;
     String obs = "REFRIGERANTE COM GÁS";
     String outros = "";
     
     
    ProdutoDAO p = new ProdutoDAO(codigo, nome, descricao, descricaoReduzida, codigoBarras, PLocalEstoque, fabricante, categoria, dataFabricacao, dataValidade, unCompra, estoque,
                    custo, tributos, obs, outros);
     
     assertTrue(p.insereProduto());
     }//Testa para ver se está sendo inserido com sucesso. Todos os campos obrigatórios estão preenchidos.

    
    @Test
    public void testeConsultaProdutoGeral() throws SQLException{
    }
    
    
    @Test
    public void testeInsercaoProdutoFalha() throws SQLException, Exception{
     
     int codigo = 1234;
     String nome=("Coca Cola");
     String descricao = ("descricao");
     String descricaoReduzida = ("DescReduzida");
     String codigoBarras =("12312123");
     String PLocalEstoque = ("");
     String fabricante = ("Fabricante");
     String categoria = ("Refrigerante"); 
     String dataFabricacao = ("2/22/2222");
     String dataValidade = ("22/22/2222");
     String unCompra = ("UN - Unidade");
     int estoque = 30;
     float custo = (float) 32.50;
     float tributos = (float) 33.33;
     String obs = "REFRIGERANTE COM GÁS";
     String outros = "";
     
     
    ProdutoDAO p = new ProdutoDAO(codigo, nome, descricao, descricaoReduzida, codigoBarras, PLocalEstoque, fabricante, categoria, dataFabricacao, dataValidade, unCompra, estoque,
                    custo, tributos, obs, outros);
     
      try{
        assertTrue(p.insereProduto());
      }catch(Exception ex){
          assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Data inválida!"));
      }
     }
    
        @Test
    public void testeInsercaoProdutoFalha2() throws SQLException, Exception{
     
     int codigo = 1234;
     String nome=("Coca Cola");
     String descricao = ("@$#$");
     String descricaoReduzida = ("DescReduzida");
     String codigoBarras =("12312123");
     String PLocalEstoque = ("");
     String fabricante = ("Fabricante");
     String categoria = ("Refrigerante"); 
     String dataFabricacao = ("22/22/2222");
     String dataValidade = ("22/22/2222");
     String unCompra = ("UN - Unidade");
     int estoque = 30;
     float custo = (float) 32.50;
     float tributos = (float) 33.33;
     String obs = "REFRIGERANTE COM GÁS";
     String outros = "";
     
     
    ProdutoDAO p = new ProdutoDAO(codigo, nome, descricao, descricaoReduzida, codigoBarras, PLocalEstoque, fabricante, categoria, dataFabricacao, dataValidade, unCompra, estoque,
                    custo, tributos, obs, outros);
     
      try{
        assertTrue(p.insereProduto());
      }catch(Exception ex){
          assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Nome inválido! Verifique:\n-Nome\n-Descrição\n-Desc.Reduzida"));
      }
     }
    
    @Test
    public void testeConsultaProduto() throws SQLException, Exception{
        conexaoBD conexao = new conexaoBD();
        ProdutoDAO p = new ProdutoDAO();
        
        p.setCodigo(12345);
        
        try{
            assertEquals("Refrigerante de Testes", conexao.consultaTodosProdutosPorCodigo2(Integer.toString(p.getCodigo())).getNome());
        }
        catch(SQLException ex){
            System.out.println("Falha ao consultar. Erro: "+ex);
        }    

    }
    
    @Test
    public void testeAtualizarProduto() throws SQLException, Exception{
        conexaoBD conexao = new conexaoBD();
        
        int codigo = 12345;
        String nome=("Refrigerante de Testes");
        String descricao = ("descricao d");
        String descricaoReduzida = ("DescReduzida");
        String codigoBarras =("12312123");
        String PLocalEstoque = ("");
        String fabricante = ("Fabricante");
        String categoria = ("Refrigerante"); 
        String dataFabricacao = ("22/22/2222");
        String dataValidade = ("22/22/2222");
        String unCompra = ("UN - Unidade");
        int estoque = 30;
        float custo = (float) 32.50;
        float tributos = (float) 33.33;
        String obs = "REFRIGERANTE COM GÁS";
        String outros = "";

        ProdutoDAO p = new ProdutoDAO(codigo, nome, descricao, descricaoReduzida, codigoBarras, PLocalEstoque, fabricante, categoria, dataFabricacao, dataValidade, unCompra, estoque,
                    custo, tributos, obs, outros);
        
        assertTrue(p.atualizaProduto()); 

    }
    
    @Test
    public void testeAtualizarProdutoFalha2() throws SQLException, Exception{
     
     int codigo = 1234;
     String nome=("Coca Cola");
     String descricao = ("Ououuu");
     String descricaoReduzida = ("DescReduzida");
     String codigoBarras =("12312123");
     String PLocalEstoque = ("");
     String fabricante = ("Fabricante");
     String categoria = ("Refrigerante"); 
     String dataFabricacao = ("22/22/2222");
     String dataValidade = ("22/22/2222");
     String unCompra = ("UN - Unidade");
     int estoque = 30;
     float custo = (float) -1;
     float tributos = (float) 33.33;
     String obs = "REFRIGERANTE COM GÁS";
     String outros = "";
     
     
    ProdutoDAO p = new ProdutoDAO(codigo, nome, descricao, descricaoReduzida, codigoBarras, PLocalEstoque, fabricante, categoria, dataFabricacao, dataValidade, unCompra, estoque,
                    custo, tributos, obs, outros);
     
      try{
        assertTrue(p.atualizaProduto());
      }catch(Exception ex){
          assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Custo inválido! \n - Digite ',' para separar os centavos!"));
      }
     }
    
    @Test
    public void testaCustoOk() throws Exception{
       float teste = (float) 2.30;
        
        assertTrue(ve.validaCusto(teste));
    }
    
    @Test
    public void testaCustoFalha() throws Exception{
       String teste = "a";
        
       try{
        assertFalse(ve.validaCusto(Float.parseFloat(teste)));
        fail("Era pra ter entrado aqui!");
       }catch(NumberFormatException ex){
           assertTrue(ex.getMessage().equalsIgnoreCase("For input string: \"a\""));
       }
    }
    
    @Test
    public void testaTributo() throws Exception{
       float teste = (float) 2.30;
        
        assertTrue(ve.validaTributos(teste));
    }
    
    public void testaTributoFalha() throws Exception{
       String teste = "a";
        
       try{
        assertFalse(ve.validaTributos(Float.parseFloat(teste)));
        fail("Era pra ter entrado aqui!");
       }catch(NumberFormatException ex){
           assertTrue(ex.getMessage().equalsIgnoreCase("For input string: \"a\""));
       }
    }
    
    @Test
    public void testaTributoFalha2() throws entradaInvalidaException{
       float teste = -1;
        
       try{
        assertTrue(ve.validaTributos(teste));
        fail("Era pra ter entrado na excessão!");
       }catch(Exception ex){
           assertTrue(ex.getMessage().equalsIgnoreCase("Tributo inválido! \n - Digite com '.' ou ','!"));
       }
    }
    
    @Test
    public void testaCustoFalha2() throws Exception{
       String teste = "1.9)";
        
       try{
            assertFalse(ve.validaCusto(Float.parseFloat(teste)));
            fail("Era pra ter entrado aqui!");
       }catch(NumberFormatException ex){
          
       }
    }
    
    @Test
    public void testaCustoFalha3() throws entradaInvalidaException{
       float teste = -1;
        
       try{
        assertTrue(ve.validaCusto(teste));
        fail("Era pra ter entrado na excessão!");
       }catch(Exception ex){
           assertTrue(ex.getMessage().equalsIgnoreCase("Custo inválido! \n - Digite ',' para separar os centavos!"));
       }
    }
    
    @Test
    public void testaEstoque() throws Exception{
       int teste = 999;
        
        assertTrue(ve.validaEstoque(teste));
    }
    
    @Test
    public void testaEstoqueFalha() throws entradaInvalidaException{
       int teste = -1;
        
       try{
        assertTrue(ve.validaEstoque(teste));
        fail("Era pra ter entrado na excessão!");
       }catch(Exception ex){
           assertTrue(ex.getMessage().equalsIgnoreCase("Estoque inicial inválido! \n - Somente números inteiros"));
       }
    }
    
    @Test
    public void testaNome() throws Exception{
       String teste = "Coca Cola Latinha";
        
        assertTrue(ve.validaNome(teste));
    }
    
    @Test
    public void testeNomeFalha() throws entradaInvalidaException{
        try{
            assertTrue(ve.validaNome(""));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Nome inválido! Verifique:\n-Nome\n-Descrição\n-Desc.Reduzida"));
        }
    }
    
    @Test
    public void testaCodigo() throws entradaInvalidaException{
        int teste = -123;
        
        try{
            assertTrue(ve.validaCodigo(teste));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Código inválido!\n - Somente números"));
        }
    }
    
    @Test
    public void testaDataOk() throws Exception{
        String teste = "24/02/1996";
      
        assertTrue(ve.validaDatas(teste));     
    }
    
    @Test
    public void testaDataFalha() throws entradaInvalidaException{
         String teste = "24/02/199";
        
        try{
            assertTrue(ve.validaDatas(teste));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Data inválida!"));
        }
    }
    
    @Test
    public void testaDataFalha2() throws entradaInvalidaException{
         String teste = "24/a/1996";
        
        try{
            assertTrue(ve.validaDatas(teste));
            fail("Era pra ter lançado a excessão!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("Data inválida!"));
        }
    }
    
    @Test
    public void testaConsultaTodosProdutos() throws SQLException{
        ProdutoDAO p = new ProdutoDAO();
        conexaoBD bd = new conexaoBD();
        ArrayList<ProdutoDAO> a = new ArrayList<ProdutoDAO>();
        
        a = (ArrayList<ProdutoDAO>) bd.consultaTodosProdutos();
        
        assertEquals(777, a.get(0).getCodigo());
        
    }
    
    @Test
    public void testaConsultaTodosProdutos2() throws SQLException{
        ProdutoDAO p = new ProdutoDAO();
        conexaoBD bd = new conexaoBD();
        ArrayList<ProdutoDAO> a = new ArrayList<ProdutoDAO>();
        
        a = (ArrayList<ProdutoDAO>) bd.consultaTodosProdutos();
        
        assertEquals("COCA", a.get(0).getNome());
        
    }
    
    @Test
    public void testaConsultaProdutosNome() throws SQLException{
        ProdutoDAO p = new ProdutoDAO();
        conexaoBD bd = new conexaoBD();
        ArrayList<ProdutoDAO> a = new ArrayList<ProdutoDAO>();
        p.setNome("Testes");
        
        a = (ArrayList<ProdutoDAO>) bd.consultaTodosProdutosPorNome(p.getNome());
        
        assertEquals("Refrigerante de Testes", a.get(0).getNome());
    }
    
    @Test
    public void testaConsultaProdutosCodigo() throws SQLException{
        ProdutoDAO p = new ProdutoDAO();
        conexaoBD bd = new conexaoBD();
        ArrayList<ProdutoDAO> a = new ArrayList<ProdutoDAO>();
        p.setCodigo(12345);
        
        a = (ArrayList<ProdutoDAO>) bd.consultaTodosProdutosPorCodigo(Integer.toString(p.getCodigo()));
        
        assertEquals("Refrigerante de Testes", a.get(0).getNome());
    }
    
    @Test
    public void testaConsultaProdutosCodigoFalha() throws SQLException{
        ProdutoDAO p = new ProdutoDAO();
        conexaoBD bd = new conexaoBD();
        ArrayList<ProdutoDAO> a = new ArrayList<ProdutoDAO>();
        p.setCodigo(56);
        
        a = (ArrayList<ProdutoDAO>) bd.consultaTodosProdutosPorCodigo(Integer.toString(p.getCodigo()));
        
        try{
            assertEquals("null", a.get(0).getNome());
            fail("Era pra ter entrado na excessão!");
        }catch(Exception ex){
            
        }
    }
     
    /*----------------------------------------------------------------------------------------------------------------
                                                     TESTE PARA PEDIDO
   ---------------------------------------------------------------------------------------------------------------- */
    
    @Test
    public void inserePedido() throws SQLException, Exception{
        String valor=("50");
        String data=("2002-02-02");
        String pago=("true");
        
        PedidoDAO pedido = new PedidoDAO(Float.parseFloat(valor), data, Boolean.parseBoolean(pago));

        assertTrue(pedido.inserePedido());
    }
    
    @Test
    public void atualizaPedido() throws SQLException, Exception{
        String valor = ("30");
        String data = ("2004-04-04");
        String pago = ("true");
        
        PedidoDAO pedido = new PedidoDAO(Float.parseFloat(valor), data, Boolean.parseBoolean(pago));
        pedido.setCodigo(3);
        
        assertTrue(pedido.atualizaPedido());
    }
    
        
    @Test
    public void atualizaPedidoFalha() throws SQLException, Exception{
        String valor = ("-30");
        String data = ("2004-04-04");
        String pago = ("true");
        
        PedidoDAO pedido = new PedidoDAO(Float.parseFloat(valor), data, Boolean.parseBoolean(pago));
        pedido.setCodigo(3);
        
        try{
            assertTrue(pedido.atualizaPedido());
              fail("Era pra ter entrado na exceção!");
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Custo inválido! \n - Digite ',' para separar os centavos!"));     
        }
    }
    
    
    @Test
    public void inserePedidoFalhaValor() throws SQLException, Exception{
        String valor = ("-50");
        String data = ("2004-04-04");
        String pago = ("true");
        
        PedidoDAO pedido = new PedidoDAO(Float.parseFloat(valor), data, Boolean.parseBoolean(pago));
        try{
            pedido.inserePedido();
            fail("Era pra ter entrada na excecao");
        }
        catch(Exception ex)
        {
            assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Custo inválido! \n - Digite ',' para separar os centavos!"));
        }
    }
    
    /*
    @Test
    public void inserePedidoFalhaData() throws SQLException, Exception{
        String valor = ("-50");
        String data = ("2004-13-04");
        String pago = ("true");
        
        PedidoDAO pedido = new PedidoDAO(Float.parseFloat(valor), data, Boolean.parseBoolean(pago));
        try{
            pedido.inserePedido();
            fail("Era pra ter entrada na excecao");
        }
        catch(Exception ex)
        {
            assertTrue(ex.getMessage().equalsIgnoreCase("my.contacteditor.entradaInvalidaException: Data Invalida!"));
        }
    }*/
    
    @Test
    public void consultaPedidoOK() throws SQLException, Exception{
        String codigo="4";
        PedidoDAO p = new PedidoDAO(50,"2002-02-02",true);
        conexaoBD conexao = new conexaoBD();

        assertEquals(p.getDataEntrega(),conexao.consultarPedido(codigo).get(0).getDataEntrega());
       

    }
}