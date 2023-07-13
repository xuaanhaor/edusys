/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.helper;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author THUAN
 */
public class XImage {

//    public static void main(String[] args) {
//        getAppIcon();
//    }

    public static Image getAppIcon() {
        try {

//        URL url = XImage.class.getResource("\\src\\Hinh\\fpt.png");
        File file = new File("src\\Hinh\\fpt.png");
            return ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(XImage.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return new ImageIcon(url).getImage();
        return null;
    }
        public static void save(File src){
        File dst = new File("logos", src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } 
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static ImageIcon read(String fileName){
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
