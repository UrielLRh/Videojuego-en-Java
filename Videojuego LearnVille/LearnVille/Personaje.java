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

public class Personaje{
        //  iPosX -> posicion en X del personaje
        //  iPosY -> posicion en Y del personaje
        //  imaIcoPersonaje -> imagen del personaje
	private int iPosX;        
	private int iPosY;
        private int iTiempo;
        private int iImagenDesplegar;
        private int iDireccion;
        private ImageIcon []imaPersonaje;
        private ImageIcon []imaArriba;
        private ImageIcon []imaAbajo;
        private ImageIcon []imaDerecha;
        private ImageIcon []imaIzquierda;
        
        Personaje(int iX, int iY)
        {
            iPosX = iX;
            iPosY = iY;
            imaPersonaje = new ImageIcon[4];
            //imaPersonaje = new ImageIcon(imagePersonaje);
        }
        
        /*Personaje(int iX, int iY, int iImagenes)
        {
            iPosX = iX;
            iPosY = iY;
            imaArriba = new ImageIcon[]
        }*/
            
        /**
	 * Metodo modificador usado para cambiar la posicion en x del personaje 
	 * @param iPosX es la <code>posicion en x</code> del personaje.
	 */
	public void setPosX(int iPosX) {
            this.iPosX = iPosX;
	}
        
	/**
	 * Metodo de acceso que regresa la posicion en x del personaje 
	 * @return iPosX es la <code>posicion en x</code> del personaje.
	 */
	public int getPosX() {
            return iPosX;
	}
	
	/**
	 * Metodo modificador usado para cambiar la posicion en y del personaje 
	 * @param iPosY es la <code>posicion en y</code> del personaje.
	 */
	public void setPosY(int iPosY) {
            this.iPosY = iPosY;
	}
	
	/**
	 * Metodo de acceso que regresa la posicion en y del personaje 
	 * @return iPosY es la <code>posicion en y</code> del personaje.
	 */
	public int getPosY() {
            return iPosY;
	}
	
	/**
	 * Metodo modificador usado para cambiar el imaPersonaje del personaje 
	 * @param imaIcoPersonaje es el <code>imaPersonaje</code> del personaje.
        * @param iNum es la casilla del personaje
	 */
	public void setImageIcon(Image imaIcoPersonaje, int iNum) {
            imaPersonaje[iNum] = new ImageIcon(imaIcoPersonaje);
	}
	
        
        public void actualizaPersonaje(int iDireccion)
        {
            if(this.iDireccion == iDireccion)
            {
                iTiempo++;
                if(iTiempo%15 == 0)
                {
                    iImagenDesplegar++;
                    if(iImagenDesplegar > 3) iImagenDesplegar = 0;
                }
            }
            else
            {
                this.iDireccion = iDireccion;
                iImagenDesplegar = 0;
                iTiempo = 0;
            }
        }
        
        /*public void getImageMov()
        {
            if(iDireccion)
        }*/
        
	/**
	 * Metodo de acceso que regresa el imaPersonaje del personaje 
         * @param iNum es la casilla del personaje
	 * @return imaPersonaje es el <code>imaPersonaje</code> del personaje.
	 */
	public ImageIcon getImageIcon(int iNum)
        {
            return imaPersonaje[iNum];
	}
	
	/**
	 * Metodo de acceso que regresa el ancho del imaPersonaje 
	 * @return un personaje de la clase <code>ImageIcon</code> que es el ancho del imaIcoPersonaje.
	 */
	public int getAncho() {
            return imaPersonaje[0].getIconWidth();
	}
	
	/**
	 * Metodo de acceso que regresa el alto del imaPersonaje 
	 * @return un personaje de la clase <code>ImageIcon</code> que es el alto del imaIcoPersonaje.
	 */
	public int getAlto() {
            return imaPersonaje[0].getIconHeight();
	}
	
	/**
	 * Metodo de acceso que regresa la imagen del imaPersonaje
         * @param iNum es la casilla del personaje
	 * @return un personaje de la clase <code>Image</code> que es la imagen del imaIcoPersonaje.
	 */
	public Image getImage(int iNum) {
            return imaPersonaje[iNum].getImage();
	}
	
	/**
	 * Metodo de acceso que regresa un nuevo rectangulo
	 * @return un personaje de la clase <code>Rectangle</code> que es el perimetro 
	 * del rectangulo
	 */
	public Rectangle getPerimetro(){
            return new Rectangle(getPosX(),getPosY(),getAncho(),getAlto());
	}
        
        /**
	 * Metodo de acceso que regresa un nuevo rectangulo
	 * @return un personaje de la clase <code>Rectangle</code> que es el perimetro 
	 * del rectangulo
	 */
	public Rectangle getBase(){
            return new Rectangle(getPosX(),getPosY()+getAlto()/2,getAncho(),getAlto()/2);
	}
        
        /**
	 * Metodo de acceso que regresa un nuevo rectangulo
	 * @return un personaje de la clase <code>Rectangle</code> que es el perimetro 
	 * del rectangulo
	 */
	public Rectangle getPosicion(){
            return new Rectangle(getPosX()+getAncho()/2,getPosY()+5*getAlto()/6,1,1);
	}
}
