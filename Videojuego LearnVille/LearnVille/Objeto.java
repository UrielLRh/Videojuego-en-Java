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

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Objeto {
        //  iPosX -> posicion en X del objeto
        //  iPosY -> posicion en Y del objeto
        //  imaIcoObjeto -> imagen del objeto
	private int iPosX;        
	private int iPosY;
        private int iAncho;
        private int iAlto;
        private ImageIcon imaObjeto;
        private Color ColorActivo;
        private Color ColorInactivo;
        private String sPalabra;
        private int iOpacidad;
        private boolean bActivo;
        
        // Constructor tipo 1
        Objeto(int iX, int iY, Image imageX)
        {
            iPosX = iX;
            iPosY = iY;
            imaObjeto = new ImageIcon(imageX);
        }
        
        // Constructor tipo 2
        Objeto(int iX, int iY, int iAlto, int iAncho, int iOpacidad, String sPalabra)
        {
            iPosX = iX;
            iPosY = iY;
            this.iOpacidad = iOpacidad;
            this.sPalabra = sPalabra;
            this.iAlto = iAlto;
            this.iAncho = iAncho;
            ColorActivo = new Color(200,0,0, iOpacidad);
            ColorInactivo = new Color(0,0,0,iOpacidad);
            bActivo = false;
        }
           
        /**
	 * Metodo modificador usado para cambiar el color Activo del objeto 
	 * @param ColorA es el <code>Color Activo</code> del objeto.
	 */
	public void setColorActivo(Color ColorA) {
            ColorActivo = ColorA;
	}
        
        /**
	 * Metodo modificador usado para cambiar el color Inactivo del objeto 
	 * @param ColorB es el <code>Color Inactivo</code> del objeto.
	 */
	public void setColorInactivo(Color ColorB) {
            ColorInactivo = ColorB;
	}
        
        /**
	 * Metodo modificador que reinicia el color original del objeto
	 */
        public void reset()
        {
            bActivo = false;
            Color ColorTemp = ColorActivo;
            ColorActivo = ColorInactivo;
            ColorInactivo = ColorTemp;
        }
        
        /**
	 * Metodo modificador que intercambia el color Activo e Inactivo
	 */
        public void activar()
        {
            if(!bActivo)
            {
                bActivo = true;
                Color ColorTemp = ColorActivo;
                ColorActivo = ColorInactivo;
                ColorInactivo = ColorTemp;
            }
        }
        
        /**
	 * Metodo de acceso que regresa el color del objeto
	 * @return un objeto de la clase <code>Color</code> que es el color activo del objeto.
	 */
        public Color getColorActivo()
        {
            return ColorActivo;
        }
        
        /**
	 * Metodo de acceso que regresa el color inactivo del objeto
	 * @return un objeto de la clase <code>Color</code> que es el color inactivo del objeto.
	 */
        public Color getColorInactivo()
        {
            return ColorInactivo;
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
	 * Metodo modificador usado para cambiar el imaObjeto del objeto 
	 * @param imaIcoBoton es el <code>imaObjeto</code> del objeto.
	 */
	public void setImageIcon(ImageIcon imaIcoBoton) {
            this.imaObjeto = imaIcoBoton;
	}
	
	/**
	 * Metodo de acceso que regresa el imaObjeto del objeto 
	 * @return imaObjeto es el <code>imaObjeto</code> del objeto.
	 */
	public ImageIcon getImageIcon() {
            return imaObjeto;
	}
	
	/**
	 * Metodo de acceso que regresa el ancho del imaObjeto 
	 * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del imaIcoObjeto.
	 */
	public int getAncho() {
            return imaObjeto.getIconWidth();
	}
	
	/**
	 * Metodo de acceso que regresa el alto del imaObjeto 
	 * @return un objeto de la clase <code>ImageIcon</code> que es el alto del imaIcoObjeto.
	 */
	public int getAlto() {
            return imaObjeto.getIconHeight();
	}
        
        /**
	 * Metodo de acceso que regresa el ancho del imaObjeto 
	 * @return un numero de tipo <code>int</code> que es el ancho del objeto.
	 */
	public int getiAncho() {
            return iAncho;
	}
	
	/**
	 * Metodo de acceso que regresa el alto del imaObjeto 
	 * @return una numero de tipo <code>int</code> que es el alto del objeto.
	 */
	public int getiAlto() {
            return iAlto;
	}
	
	/**
	 * Metodo de acceso que regresa la imagen del imaObjeto
	 * @return un objeto de la clase <code>Image</code> que es la imagen del imaIcoObjeto.
	 */
	public Image getImageI() {
            return imaObjeto.getImage();
	}
	
	/**
	 * Metodo de acceso que regresa un nuevo rectangulo
	 * @return un objeto de la clase <code>Rectangle</code> que es el perimetro 
	 * del rectangulo
	 */
	public Rectangle getPerimetro(){
            return new Rectangle(getPosX(),getPosY(),getAncho(),getAlto());
	}
        
        /**
	 * Metodo de acceso que regresa un nuevo rectangulo
	 * @return un objeto de la clase <code>Rectangle</code> que es el perimetro 
	 * del rectangulo
	 */
	public Rectangle getRectangle()
        {
            return new Rectangle(getPosX(),getPosY(),iAncho,iAlto);
	}
        
        /**
	 * Metodo que indica si el objeto esta colisionando con un Personaje
         * @param obj es el <code>Personaje</code> con el que se reviza la 
         * intersección.
	 * @return una variable <code>boolean</code> que es true si hay colisión 
	 */
        public boolean intersectaPersonaje(Personaje obj){
            return getRectangle().intersects(obj.getPerimetro());
        }
        
        /**
	 * Metodo que encuentra una nueva posición para el objeto
	 * @param imaColision es un <code>ImageIcon</code> que 
	 */
        public void nuevaPosicion(ImageIcon imaColision)
        {
            Image imaAnterior = getImageI();
            setImageIcon(imaColision);
            
            for(int iC = 0; iC < 5000; iC++)
            {
                
            }
            int iNPosX =  (int)(Math.random()*(2)) ;
            iPosX = (300 * iNPosX) + 1280 ;
            setImageIcon(new ImageIcon(imaAnterior));
        }
        
        /**
	 * Metodo de acceso que regresa un rectangulo que corresponde a la parte
         * izquierda del objeto
	 * @return Un objeto de la clase <code>Rectangle</code> la cara
         * izquierda del objeto
	 */
        public Rectangle getLadoIzquierdo(){
            return new Rectangle(getPosX(), getPosY(), 1, getAlto());
	}
        
        /**
	 * Metodo boleano que indica si hay colision con otro objeto
	 * @return un valor <code>boolean</code> que indica si hay colision o no
	 */
        public boolean intersecta(Objeto obj){
            return getLadoIzquierdo().intersects(obj.getPerimetro());
        }
}
