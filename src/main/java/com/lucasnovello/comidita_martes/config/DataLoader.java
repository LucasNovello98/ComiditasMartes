package com.lucasnovello.comidita_martes.config;



import com.lucasnovello.comidita_martes.repository.IComidaRepository;
import com.lucasnovello.comidita_martes.service.IComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private IComidaService comidaService; // inyectar servicio

    @Autowired
    private IComidaRepository comidaRepository;


    @Override
    public void run(String... args) throws Exception {

        if (comidaRepository.count() == 0) {
            // ruta del CSV
            String rutaCSV = "app/src/main/resources/comidas.csv";

            // llamar al metodo para cargar datos
            comidaService.cargarDesdeCSV(rutaCSV);
            System.out.println("Datos cargados desde CSV");
        } else {
            System.out.println("La tabla ya tiene datos, no se cargo nada");
        }
    }
}
