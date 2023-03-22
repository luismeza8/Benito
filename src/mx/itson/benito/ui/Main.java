/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mx.itson.benito.ui;

import javax.swing.table.DefaultTableModel;
import mx.itson.benito.entidades.Orden;
import mx.itson.benito.persistencia.OrdenDAO;

/**
 *
 * @author lm
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        this.modelTblOrdenes = (DefaultTableModel) tblOrdenes.getModel();
        llenarOrdenes();
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
        tblOrdenes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAgregarOrden = new javax.swing.JButton();
        btnEliminarOrden = new javax.swing.JButton();
        btnActualizarOrden = new javax.swing.JButton();
        btnVerDetalleOrden = new javax.swing.JButton();
        btnVerTodosLosArticulos = new javax.swing.JButton();
        txtTotalArticulos = new javax.swing.JLabel();
        txtTotalProveedores = new javax.swing.JLabel();
        btnVerTodosLosProveedores = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblOrdenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Folio", "Total", "Subtotal", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdenesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrdenes);
        if (tblOrdenes.getColumnModel().getColumnCount() > 0) {
            tblOrdenes.getColumnModel().getColumn(1).setResizable(false);
            tblOrdenes.getColumnModel().getColumn(2).setResizable(false);
            tblOrdenes.getColumnModel().getColumn(3).setResizable(false);
            tblOrdenes.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 700, 260));

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LLANTERA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 70));

        jLabel2.setText("Ordenes:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

        btnAgregarOrden.setText("Agregar orden");
        btnAgregarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarOrdenActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregarOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

        btnEliminarOrden.setText("Eliminar orden");
        getContentPane().add(btnEliminarOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, -1, -1));

        btnActualizarOrden.setText("Actualizar orden");
        getContentPane().add(btnActualizarOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, -1, -1));

        btnVerDetalleOrden.setText("Ver detalle de la orden");
        getContentPane().add(btnVerDetalleOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, -1, -1));

        btnVerTodosLosArticulos.setText("Ver todos los articulos");
        btnVerTodosLosArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodosLosArticulosActionPerformed(evt);
            }
        });
        getContentPane().add(btnVerTodosLosArticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        txtTotalArticulos.setText("Total de articulos: ");
        getContentPane().add(txtTotalArticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        txtTotalProveedores.setText("Total de proveedores: ");
        getContentPane().add(txtTotalProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, -1));

        btnVerTodosLosProveedores.setText("Ver todos los proveedores");
        btnVerTodosLosProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodosLosProveedoresActionPerformed(evt);
            }
        });
        getContentPane().add(btnVerTodosLosProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, -1, -1));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 489, 10, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblOrdenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdenesMouseClicked
        int filaSeleccionada = tblOrdenes.getSelectedRow();
        String id = modelTblOrdenes.getValueAt(filaSeleccionada, 0).toString();
        
        DetalleOrden j = new DetalleOrden(this, true, OrdenDAO.obtenerPorId(Integer.parseInt(id)));
        j.setVisible(true);
    }//GEN-LAST:event_tblOrdenesMouseClicked

    private void btnVerTodosLosArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosLosArticulosActionPerformed
        Articulos articulos = new Articulos(this, true);
        articulos.setVisible(true);
    }//GEN-LAST:event_btnVerTodosLosArticulosActionPerformed

    private void btnVerTodosLosProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosLosProveedoresActionPerformed
        Proveedores proveedores = new Proveedores(this, true);
        proveedores.setVisible(true);
    }//GEN-LAST:event_btnVerTodosLosProveedoresActionPerformed

    private void btnAgregarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarOrdenActionPerformed
        FormularioOrden formularioOrden = new FormularioOrden(this, true);
        formularioOrden.setVisible(true);
    }//GEN-LAST:event_btnAgregarOrdenActionPerformed

    DefaultTableModel modelTblOrdenes;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    public void llenarOrdenes() {
        
        for (Orden o : OrdenDAO.obtenerTodos()) {
            modelTblOrdenes.addRow(
                    new Object[] {
                        o.getId(),
                        o.getFolio(),
                        "$" + o.getTotal(),
                        "$" + o.getSubtotal(),
                        o.getFecha()
                    }
            );
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarOrden;
    private javax.swing.JButton btnAgregarOrden;
    private javax.swing.JButton btnEliminarOrden;
    private javax.swing.JButton btnVerDetalleOrden;
    private javax.swing.JButton btnVerTodosLosArticulos;
    private javax.swing.JButton btnVerTodosLosProveedores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblOrdenes;
    private javax.swing.JLabel txtTotalArticulos;
    private javax.swing.JLabel txtTotalProveedores;
    // End of variables declaration//GEN-END:variables
}
