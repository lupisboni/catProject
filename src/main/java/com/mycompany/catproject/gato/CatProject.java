package com.mycompany.catproject.gato;

import java.io.IOException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guada
 */
public class CatProject {
    public static void main(String [] args)throws IOException{
        GatosService catService = new GatosService();
        catService.getGatos();
        
        int opcionMenu = -1;
        String[] opciones={
            "1. Ver Gatos",
            "2. Salir"
        };
        
        do{
            String opcion =(String) JOptionPane.showInputDialog(null,"Gatitos java","Menu Principal", 
                    JOptionPane.INFORMATION_MESSAGE,null,opciones,opciones[0]);
            for(int i=0; i< opciones.length; i++){
                if(opcion.equals(opciones[i]))
                    opcionMenu = i;
            }
                    switch(opcionMenu ){
                        case 0:
                            System.out.println("Vas a ver un gato");
                    }       
            }while(opcionMenu !=1);
        }
    }
