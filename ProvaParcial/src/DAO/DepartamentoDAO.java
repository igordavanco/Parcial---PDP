/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Departamento;
import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author igor_
 */
public class DepartamentoDAO {
    
    public void create(Departamento d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO departamentos (nome, descricao) VALUES (?, ?)");
            stmt.setString(1, d.getNome());
            stmt.setString(2, d.getDescricao());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);
        }finally{
            ConnectionFactory.closeConnection();
        }
    }
    
    public List<Departamento> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Departamento> departamentos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM departamentos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Departamento departamento = new Departamento();
                
                departamento.setCodigo(rs.getInt("departamento_id"));
                departamento.setNome(rs.getString("nome"));
                departamento.setDescricao(rs.getString("descricao"));
                departamentos.add(departamento);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection();
        }
        
        return departamentos;
    }
    
    public void update(Departamento d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE departamentos SET nome = ?, descricao = ? WHERE departamento_id = ?");
            stmt.setString(1, d.getNome());
            stmt.setString(2, d.getDescricao());
            stmt.setInt(3, d.getCodigo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection();
        }
    }
    
    public void delete(Departamento d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM departamentos WHERE departamento_id = ?");
            stmt.setInt(1, d.getCodigo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!" + ex);
        }finally{
            ConnectionFactory.closeConnection();
        }
    }
}
