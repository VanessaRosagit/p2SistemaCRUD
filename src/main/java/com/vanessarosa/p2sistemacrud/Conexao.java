package com.vanessarosa.p2sistemacrud;


 
/*
 Bibliotecas para trabalharem com as funções
 de banco de dados
*/
import java.sql.Connection;  
import java.sql.DriverManager; 
import java.sql.ResultSet;    
import java.sql.SQLException;
import java.sql.Statement;    
/**
 *
 * @author Vanessa
 */
public class Conexao {
    //Atributos de conexão ligados as bibliotecas importadas
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;

    //Atributos de conexãolocalhost:3307/
    private final String servidor = "jdbc:mysql://localhost:3307/bd_login";
    
    
                                    
    private final String usuario = "root"; 
    private final String senha = "";      
    private final String driver = "com.mysql.cj.jdbc.Driver"; 

    //Método de abertura da conexão com banco de dados
    public Connection abrirConexao() {
        try {
            Class.forName(driver); //Driver de utilização
            //Atributos de conexão
            con = DriverManager.getConnection(servidor, usuario, senha);
            stmt = con.createStatement(); //Fazendo conexão com o banco de dados
            
            System.out.println("Conexão aberta com sucesso");
        } catch (ClassNotFoundException | SQLException e) {
           
            System.out.println("Erro ao acessar banco de dados, verifique! " + e.getMessage());
        }
        return con; //Retorna a conexão
    }

    //Método de fechamento da conexão com o banco de dados
    public void fecharConexao() {
        try {
            con.close(); //Fechamento da conexão com o banco de dados
         
            System.out.println("Conexão finalizada com sucesso");
        } catch (SQLException e) {
          
            System.out.println("Erro ao encerrar conexão " + e.getMessage());
        }
    }
}
