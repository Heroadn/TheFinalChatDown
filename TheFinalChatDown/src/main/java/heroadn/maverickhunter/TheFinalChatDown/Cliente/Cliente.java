/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heroadn.maverickhunter.TheFinalChatDown.Cliente;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.imageio.ImageIO;

/**
 *
 * @author Heroadn
 */
public class Cliente implements Serializable{
    private static final long serialVersionUID = 5397040574125655354L;
   
    private transient Socket socket;
    private String nome;
    
    private String hostName;
    private int id;
    
    private byte[] imgArray;
    private transient BufferedImage image;
    
    public Cliente() {}
    
    public Cliente(Socket socket, String nome, String hostName, byte[] imgArray) {
        this.socket = socket;
        this.nome = nome;
        this.hostName = hostName;
        this.imgArray = imgArray;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public byte[] getImgArray() {
		return imgArray;
	}

	public void setImgArray(byte[] img) {
		this.imgArray = img;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
    
}
