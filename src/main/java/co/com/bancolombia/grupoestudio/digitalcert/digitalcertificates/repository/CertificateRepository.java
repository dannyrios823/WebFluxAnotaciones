package co.com.bancolombia.grupoestudio.digitalcert.digitalcertificates.repository;

import co.com.bancolombia.grupoestudio.digitalcert.digitalcertificates.model.Certificate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CertificateRepository extends ReactiveMongoRepository<Certificate, String> {
}
