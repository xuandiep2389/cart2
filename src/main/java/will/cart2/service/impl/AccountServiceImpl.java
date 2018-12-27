package will.cart2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import will.cart2.model.Account;
import will.cart2.repository.AccountRepository;
import will.cart2.repository.RoleRepository;
import will.cart2.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void remove(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Page<Account> findAllByUsernameContaining(String username, Pageable pageable) {
        return accountRepository.findAllByUsernameContaining(username, pageable);
    }

    @Override
    public Page<Account> findAllByEmailContaining(String email, Pageable pageable) {
        return accountRepository.findAllByEmailContaining(email, pageable);
    }
}
