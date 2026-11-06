/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanessarosa.p2sistemacrud;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Vanessa
 */
public class TelaLogin extends JFrame{
    private final JPanel panelTela;
    private final JTextField txtUsuario;
    private final JPasswordField pswSenha;
    
    // método construtor

    public TelaLogin() {
        setLocationRelativeTo(null);
        setResizable(false);
             //coloca título na caixa JFrame
        setTitle("Login - Fatec");

        //Quando clicado no X eu encerro todo o programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //define posicionamento e tamanho
        //          x    y  width height
        setBounds(500, 200, 426, 212);

        //Crio um objeto JPanel e atribuo ele a variável tela
        panelTela = new JPanel();

        //Define a cor de fundo do JPanel (tela)
        panelTela.setBackground(SystemColor.gray);
        setContentPane(panelTela);

        //Vou utilizar o meu panel sem utilizar o padrão
        panelTela.setLayout(null);

        //Adicionando elementos na tela:
        // Criando um objeto do tipo JLabel e atribuindo o valor ao atributo
        JLabel lblIdentificacao = new JLabel("IDENTIFICAÇÃO");

        //Localização na tela
        lblIdentificacao.setBounds(144, 0, 160, 39);

        //Definindo a Fonte
        lblIdentificacao.setFont(new Font("Arial", 3, 19));

        //Adicionando o meu label ao meu Panel
        panelTela.add(lblIdentificacao);

        //Identificação e Posicionamento dos labels
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(24, 65, 70, 15);
        panelTela.add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(24, 92, 70, 15);
        panelTela.add(lblSenha);

        //Identificação e Posicionamento dos texts fields
        txtUsuario = new JTextField();
        txtUsuario.setBounds(112, 63, 219, 19);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);

        pswSenha = new JPasswordField();
        pswSenha.setBounds(112, 90, 219, 19);
        panelTela.add(pswSenha);

        //Identificação e Posicionamento dos botões
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(200, 136, 117, 25);
        panelTela.add(btnEntrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 136, 117, 25);
        panelTela.add(btnCadastrar);
        
        
        
        btnCadastrar.addActionListener(((e) -> {
            System.out.println("HELLO");
        }));
    
    }
    
    public void abreTela(){
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
       
    
    
    
    
    
    }
    
    
    
}
