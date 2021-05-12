/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrador
 */
public class Sockets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Gerenciamento de impressão iniciado.");
            
            List<Impressora> listaImpressoras = Collections.synchronizedList(new ArrayList<>());
            Servidor servidor = new Servidor();
            servidor.start();
            System.out.println("Receptor de conexões aberto na porta: " + servidor.getPort());
            
            for(int i = 0; i < 5; i++){
                Impressora impressora = new Impressora(i + 1);
                impressora.start();
                listaImpressoras.add(impressora);
                System.out.println("Conexão com impressora" + impressora.getIdentificacao() + " iniciada.");
            }
            
            Scanner leitor = new Scanner(System.in);           
            String resposta;
            
            do{
                System.out.println("\n\nEsolha uma opção:\n"
                                 + "1 - Listar Clientes"
                                 + "2 - Fechar");
                resposta = leitor.nextLine();
                
                if(resposta.equals("1")){
                    servidor.listarClientes();
                }
                else if (!resposta.equals(2)){
                    System.out.print("Digite 1 ou 2");
                }
                
            }while (!resposta.equals("2"));
            
            //Fechando a aplicação
            System.out.println("Fechando receptor de conexões");
            servidor.fechar();
            servidor.join(5000);
            servidor.interrupt();
            
            for(Impressora i : listaImpressoras){
                System.out.println("Fechando conexão com a impressora " + i.getIdentificacao());
                i.fechar();
            }
            
        } catch (Exception ex) {
            System.out.println("Ocorreu uma excessão!");
            System.out.println(ex.getMessage());
        }
    }

}
