package com.mycompany.catproject.gato;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author alexa
 */
public class GatosService {

    //Renderiza una imagen dentro de in JOptionPane
    public void despliegaImagen(gato unGato, ImageIcon img) throws IOException {
        //Crear menu de opciones para el JOptionPane
        String menu = "Opciones: \n"
                + "1. Ver otro gato \n"
                + "2. Regresar \n";

        String[] opciones = {"Ver otro gato", "Regresar"};

        String idGato = unGato.getId();
        String opcion = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE, img, opciones, opciones[0]);

        int seleccion = -1;
        for (int i = 0; i < opciones.length; i++) {
            if (opcion.equals(opciones[i])) {
                seleccion = i;
            }
            
            switch(seleccion){
                case 0 -> getGatos();
                default ->{
                       break; 
                        }
            }

        }
        
        
    }

    public void getGatos() throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        //Crear un objeto con formato Json
        String gatoJson = response.body().string();
        //Quitar llave inicial y final
        gatoJson = gatoJson.substring(1, gatoJson.length());
        gatoJson = gatoJson.substring(0, gatoJson.length() - 1);

        System.out.println("gatoJson" + gatoJson);
        //Crear un objeto de la clase Gson
        Gson gson = new Gson();
        gato gato = gson.fromJson(gatoJson, gato.class);
        //Probando la informacion que este en el objeto gato
        System.out.println("Gato id: " + gato.getId());
        System.out.println("Gato url: " + gato.getUrl());

        Image image = null;
        try {
            URL url = new URL(gato.getUrl());
            image = ImageIO.read(url);

            //Rendimensionar la imagen obtenida de The Cat API 
            ImageIcon imgGato = new ImageIcon(image);
            if (imgGato.getIconWidth() > 800) {
                Image img = imgGato.getImage();
                Image imgModificada = img.getScaledInstance(800, 550, java.awt.Image.SCALE_SMOOTH);
                imgGato = new ImageIcon(imgModificada);
            }

            despliegaImagen(gato, imgGato);

        } catch (Exception e) {
            System.out.println("No se pudo crear el objeto Image");

        }

    }
}