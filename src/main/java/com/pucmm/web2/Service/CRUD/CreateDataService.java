
package com.pucmm.web2.Service.CRUD;

import com.pucmm.web2.Entity.History;
import com.pucmm.web2.Entity.Product;
import com.pucmm.web2.Entity.Receipt;
import com.pucmm.web2.Entity.User;
import com.pucmm.web2.Repository.HistoryRepository;
import com.pucmm.web2.Repository.ProductRepository;
import com.pucmm.web2.Repository.ReceiptRepository;
import com.pucmm.web2.Repository.UserRepository;
import com.pucmm.web2.Service.Auxiliary.EmailService;
import com.pucmm.web2.Service.Auxiliary.EncryptionService;
import com.pucmm.web2.Enums.Permission;
import freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;

@Service
public class CreateDataService
{
    // Repositories
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private EmailService emailService;

    // Product Creation
    public Product registerNewProduct(String productName, String supplier, String productDescription, Float productPrice, Integer productInStock) throws Exception{

        if (productPrice <= 0.00f)
            throw new IllegalArgumentException("All price must be positive decimal numbers");

        if (productInStock < 0)
            throw new IllegalArgumentException("There must be at least one unit registered");

        try {
            return productRepository.save(new Product(productName, supplier, productDescription, productPrice, productInStock));
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (NullArgumentException exp){
            throw new NullArgumentException("Null Argument Error --> " + exp.getMessage());
        } catch (Exception exp){
            throw new Exception("General Error --> " + exp.getMessage());
        }
    }

    public Product registerNewProduct(Product p) throws Exception{

        if (p.getProductPrice() <= 0.00f)
            throw new IllegalArgumentException("All price must be positive decimal numbers");

        if (p.getProductInStock() < 0)
            throw new IllegalArgumentException("There must be at least one unit registered");

        try {
            return productRepository.save(p);
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (NullArgumentException exp){
            throw new NullArgumentException("Null Argument Error --> " + exp.getMessage());
        } catch (Exception exp){
            throw new Exception("General Error --> " + exp.getMessage());
        }
    }

    // Receipt Creation
    public Receipt registerTransaction(String email, ArrayList<Integer> productList, ArrayList<Integer> amount, Float total) throws Exception {

        if (!isEmailAddressTaken(email))
            throw new IllegalArgumentException("This user account does not exist");

        if (productList.isEmpty())
            throw new IllegalArgumentException("There needs to be purchased items to realize a transaction");

        if (total < 0.00f)
            throw new IllegalArgumentException("Nothing is free in life");

        if (productList.size() != amount.size())
            throw new IllegalStateException("An error occurred while registering items; productList size is no equal to amount size");

        try {
            return receiptRepository.save(new Receipt(userRepository.findByEmail(email), productList, amount, total));
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (NullArgumentException exp){
            throw new NullArgumentException("Null Argument Error --> " + exp.getMessage());
        } catch (Exception exp){
            throw new Exception("General Error --> " + exp.getMessage());
        }
    }

    // User and History Creation
    public User registerNewUser(String email, String firstName, String lastName, String shippingAddress, String country, String city, String password, Permission permission, String id, boolean valorF, String departament) throws Exception{

        if (isEmailAddressTaken(email))
            throw new IllegalArgumentException("This user Account already exist");

        try {
            User user = userRepository.save(new User(email, firstName, lastName,  shippingAddress, country, city,password, permission, departament,id,valorF));
            historyRepository.save(new History(user)); // Creating the users history
            emailService.sendUserRegistrationConfirmation(user);
            return user;
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (NullArgumentException exp){
            throw new NullArgumentException("Null Argument Error --> " + exp.getMessage());
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
