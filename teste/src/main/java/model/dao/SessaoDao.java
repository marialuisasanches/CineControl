/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Sessao;

/**
 *
 * @author Pedro
 */
public class SessaoDao implements InterfaceDao<Sessao> {

    private Connection conn;

    public SessaoDao() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void inserir(Sessao sessao) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Sessao (filme, sala) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, sessao.getFilme().getId());
            ps.setInt(2, sessao.getSala().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                sessao.setId(rs.getInt(1));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<Sessao> listar() throws Exception {
        List<Sessao> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM Sessao");
            rs = ps.executeQuery();

            FilmeDao filmeDao = DaoFactory.novoFilmeDao();
            SalaDao salaDao = DaoFactory.novoSalaDao();

            while (rs.next()) {
                Sessao sessao = new Sessao();
                sessao.setId(rs.getInt("id"));

                int idFilme = rs.getInt("filme");
                int idSala = rs.getInt("sala");

                Filme filme = filmeDao.pesquisarPorId(idFilme);
                Sala sala = salaDao.pesquisarPorId(idSala);

                if (filme != null && sala != null) {
                    sessao.setFilme(filme);
                    sessao.setSala(sala);
                    lista.add(sessao);
                }
            }

        } catch (Exception e) {
            System.out.println("ERRO: "+ e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return lista;
    }

    @Override
    public void editar(Sessao sessao) throws Exception {
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("UPDATE Sessao SET filme = ?, sala = ? WHERE id = ?");
            ps.setInt(1, sessao.getFilme().getId());
            ps.setInt(2, sessao.getSala().getId());
            ps.setInt(3, sessao.getId());

            ps.execute();
        } catch (Exception e) {
            System.out.println("ERRO: "+e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void excluir(Sessao sessao) throws Exception{
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("DELETE FROM Sessao WHERE id = ?");
            ps.setInt(1, sessao.getId());
            ps.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Sessao pesquisarPorId(int id) throws Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM Sessao WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {

                Sessao sessao = new Sessao();
                sessao.setId(rs.getInt("id"));

                FilmeDao filmeDao = DaoFactory.novoFilmeDao();
                Filme filme = filmeDao.pesquisarPorId(rs.getInt("filme"));
                sessao.setFilme(filme);

                SalaDao salaDao = DaoFactory.novoSalaDao();
                Sala sala = salaDao.pesquisarPorId(rs.getInt("sala"));
                sessao.setSala(sala);

                return sessao;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }
}
