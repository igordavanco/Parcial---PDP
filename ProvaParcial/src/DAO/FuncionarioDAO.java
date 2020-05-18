/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Departamento;
import Classes.Funcionario;
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
public class FuncionarioDAO {
    
    public void create(Funcionario f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO funcionarios (dep_id, nome, cpf) VALUES (?, ?, ?)");
            stmt.setInt(1, f.getDepartamento_id());
            stmt.setString(2, f.getNome());
            stmt.setString(3, f.getCpf());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);
        }finally{
            ConnectionFactory.closeConnection();
        }
    }
    
    public List<Funcionario> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT *FROM funcionarios");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario Funcionario = new Funcionario();
                
                Funcionario.setCodigo(rs.getInt("funcionario_id"));
                Funcionario.setDepartamento_id(rs.getInt("dep_id"));
                Funcionario.setNome(rs.getString("nome"));
                Funcionario.setCpf(rs.getString("cpf"));
         
                funcionarios.add(Funcionario);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection();
        }
        
        return funcionarios;
    }
    
    public void update(Funcionario f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE funcionarios SET dep_id = ?, nome = ?, cpf = ? WHERE funcionario_id = ?");
            stmt.setInt(1, f.getDepartamento_id());
            stmt.setString(2, f.getNome());
            stmt.setString(3, f.getCpf());
            stmt.setInt(4, f.getCodigo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection();
        }
    }
    
    public void delete(Funcionario f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM funcionarios WHERE funcionario_id = ?");
            stmt.setInt(1, f.getCodigo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);
        }finally{
            ConnectionFactory.closeConnection();
        }
    }
    
}
