package com.lucasnovello.comidita_martes.config;



import com.lucasnovello.comidita_martes.service.IComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private IComidaService comidaService; // inyectar servicio


    @Override
    public void run(String... args) throws Exception {

        // ruta del CSV
        String rutaCSV = "src/main/resources/comidas.csv";


        // llamar al metodo para cargar datos
        //comidaService.cargarDesdeCSV(rutaCSV);
    }
}
