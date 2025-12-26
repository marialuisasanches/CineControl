/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Pedro
 */
public class Sessao {

    private int id;
    private Filme filme;
    private Sala sala;

    public Sessao() {
    }

    public Sessao(Filme filme, Sala sala) {
        this.filme = filme;
        this.sala = sala;
    }

    public Filme getFilme() {
        return filme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getFilmeNome() {
        return filme.getTitulo();
    }

    public int getNumeroSala() {
        return sala.getNumeroSala();
    }

    @Override
    public String toString() {
        return id + " | " + filme + " | " + sala;
    }
}
