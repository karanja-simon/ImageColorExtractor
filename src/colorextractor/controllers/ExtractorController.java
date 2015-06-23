/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colorextractor.controllers;

import colorextractor.ui.JFrameMainUI;
import colorextractor.utils.TweakUI;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author RESEARCH2
 */
public class ExtractorController {

    private final JFrameMainUI view;

    public ExtractorController(JFrameMainUI myview) {
        this.view = myview;
        view.addBtnUploadActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage img = getImage();
                    if (img != null) {
                        view.setImage(img);
                        if (img.getWidth() > 700 && img.getHeight() > 500) {
                            view.setCanvasSize(view.getImage().getWidth(), view.getImage().getHeight());
                        }
                        view.repaint();
                        view.revalidate();
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error reading image file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        view.addCanvasMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TweakUI.onMousePressed(view.getCanvas(), e);
            }
        });
        view.addCanvasMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                TweakUI.onMouseDragged(view.getCanvas(), e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point point = new Point(a.getLocation());
                SwingUtilities.convertPointFromScreen(point, e.getComponent());
                int x = (int) point.getX();
                int y = (int) point.getY();
                int imgW = view.getImage().getWidth();
                int imgH = view.getImage().getHeight();
                view.setLocX(x);
                view.setLocY(y);
                if (x < imgW && y < imgH) {
                    int clr = view.getImage().getRGB(x, y);
                    int red = (clr & 0x00ff0000) >> 16;
                    int green = (clr & 0x0000ff00) >> 8;
                    int blue = clr & 0x000000ff;
                    view.setR(red);
                    view.setG(green);
                    view.setB(blue);
                    view.setPreviewColor(red, green, blue);
                    String hex = String.format("#%02X%02X%02X", red, green, blue);
                    view.setHex(hex);
                }

            }
        });
        view.addAppMenuBtnMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton jbtn = (JButton) e.getComponent();
                view.showPopup(e);
                jbtn.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private BufferedImage getImage() throws IOException {
        BufferedImage image = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "jpg", "gif", "png", "bmp");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            image = ImageIO.read(f);
            view.setImageUploadPath(f.getAbsolutePath());
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Cancelled. No Image Selected!", "Cancelled", JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

}
