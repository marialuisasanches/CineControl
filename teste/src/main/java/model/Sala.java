/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Pedro
 */
public class Sala {
    private int id;
    private int numeroSala;
    private int capacidade;
    
    public Sala(){}
    
    public Sala(int numeroSala, int capacidade){
        this.numeroSala = numeroSala;
        this.capacidade = capacidade;
    }
    
    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    
    public int getNumeroSala(){return numeroSala;}
    public void setNumeroSala(int numeroSala){this.numeroSala = numeroSala;}
    
    public int getCapacidade(){return capacidade;}
    public void setCapacidade(int capacidade){this.capacidade = capacidade;}
    
        @Override
    public String toString() {
        return id + " | " + numeroSala + " | " + capacidade;
    }
}
