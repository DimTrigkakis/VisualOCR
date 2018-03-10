/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twinmoons;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;

/**
 *
 * @author James
 */
public class TwinMoons extends javax.swing.JFrame {

          public static boolean loadingLogger = true;
          public static boolean processLogger = false;
          private Painter BobRoss;
          private Painter Escher;
          private Painter Wolfie;
          private static int dreamSize = 15;
          public static int brushSize;
          public static int selectedImage = 0;
          public static boolean eraser = false;
          private Dreamer[] dreams = new Dreamer[dreamSize];

          /**
           * Creates new form TwinMoons
           */
          public TwinMoons() {
                    initComponents();
          }

          /**
           * This method is called from within the constructor to initialize the
           * form. WARNING: Do NOT modify this code. The content of this method
           * is always regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
          private void initComponents() {

                    jPanel1 = new javax.swing.JPanel();
                    jPanel3 = new javax.swing.JPanel();
                    resetButton = new javax.swing.JButton();
                    jButton1 = new javax.swing.JButton();
                    saveButton = new javax.swing.JButton();
                    loadButton = new javax.swing.JButton();
                    nameText = new javax.swing.JTextField();
                    jPanel4 = new javax.swing.JPanel();
                    jPanel2 = new javax.swing.JPanel();
                    jPanel5 = new javax.swing.JPanel();
                    jPanel6 = new javax.swing.JPanel();
                    jComboBox1 = new javax.swing.JComboBox();
                    clearButton = new javax.swing.JButton();
                    jComboBox2 = new javax.swing.JComboBox();
                    jLabel1 = new javax.swing.JLabel();
                    jLabel2 = new javax.swing.JLabel();
                    eraserBox = new javax.swing.JCheckBox();
                    jComboBox3 = new javax.swing.JComboBox();
                    jLabel3 = new javax.swing.JLabel();
                    trainButton = new javax.swing.JButton();
                    trainField = new javax.swing.JTextField();
                    jScrollPane1 = new javax.swing.JScrollPane();
                    jTextArea1 = new javax.swing.JTextArea();

                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                    setLocationByPlatform(true);
                    setResizable(false);

                    jPanel1.setPreferredSize(new java.awt.Dimension(152, 36));

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                              jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 148, Short.MAX_VALUE)
                    );
                    jPanel1Layout.setVerticalGroup(
                              jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 148, Short.MAX_VALUE)
                    );

                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                    jPanel3.setLayout(jPanel3Layout);
                    jPanel3Layout.setHorizontalGroup(
                              jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 148, Short.MAX_VALUE)
                    );
                    jPanel3Layout.setVerticalGroup(
                              jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 148, Short.MAX_VALUE)
                    );

                    resetButton.setText("Reset");
                    resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
                              public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        resetButtonMouseClicked(evt);
                              }
                    });

                    jButton1.setText("Dream");
                    jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
                              public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        jButton1MouseClicked(evt);
                              }
                    });

                    saveButton.setText("Save");
                    saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
                              public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        saveButtonMouseClicked(evt);
                              }
                    });

                    loadButton.setText("Load");
                    loadButton.addMouseListener(new java.awt.event.MouseAdapter() {
                              public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        loadButtonMouseClicked(evt);
                              }
                    });

                    nameText.setText("Examples001");

                    jPanel4.setPreferredSize(new java.awt.Dimension(150, 150));

                    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                    jPanel4.setLayout(jPanel4Layout);
                    jPanel4Layout.setHorizontalGroup(
                              jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 150, Short.MAX_VALUE)
                    );
                    jPanel4Layout.setVerticalGroup(
                              jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 150, Short.MAX_VALUE)
                    );

                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                    jPanel2.setLayout(jPanel2Layout);
                    jPanel2Layout.setHorizontalGroup(
                              jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 148, Short.MAX_VALUE)
                    );
                    jPanel2Layout.setVerticalGroup(
                              jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 148, Short.MAX_VALUE)
                    );

                    jPanel5.setPreferredSize(new java.awt.Dimension(150, 150));

                    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                    jPanel5.setLayout(jPanel5Layout);
                    jPanel5Layout.setHorizontalGroup(
                              jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 150, Short.MAX_VALUE)
                    );
                    jPanel5Layout.setVerticalGroup(
                              jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 150, Short.MAX_VALUE)
                    );

                    jPanel6.setPreferredSize(new java.awt.Dimension(150, 150));

                    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                    jPanel6.setLayout(jPanel6Layout);
                    jPanel6Layout.setHorizontalGroup(
                              jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 150, Short.MAX_VALUE)
                    );
                    jPanel6Layout.setVerticalGroup(
                              jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGap(0, 150, Short.MAX_VALUE)
                    );

                    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        jComboBox1ActionPerformed(evt);
                              }
                    });

                    clearButton.setText("Clear");
                    clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
                              public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        clearButtonMouseClicked(evt);
                              }
                    });

                    jComboBox2.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        jComboBox2ActionPerformed(evt);
                              }
                    });

                    jLabel1.setText("Set of processed maps");

                    jLabel2.setText("Brush Size");

                    eraserBox.setText("Eraser");
                    eraserBox.addChangeListener(new javax.swing.event.ChangeListener() {
                              public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                        eraserBoxStateChanged(evt);
                              }
                    });

                    jComboBox3.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        jComboBox3ActionPerformed(evt);
                              }
                    });

                    jLabel3.setText("Image selected");

                    trainButton.setText("Train");
                    trainButton.addMouseListener(new java.awt.event.MouseAdapter() {
                              public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        trainButtonMouseClicked(evt);
                              }
                    });

                    jTextArea1.setColumns(20);
                    jTextArea1.setRows(5);
                    jScrollPane1.setViewportView(jTextArea1);

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(layout.createSequentialGroup()
                                                                      .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addGap(18, 18, 18)
                                                                      .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                      .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                      .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addGap(18, 18, 18)
                                                                      .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addGap(18, 18, 18)
                                                                      .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addGap(6, 6, 6)))
                                                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addComponent(trainField, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(trainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addGroup(layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                      .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                          .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                          .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                          .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                          .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                      .addComponent(nameText)
                                                                      .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                      .addComponent(jLabel1)
                                                                      .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(1, 1, 1))
                                                  .addGroup(layout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                      .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(eraserBox)
                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                      .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jLabel3))
                                                                      .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                            .addContainerGap())))
                    );
                    layout.setVerticalGroup(
                              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addGroup(layout.createSequentialGroup()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                      .addComponent(resetButton)
                                                                      .addComponent(saveButton))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                      .addComponent(jButton1)
                                                                      .addComponent(loadButton))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(clearButton)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                      .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel1)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(7, 7, 7)
                                                            .addComponent(eraserBox)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                      .addComponent(jLabel2)
                                                                      .addComponent(jLabel3))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                      .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                  .addGroup(layout.createSequentialGroup()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                      .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                          .addComponent(trainButton)
                                                                                          .addComponent(trainField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                      .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                      .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                      .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    pack();
          }// </editor-fold>//GEN-END:initComponents

          private void resetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseClicked
                    // TODO add your handling code here:

                    Varea.resetPressed();
                    Larea.resetPressed();

          }//GEN-LAST:event_resetButtonMouseClicked

          private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
                    // TODO add your handling code here:

                    BufferedImage a = BobRoss.getImage();
                    BufferedImage b = Escher.getImage();
                    Moons.processImage(a, b, dreams);

          }//GEN-LAST:event_jButton1MouseClicked

          private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
                    // TODO add your handling code here:

                    BufferedImage a = BobRoss.getImage();
                    BufferedImage b = Escher.getImage();
                    String filename = nameText.getText();

                    File outBobRoss = new File("art/" + filename + "a.bmp");
                    File outEscher = new File("art/" + filename + "b.bmp");

                    try {
                              ImageIO.write(a, "bmp", outBobRoss);
                              ImageIO.write(b, "bmp", outEscher);


                    } catch (IOException ex) {
                              Logger.getLogger(TwinMoons.class.getName()).log(Level.SEVERE, null, ex);
                    }

          }//GEN-LAST:event_saveButtonMouseClicked

          private void loadButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadButtonMouseClicked
                    // TODO add your handling code here:

                    String filename = nameText.getText();

                    File outBobRoss = new File("art/" + filename + "a.bmp");
                    File outEscher = new File("art/" + filename + "b.bmp");

                    try {
                              BobRoss.setImage(ImageIO.read(outBobRoss));
                              Escher.setImage(ImageIO.read(outEscher));

                    } catch (IOException ex) {
                              Logger.getLogger(TwinMoons.class.getName()).log(Level.SEVERE, null, ex);
                    }



          }//GEN-LAST:event_loadButtonMouseClicked

          private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
                    // TODO add your handling code here:

                    JComboBox cb = (JComboBox) evt.getSource();
                    String setName = (String) cb.getSelectedItem();

                    if (jPanel4.getComponentCount() > 0) {
                              jPanel4.remove(jPanel4.getComponent(0));
                              jPanel5.remove(jPanel5.getComponent(0));
                              jPanel6.remove(jPanel6.getComponent(0));

                              int number = setName.charAt(0) - '0';

                              jPanel4.add(dreams[0 + (number - 1) * 3]);
                              jPanel5.add(dreams[1 + (number - 1) * 3]);
                              jPanel6.add(dreams[2 + (number - 1) * 3]);

                              jPanel4.repaint();
                              jPanel5.repaint();
                              jPanel6.repaint();
                    }
          }//GEN-LAST:event_jComboBox1ActionPerformed

          private void clearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseClicked

                    Varea.resetPressed();
                    Larea.resetPressed();
                    BobRoss.reset();
                    Wolfie.reset();
                    Escher.reset();

                    for (int i = 0; i < dreamSize; i++) {
                              dreams[i].reset();
                    }
                    jPanel4.repaint();
                    jPanel5.repaint();
                    jPanel6.repaint();
          }//GEN-LAST:event_clearButtonMouseClicked

          private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
                    // TODO add your handling code here:

                    JComboBox cb = (JComboBox) evt.getSource();
                    String setName = (String) cb.getSelectedItem();
                    int number = setName.charAt(0) - '0';

                    brushSize = number;

          }//GEN-LAST:event_jComboBox2ActionPerformed

          private void eraserBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_eraserBoxStateChanged
                    // TODO add your handling code here:
                    
                    eraser = eraserBox.isSelected();
                    
          }//GEN-LAST:event_eraserBoxStateChanged

          private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
                    // TODO add your handling code here:
                    
                    
                    JComboBox cb = (JComboBox) evt.getSource();
                    String setName = (String) cb.getSelectedItem();
                    switch(setName)
                    {
                              case "First": selectedImage = 0;
                                        break;
                              case "Second":selectedImage = 1;
                                        break;
                              default:
                    }
                    
          }//GEN-LAST:event_jComboBox3ActionPerformed

          private void trainButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trainButtonMouseClicked

                    
                    BufferedImage a = Wolfie.getImage();
                    Moons.trainImage(a, dreams);
                    
          }//GEN-LAST:event_trainButtonMouseClicked

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
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                              java.util.logging.Logger.getLogger(TwinMoons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    //</editor-fold>

                    /* Create and display the form */
                    java.awt.EventQueue.invokeLater(new Runnable() {
                              @Override
                              public void run() {
                                        TwinMoons twinMoons = new TwinMoons();
                                        twinMoons.setVisible(true);
                                        twinMoons.setLocation(500, 250);

                                        for (int i = 1; i <= dreamSize; i++) {
                                                  if (i % 3 == 0) {
                                                            twinMoons.jComboBox1.addItem(Integer.toString(i / 3));
                                                  }
                                        }
                                        for (int i = 1; i < 10; i++) {
                                                  twinMoons.jComboBox2.addItem(Integer.toString(i));
                                        }                                        
                                        twinMoons.jComboBox2.setSelectedIndex(4);
                                        
                                        twinMoons.jComboBox3.addItem("First");
                                        twinMoons.jComboBox3.addItem("Second");
                                        twinMoons.jComboBox3.setSelectedIndex(0);

                                        twinMoons.BobRoss = new Painter();
                                        twinMoons.jPanel1.add(twinMoons.BobRoss);
                                        twinMoons.Escher = new Painter();
                                        twinMoons.jPanel2.add(twinMoons.Escher);
                                        twinMoons.Wolfie = new Painter();
                                        twinMoons.jPanel3.add(twinMoons.Wolfie);


                                        for (int i = 0; i < TwinMoons.dreamSize; i++) {
                                                  twinMoons.dreams[i] = new Dreamer();
                                        }

                                        twinMoons.jPanel4.add(twinMoons.dreams[0]);
                                        twinMoons.jPanel5.add(twinMoons.dreams[1]);
                                        twinMoons.jPanel6.add(twinMoons.dreams[2]);

                              }
                    });
          }
          // Variables declaration - do not modify//GEN-BEGIN:variables
          private javax.swing.JButton clearButton;
          private javax.swing.JCheckBox eraserBox;
          private javax.swing.JButton jButton1;
          private javax.swing.JComboBox jComboBox1;
          private javax.swing.JComboBox jComboBox2;
          private javax.swing.JComboBox jComboBox3;
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JLabel jLabel3;
          private javax.swing.JPanel jPanel1;
          private javax.swing.JPanel jPanel2;
          private javax.swing.JPanel jPanel3;
          private javax.swing.JPanel jPanel4;
          private javax.swing.JPanel jPanel5;
          private javax.swing.JPanel jPanel6;
          private javax.swing.JScrollPane jScrollPane1;
          private javax.swing.JTextArea jTextArea1;
          private javax.swing.JButton loadButton;
          private javax.swing.JTextField nameText;
          private javax.swing.JButton resetButton;
          private javax.swing.JButton saveButton;
          private javax.swing.JButton trainButton;
          private javax.swing.JTextField trainField;
          // End of variables declaration//GEN-END:variables
}