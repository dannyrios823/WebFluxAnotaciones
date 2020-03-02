package co.com.bancolombia.grupoestudio.digitalcert.digitalcertificates.controller;

import co.com.bancolombia.grupoestudio.digitalcert.digitalcertificates.model.Certificate;
import co.com.bancolombia.grupoestudio.digitalcert.digitalcertificates.repository.CertificateRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/certificates")
@AllArgsConstructor

public class CertificateController {

    private CertificateRepository repository;

    @GetMapping
    public Flux<Certificate> getAllCertificates(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Certificate>> getCertificate(@PathVariable("id") String id){
        return repository.findById(id).map(cert -> ResponseEntity.ok(cert))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Certificate> saveCertificate(@RequestBody Certificate cert){
        return repository.save(cert);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Certificate>> updateCertificate(@PathVariable(value="id") String id, @RequestBody Certificate cert){
        return  repository.findById(id)
                .flatMap(existingCertificate -> {
                    existingCertificate.setFechaVencimiento(cert.getFechaVencimiento());
                    existingCertificate.setAplicacion(cert.getAplicacion());
                    existingCertificate.setAreaResponsable(cert.getAreaResponsable());
                    existingCertificate.setNombreKeyStore(cert.getNombreKeyStore());
                    existingCertificate.setIndicadorFirmado(cert.isIndicadorFirmado());
                    return repository.save(existingCertificate);
                }).map(updateCertificate -> ResponseEntity.ok(updateCertificate))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteCertificate(@PathVariable(value = "id") String id){
        return repository.findById(id)
                .flatMap(existingCertificate -> repository.delete(existingCertificate)
                        .then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllCertificates(){
        return repository.deleteAll();
    }

}
