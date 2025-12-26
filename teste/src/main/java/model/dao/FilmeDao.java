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
import model.Filme;

/**
 *
 * @author Pedro
 */
public class FilmeDao implements InterfaceDao<Filme> {

    private Connection conn;

    public FilmeDao() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void inserir(Filme filme) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Filme(titulo, duracao, genero) VALUES(?, ?, ?)");
            ps.setString(1, filme.getTitulo());
            ps.setInt(2, filme.getDuracao());
            ps.setString(3, filme.getGenero());

            ps.execute();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<Filme> listar() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM Filme");
            rs = ps.executeQuery();
            List<Filme> lista = new ArrayList();
            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setGenero(rs.getString("genero"));
                lista.add(filme);
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
    public void editar(Filme filme) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE Filme SET titulo = ?, duracao = ?, genero = ? WHERE id = ? ");
            ps.setString(1, filme.getTitulo());
            ps.setInt(2, filme.getDuracao());
            ps.setString(3, filme.getGenero());
            ps.setInt(4, filme.getId());

            ps.execute();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void excluir(Filme filme) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM Filme WHERE id = ?");
            ps.setInt(1, filme.getId());
            ps.execute();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Filme pesquisarPorId(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM Filme WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setGenero(rs.getString("genero"));
                return filme;
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
