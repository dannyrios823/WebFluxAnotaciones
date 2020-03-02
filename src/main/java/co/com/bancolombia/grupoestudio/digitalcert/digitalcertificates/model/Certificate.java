package co.com.bancolombia.grupoestudio.digitalcert.digitalcertificates.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Certificate {

    @Id
    private String id;
    private String nombre;
    private String fechaVencimiento;
    private String aplicacion;
    private String areaResponsable;
    private String nombreKeyStore;
    private boolean indicadorFirmado;
}

