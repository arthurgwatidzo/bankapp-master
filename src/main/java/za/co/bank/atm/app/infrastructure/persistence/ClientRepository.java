package za.co.bank.atm.app.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.bank.atm.app.domain.Client;

import java.util.Optional;

/**
 * @author Arthur Gwatidzo email:arthur.gwatidzo@gmail.com
 */
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
