/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.condorcet.demo11;


import be.condorcet.demo11.entities.Client;
import be.condorcet.demo11.services.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;
@Configuration
public class SpringBootConfiguration {
    @Value("${server.mode}")
    private String mode;


    private  Client cl1= new Client(2,"Durant","Patrick",7000,"MONS","de Cuesmes","22","0555444",null);
    private Client cl2= new Client(3,"Durant","Mathilde",4000,"LIEGE","de Bastogne","51","02222222",null);


    @Bean(name = "myClientServiceImpl")
    InterfClientService clientServiceImpl() {

          System.out.println("création du service client en mode : "+mode);
          switch (mode){
              case "PROD" : return new ClientServiceImpl();
              case "STUB" : InterfClientService iclstub = mock(InterfClientService.class);
                  try {
                      when(iclstub.read(2)).thenReturn(new Client(2,"Durant","Patrick",7000,"MONS","de Cuesmes","22","0555444",null));
                      when(iclstub.read("Durant")).thenReturn(List.of(
                              new Client(2,"Durant","Patrick",7000,"MONS","de Cuesmes","22","0555444",null),
                              new Client(3,"Durant","Mathilde",4000,"LIEGE","de Bastogne","51","02222222",null)
                      ));
                  } catch (Exception e) {
                      throw new RuntimeException(e);
                  }
                  return iclstub;

              case "MOCK" :InterfClientService iclsmock = mock(InterfClientService.class);

                  try {
                      when(iclsmock.read(2)).thenReturn(new Client(2,"Durant","Patrick",7000,"MONS","de Cuesmes","22","0555444",null));
                      when(iclsmock.read("Durant")).thenReturn(List.of(
                              new Client(2,"Durant","Patrick",7000,"MONS","de Cuesmes","22","0555444",null),
                              new Client(3,"Durant","Mathilde",4000,"LIEGE","de Bastogne","51","02222222",null)
                      )).thenReturn(List.of(
                              new Client(2,"Durant","Patrick",7000,"MONS","de Cuesmes","22","0555444",null),
                              new Client(3,"Durant","Mathilde",4000,"LIEGE","de Bastogne","51","02222222",null),
                              new Client(1,"Durant","Pierre",1000,"BXL","de la fontaine","1A","0444444444",null)
                      ));
                      when(iclsmock.create(any(Client.class))).thenReturn((new Client(1,"Durant","Pierre",1000,"BXL","de la fontaine","1A","0444444444",null))).thenThrow(new Exception("violation de contrainte"));

                  } catch (Exception e) {
                      throw new RuntimeException(e);
                  }
                  return iclsmock;



              default: return new ClientServiceImpl();
           }
     }

}
