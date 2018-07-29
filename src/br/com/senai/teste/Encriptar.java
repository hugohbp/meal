/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.teste;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Simulado
 */
public class Encriptar {

    public static void main(String[] args) throws Exception {
        
         try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] mensagem = "minha mensagem original fsdfsdfdsfsdfsdfsdfds".getBytes();
 
            // Usando chave de 128-bits (16 bytes)
            byte[] chave = "chave de 16bytes".getBytes();
            System.out.println("Tamanho da chave: " + chave.length);
 
            // Encriptando...
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"));
            byte[] encrypted = cipher.doFinal(mensagem);
            
            
 
            // Decriptando...
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, "AES"));
            byte[] decrypted = cipher.doFinal(encrypted);
 
            System.out.println(new String(decrypted));
        } catch (Exception e) {
           e.printStackTrace();
        }

       

    }

}
