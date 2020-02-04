/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bashlearning;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author enriq
 */
public class Autocompletar extends javax.swing.JFrame {
    boolean bol = true;
    String identificadores="";
    String errores="";
    String[] reservadas = {"CLEAR", "MKDIR", "LS", "CAT","VIM","CD","TOUCH","MV"};
    private  TextAutoCompleter ac;
    public Autocompletar() {
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        TexFieldComando = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        botonEjecutar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaComandos = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(55, 71, 79));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(126, 199, 50));
        jLabel2.setText("Username@Ubuntu:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        TexFieldComando.setBackground(new java.awt.Color(48, 10, 36));
        TexFieldComando.setForeground(new java.awt.Color(255, 255, 255));
        TexFieldComando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TexFieldComandoActionPerformed(evt);
            }
        });
        TexFieldComando.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TexFieldComandoKeyPressed(evt);
            }
        });
        getContentPane().add(TexFieldComando, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 500, -1));

        jLabel3.setForeground(new java.awt.Color(126, 199, 50));
        jLabel3.setText("Comandos ejecutados:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        botonEjecutar.setText("Ejecutar");
        botonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEjecutarActionPerformed(evt);
            }
        });
        getContentPane().add(botonEjecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 17, -1, -1));

        TextAreaComandos.setBackground(new java.awt.Color(48, 10, 36));
        TextAreaComandos.setColumns(20);
        TextAreaComandos.setForeground(new java.awt.Color(255, 255, 255));
        TextAreaComandos.setRows(5);
        jScrollPane1.setViewportView(TextAreaComandos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 730, 270));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bashlearning/fondo.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 770, 390));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TexFieldComandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TexFieldComandoActionPerformed
     
    }//GEN-LAST:event_TexFieldComandoActionPerformed

    private void botonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarActionPerformed
        errores="";identificadores="";
        String reservadasEntrada ="";
        Ejecutor(TexFieldComando.getText());
        String texto = TexFieldComando.getText();
        String[] cadenaEntrada = texto.split(" ");
        
        
            
        for (int i = 0; i < cadenaEntrada.length; i++) {
            
            for (int j = 0; j < reservadas.length; j++) {
                if(cadenaEntrada[i].equals(reservadas[j])){
                    reservadasEntrada=reservadasEntrada+cadenaEntrada[i]+" ";
                    bol=false;
                }
            }
            if(bol==true){
                Comprobar(cadenaEntrada[i]);
                bol=true;
            }
            bol=true;
            
            
            
            
        }
        
        
        
        System.out.println("Identificadores: ["+identificadores+"]");
        System.out.println("Reservadas: ["+reservadasEntrada+"]");
        System.out.println("Errores: ["+errores+"]");
        
        
    }//GEN-LAST:event_botonEjecutarActionPerformed

    private void TexFieldComandoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TexFieldComandoKeyPressed
        ac=new TextAutoCompleter(TexFieldComando);
        ac.addItem("MKDIR <nombre de la carpeta>");
        ac.addItem("LS <vacio o nombre de carpeta>");
        ac.addItem("CAT <nombre de archivo con extension>");
        ac.addItem("VIM <nombre de archivo>");
        ac.addItem("CD <vacio o nombre de carpeta>");
        ac.addItem("MV <nombre de archivo> <lugar de destino>");
        ac.addItem("CLEAR");
        ac.addItem("TOUCH <nombre del archivo con/sin extension>");
    }//GEN-LAST:event_TexFieldComandoKeyPressed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Autocompletar().setVisible(true);
            }
        });
    }
    
    
    
    public void Ejecutor(String comando){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("C:\\Program Files\\Git\\bin\\bash.exe", "-c", comando);

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                TextAreaComandos.setText(TextAreaComandos.getText()+line+"\n");
            }
            if(comando.equals("clear")||comando.equals("CLEAR")){
            TextAreaComandos.setText("");
            }
            int exitCode = process.waitFor();
            TextAreaComandos.setText(TextAreaComandos.getText()+"Exited with error code : " + exitCode+"\n");
            

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void Comprobar(String palabra){
        Pattern pat = Pattern.compile("[-_a-zA-Z0-9][a-zA-Z0-9]+");
        Matcher mat = pat.matcher(palabra);
        
        if(mat.matches()){
            identificadores=identificadores+palabra+" ";
        }else{
            errores=errores+palabra+" ";
        }
            
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField TexFieldComando;
    public javax.swing.JTextArea TextAreaComandos;
    public javax.swing.JButton botonEjecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}


