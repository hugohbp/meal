/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author Simulado
 */
public class PlayAudio {

    private String filename;
    private Player player;

    public PlayAudio(String filename) {
        this.filename = filename;

    }

    public void close() {
        if (player != null) {
            player.close();
        }
    }

    public void play() {

        try {

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("audio/" + filename));
            player = new Player(bis);

        } catch (Exception ex) {
//            System.out.println("Problema com o arquivo em: " + filename);
            System.out.println("Erro:" + ex.getMessage());

        }

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println("Erro ao executar");
                }
            }

        }.start();
    }

}
