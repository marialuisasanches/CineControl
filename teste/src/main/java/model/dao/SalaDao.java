/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Sala;

/**
 *
 * @author Pedro
 */
public class SalaDao implements InterfaceDao<Sala> {

    private Connection conn;

    public SalaDao() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void inserir(Sala sala) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Sala(numeroSala, capacidade) VALUES(?, ?)");
            ps.setInt(1, sala.getNumeroSala());
            ps.setInt(2, sala.getCapacidade());
            ps.execute();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<Sala> listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM Sala");
            rs = ps.executeQuery();
            List<Sala> lista = new ArrayList();
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setId(rs.getInt("id"));
                sala.setNumeroSala(rs.getInt("numeroSala"));
                sala.setCapacidade(rs.getInt("capacidade"));

                lista.add(sala);
            }
            return lista;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    @Override
    public void editar(Sala sala) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE Sala SET numeroSala = ?, capacidade = ? WHERE id = ? ");
            ps.setInt(1, sala.getNumeroSala());
            ps.setInt(2, sala.getCapacidade());
            ps.setInt(3, sala.getId());

            ps.execute();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void excluir(Sala sala) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM Sala WHERE id = ?");
            ps.setInt(1, sala.getId());
            ps.execute();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Sala pesquisarPorId(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM Sala WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Sala sala = new Sala();
                sala.setId(rs.getInt("id"));
                sala.setNumeroSala(rs.getInt("numeroSala"));
                sala.setCapacidade(rs.getInt("capacidade"));
                return sala;
            } else {
                return null;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

}
