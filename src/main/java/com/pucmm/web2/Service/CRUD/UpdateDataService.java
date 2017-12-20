
package com.pucmm.web2.Service.CRUD;

import com.pucmm.web2.Entity.History;
import com.pucmm.web2.Entity.Product;
import com.pucmm.web2.Entity.Receipt;
import com.pucmm.web2.Entity.User;
import com.pucmm.web2.Repository.HistoryRepository;
import com.pucmm.web2.Repository.ProductRepository;
import com.pucmm.web2.Repository.ReceiptRepository;
import com.pucmm.web2.Repository.UserRepository;
import freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class UpdateDataService {

    // Repositories
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private UserRepository userRepository;

    // History Updates
    public void updateRegisteredUserHistory(History history) throws Exception{

        if (history == null)
            throw new NullArgumentException("This history is void");

        try {
            historyRepository.save(history);
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (Exception exp){
            throw new Exception("General Error --> " + exp.getMessage());
        }
    }

    // Product Updates
    public void updateRegisteredProduct(Product product) throws Exception {

        if (product == null)
            throw new NullArgumentException("This product is void");

        try {
            productRepository.save(product);
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (Exception exp){
            throw new Exception("General Error --> " + exp.getMessage());
        }
    }

    // Receipt Updates
    public void updateRegisteredUserTransaction(Receipt receipt) throws Exception{

        if (receipt == null)
            throw new NullArgumentException("This transaction is void");

        try {
            receiptRepository.save(receipt);
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (Exception exp){
            throw new Exception("General Error --> " + exp.getMessage());
        }
    }

    // User and History Updates
    public void updateRegisteredUserAccount(User user) throws Exception {

        if (user == null)
            throw new NullArgumentException("This user has a null value");

        if (!isEmailAddressTaken(user.getEmail()))
            throw new IllegalArgumentException("This user account does not exist");

        try {
            // Updating user
            userRepository.save(user);

            // Updating History
            History history = historyRepository.findByUser(user.getEmail());

            history.setUser(user);

            historyRepository.save(history);
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (Exception exp){
            throw new Exception("General Error --> " + exp.getMessage());
        }
    }

    // Auxiliary Functions
    private boolean isEmailAddressTaken(String email){
        User user = userRepository.findByEmail(email);
        return (user != null);
    }
}
