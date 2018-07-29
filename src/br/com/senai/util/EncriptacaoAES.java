/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.axis.encoding.Base64;

/**
 *
 * @author Simulado
 */
public class EncriptacaoAES {

    Cipher cipher;
    byte[] chave;

    public EncriptacaoAES() {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(EncriptacaoAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        chave = "chave de 16bytes".getBytes();
    }

    public String encriptar(String value) {

        try {
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"));
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encode(encrypted);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            return null;
        }

    }

    public String desencriptar(String cifra) {

        try {
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, "AES"));
            byte[] decrypted = cipher.doFinal(Base64.decode(cifra));
            return new String(decrypted);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(EncriptacaoAES.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
