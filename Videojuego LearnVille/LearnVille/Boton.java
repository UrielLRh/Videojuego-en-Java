/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Uriel
 */
package LearnVille;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Boton {
        //  iPosX -> posicion en X del objeto
        //  iPosY -> posicion en Y del objeto
        //  imaIcoObjeto -> imagen del objeto
	private int iPosX;        
	private int iPosY;
        ImageIcon imaBoton;
        ImageIcon imaBoton2;
        
        Boton(int iX, int iY, Image imageBoton, Image imageBoton2)
        {
            iPosX = iX;
            iPosY = iY;
            imaBoton = new ImageIcon(imageBoton);
            imaBoton2 = new ImageIcon(imageBoton2);
        }
        
        	/**
	 * Metodo modificador usado para cambiar la posicion en x del objeto 
	 * @param iPosX es la <code>posicion en x</code> del objeto.
	 */
	public void setPosX(int iPosX) {
            this.iPosX = iPosX;
	}
        
	/**
	 * Metodo de acceso que regresa la posicion en x del objeto 
	 * @return iPosX es la <code>posicion en x</code> del objeto.
	 */
	public int getPosX() {
            return iPosX;
	}
	
	/**
	 * Metodo modificador usado para cambiar la posicion en y del objeto 
	 * @param iPosY es la <code>posicion en y</code> del objeto.
	 */
	public void setPosY(int iPosY) {
            this.iPosY = iPosY;
	}
	
	/**
	 * Metodo de acceso que regresa la posicion en y del objeto 
	 * @return iPosY es la <code>posicion en y</code> del objeto.
	 */
	public int getPosY() {
            return iPosY;
	}
	
	/**
	 * Metodo modificador usado para cambiar el imaBoton del objeto 
	 * @param imaIcoBoton es el <code>imaBoton</code> del objeto.
	 */
	public void setImageIcon(ImageIcon imaIcoBoton) {
            this.imaBoton = imaIcoBoton;
	}
        
        /**
	 * Metodo modificador usado para cambiar el imaBoton2 del objeto 
	 * @param imaIcoBoton es el <code>imaBoton2</code> del objeto.
	 */
        public void setImageIcon2(ImageIcon imaIcoBoton) {
            this.imaBoton2 = imaIcoBoton;
	}
	
	/**
	 * Metodo de acceso que regresa el imaBoton del objeto 
	 * @return imaBoton es el <code>imaBoton</code> del objeto.
	 */
	public ImageIcon getImageIcon() {
            return imaBoton;
	}
         /**
	 * Metodo de acceso que regresa el imaBoton2 del objeto 
	 * @return imaBoton2 es el <code>imaBoton2</code> del objeto.
	 */
	public ImageIcon getImageIcon2() {
            return imaBoton2;
	}
	
	/**
	 * Metodo de acceso que regresa el ancho del imaBoton 
	 * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del imaIcoObjeto.
	 */
	public int getAncho() {
            return imaBoton.getIconWidth();
	}
	
	/**
	 * Metodo de acceso que regresa el alto del imaBoton 
	 * @return un objeto de la clase <code>ImageIcon</code> que es el alto del imaIcoObjeto.
	 */
	public int getAlto() {
            return imaBoton.getIconHeight();
	}
	
	/**
	 * Metodo de acceso que regresa la imagen del imaBoton
	 * @return un objeto de la clase <code>Image</code> que es la imagen del imaIcoObjeto.
	 */
	public Image getImageI() {
            return imaBoton.getImage();
	}
        
        public Image getImageI2() {
            return imaBoton2.getImage();
	}
	
	/**
	 * Metodo de acceso que regresa un nuevo rectangulo
	 * @return un objeto de la clase <code>Rectangle</code> que es el perimetro 
	 * del rectangulo
	 */
	public Rectangle getPerimetro(){
            return new Rectangle(getPosX(),getPosY(),getAncho(),getAlto());
	}
}
