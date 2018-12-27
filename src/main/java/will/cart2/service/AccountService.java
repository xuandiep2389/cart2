package will.cart2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import will.cart2.model.Account;

public interface AccountService {
    Page<Account> findAll(Pageable pageable);

    Account findById(Long id);

    void save(Account account);

    void remove(Long id);

    Page<Account> findAllByUsernameContaining(String username, Pageable pageable);

    Page<Account> findAllByEmailContaining(String email, Pageable pageable);

}
