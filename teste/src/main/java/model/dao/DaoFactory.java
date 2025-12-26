/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author Malu Sanches
 */
public class DaoFactory {

    public static FilmeDao novoFilmeDao() throws Exception {
        return new FilmeDao();
    }

    public static SalaDao novoSalaDao() throws Exception {
        return new SalaDao();
    }
    
    public static SessaoDao novoSessaoDao() throws Exception{
        return new SessaoDao();
    }
}
