/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.admin;

/**
 *
 * @author Victor López
 */

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.db.Conexion;

public class DepartamentoListado extends javax.swing.JInternalFrame {
    DefaultTableModel modeloDepa = null;
    public static int bandera = 0;
    ResultSet resultado = null;
    
    Conexion conex = new Conexion();
    
    /**
     * Creates new form DepartamentoListado
     */
    public DepartamentoListado() throws SQLException {
        initComponents();
        
        bandera = 1;
        
        initComponents();
        
        Object[][] data = null;
        
        String[] columnas = { "#", "Código", "Departamento", "Jefe", "Fecha de registro" };
        
        modeloDepa = new DefaultTableModel(data, columnas);
        this.JefesAreajTable.setModel(modeloDepa);
        
        conex.setRs("SELECT departamento.codigo, departamento.departamento, CONCAT(usuarios.nombres, ' ', usuarios.apellidos) AS encargado, departamento.fecha_registro FROM departamento INNER JOIN usuarios ON usuarios.usuario_id = departamento.jefe");
        
        listarDepartamentos();
    }
    
    public void listarDepartamentos() throws SQLException {
        resultado = conex.getRs();
        
        int i = 0;

        while (resultado.next()) {
            i++;
            
            Object[] newRow = {
                i, resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4), 
            };

            modeloDepa.addRow(newRow);
        }

        resultado.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JefesAreajTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        JefesAreajTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JefesAreajTable.setName("JefesAreajTable"); // NOI18N
        jScrollPane1.setViewportView(JefesAreajTable);

        jLabel1.setText("Buscar:");

        txtBuscar.setName("txtBuscar"); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        while (modeloDepa.getRowCount() != 0) modeloDepa.removeRow(0) ;

        String busqueda = txtBuscar.getText();

        String sql = "SELECT departamento.codigo, departamento.departamento, CONCAT(usuarios.nombres, ' ', usuarios.apellidos) AS encargado, departamento.fecha_registro FROM departamento INNER JOIN usuarios ON usuarios.usuario_id = departamento.jefe WHERE departamento.departamento LIKE '%" + busqueda + "%' OR CONCAT(usuarios.nombres, ' ', usuarios.apellidos) LIKE '%" + busqueda + "%'";

        conex.setRs(sql);

        try {
            listarDepartamentos();
        } catch (SQLException ex) {
            System.out.println(ex);
            // Logger.getLogger(AlumnosListado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        try {
            bandera = 0;
            
            this.dispose();
            
            conex.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex);
            // Logger.getLogger(AlumnosListado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JefesAreajTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
