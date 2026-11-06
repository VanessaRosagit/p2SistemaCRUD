package com.vanessarosa.p2sistemacrud;

import java.sql.SQLException; //Tratar as exceções no banco de dados


public class Usuario {
    //Criação dos atributos privados da classe
    private String usuario;
    private String nome;
    private String senha;

    //Atributo que armazenará o retorno do banco de dados
    private boolean resultUsuario;

    //Criação dos getters e setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //Método de verificação da autenticidade do usuário
    public boolean verificaUsuario(String usuario, String senha){
        //Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();

        try{
            //Abro a conexão com o banco de dados
            banco.abrirConexao();

            //Criando parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            //Executando a consulta no banco de dados
            banco.resultset =
                    banco.stmt.executeQuery("SELECT * FROM usuario "
                                + "WHERE usuario = '" + usuario + "'"
                                + " AND senha = md5('" + senha + "')");

            //Verificando se existe retorno de dados no banco
            if (banco.resultset.next()){
                //Caso tenha
                resultUsuario = true;
            }else{
                //Caso não tenha
                resultUsuario = false;
            }

            banco.fecharConexao(); // fecha nossa conexão com o banco de dados

        }catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;
    }
    
    //Método de verificação do usuário, estamos aqui fazendo uma
//sobrecarga de método
public boolean verificaUsuario(String usuario){
    //Fazer a instância da conexão com o banco de dados
    Conexao banco = new Conexao();

    try{
        //Abro a conexão com o banco de dados
        banco.abrirConexao();

        //Criando parâmetro de retorno
        banco.stmt= banco.con.createStatement();

        //Executando a consulta no banco de dados
        banco.resultset =
                banco.stmt.executeQuery("SELECT * FROM usuario "
                                + "WHERE usuario = '" + usuario + "'");

        //Verificando se existe retorno de dados no banco
        if (banco.resultset.next()){
            //Caso tenha
            resultUsuario = true;
        }else{
            //Caso não tenha
            resultUsuario = false;
        }

        banco.fecharConexao(); // fecha nossa conexão com o banco de dados

    }catch (SQLException ec) {
        System.out.println("Erro ao consultar usuário " + ec.getMessage());
    }

    return resultUsuario;
}


//Método para cadastro de usuário em nosso sistema
public boolean cadastraUsuario(String nome, String usuario, String senha){
    //Fazer a instância da conexão com o banco de dados
    Conexao banco = new Conexao();
boolean resultCadastro = false;
    try{
        //Abertura da conexão com o banco de dados
        banco.abrirConexao();

        //Criando parâmetro de retorno
        banco.stmt = banco.con.createStatement();

        //Executando a inserção dos dados
        banco.stmt.execute("INSERT INTO usuario (nome, usuario, senha)"
                    + " VALUES ('" + nome + "','" + usuario + "', md5('"
                    + senha + "'))");

        resultCadastro = true;

    }catch(SQLException ec){
        System.out.println("Erro ao inserir o usuário " + ec.getMessage());
        resultCadastro = false;
    }

    return resultCadastro;
}
}