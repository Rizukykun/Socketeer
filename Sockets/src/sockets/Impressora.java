/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fábio
 */
public class Impressora extends Thread{
    private boolean ativo;
    private int identificacao;
    
    public Impressora(int id){
        this.identificacao = id;
        this.ativo = true;
    }
    
    @Override
    public void run(){
        while(ativo){
            try {
                Fila fila = Fila.obtemInstancia();
                if (!fila.filaVazia()){
                    String texto = fila.desenfileirar();
                }
                System.out.println("Impressora " + identificacao + " está imprimindo.");
                //Enviar pra impressão
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Impressora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void fechar(){
        ativo = false;
    }
    
    public int getIdentificacao(){
        return identificacao;
    }
}
