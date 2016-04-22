package com.projectvalis.altk.jbox2d.lab;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.jbox2d.testbed.framework.TestList;
import org.jbox2d.testbed.framework.TestbedController;
import org.jbox2d.testbed.framework.TestbedController.MouseBehavior;
import org.jbox2d.testbed.framework.TestbedController.UpdateBehavior;
import org.jbox2d.testbed.framework.TestbedErrorHandler;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.j2d.DebugDrawJ2D;
import org.jbox2d.testbed.framework.j2d.TestPanelJ2D;
import org.jbox2d.testbed.framework.j2d.TestbedSidePanel;

/*******************************************************************************
 * Copyright (c) 2013, Daniel Murphy
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/



/**
 * The entry point for the testbed application
 * 
 * @author Daniel Murphy
 */
public class TestbedRunner {
  // private static final Logger log = LoggerFactory.getLogger(TestbedMain.class);

  public void bootstrapTestBedRunner() {
    // try {
    // UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    // } catch (Exception e) {
    // log.warn("Could not set the look and feel to nimbus.  "
    // + "Hopefully you're on a mac so the window isn't ugly as crap.");
    // }
    TestbedModel model = new TestbedModel();
    final TestbedController controller =
        new TestbedController(model, UpdateBehavior.UPDATE_CALLED, MouseBehavior.NORMAL,
            new TestbedErrorHandler() {
              @Override
              public void serializationError(Exception e, String message) {
                JOptionPane.showMessageDialog(null, message, "Serialization Error",
                    JOptionPane.ERROR_MESSAGE);
              }
            });
    TestPanelJ2D panel = new TestPanelJ2D(model, controller);
    model.setPanel(panel);
    model.setDebugDraw(new DebugDrawJ2D(panel, true));
    model.addTest(new MJWTest2());
    TestList.populateModel(model);

    JFrame testbed = new JFrame();
    testbed.setTitle("JBox2D Testbed");
    testbed.setLayout(new BorderLayout());
    TestbedSidePanel side = new TestbedSidePanel(model, controller);
    testbed.add((Component) panel, "Center");
    testbed.add(new JScrollPane(side), "East");
    testbed.pack();
    testbed.setVisible(true);
    testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    System.out.println(System.getProperty("java.home"));

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        controller.playTest(0);
        controller.start();
      }
    });
  }
}