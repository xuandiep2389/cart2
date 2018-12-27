package will.cart2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import will.cart2.model.Account;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    Page<Account> findAllByUsernameContaining(String username, Pageable pageable);

    Page<Account> findAllByEmailContaining(String email, Pageable pageable);

    Account findByUsername(String username);
}
