package co.com.bancolombia.grupoestudio.digitalcert;

import co.com.bancolombia.grupoestudio.digitalcert.digitalcertificates.model.Certificate;
import co.com.bancolombia.grupoestudio.digitalcert.digitalcertificates.repository.CertificateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ApiDigitalCertificates {
    public  static void main(String[] args) {
        SpringApplication.run(ApiDigitalCertificates.class, args);
    }

    @Bean
    CommandLineRunner init(CertificateRepository repository){
        return args -> {

            Flux<Certificate> Certificates = Flux.just(
                    Certificate.builder().nombre("CertiBanco1").fechaVencimiento("2020/05/28").aplicacion("APP").areaResponsable("Soporte").nombreKeyStore("KYS-CAN-PER-APP").indicadorFirmado(true).build(),
                    Certificate.builder().nombre("CerBanc002").fechaVencimiento("2020/06/13").aplicacion("SVP").areaResponsable("Transformacion").nombreKeyStore("KYS-CAN-PER-SVP").indicadorFirmado(true).build(),
                    Certificate.builder().nombre("CertifiBC03").fechaVencimiento("2020/10/27").aplicacion("SVE").areaResponsable("Tranformacion").nombreKeyStore("KYS-CAN-EMP-SVE").indicadorFirmado(true).build(),
                    Certificate.builder().nombre("CertiBanco2").fechaVencimiento("2020/11/03").aplicacion("ALM").areaResponsable("Soporte").nombreKeyStore("KYS-CAN-DIG-ALM").indicadorFirmado(false).build()

            ).flatMap(p-> repository.save(p));

            Certificates.thenMany(repository.findAll())
                    .subscribe(System.out::println);

        };
    }
}


