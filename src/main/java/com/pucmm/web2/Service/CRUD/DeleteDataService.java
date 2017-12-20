
package com.pucmm.web2.Service.CRUD;

import com.pucmm.web2.Entity.History;
import com.pucmm.web2.Entity.Product;
import com.pucmm.web2.Repository.HistoryRepository;
import com.pucmm.web2.Repository.ProductRepository;
import com.pucmm.web2.Repository.ReceiptRepository;
import com.pucmm.web2.Enums.OrderStatus;
import freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Set;

@Service
public class DeleteDataService {

    // Repositories
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    // Deletes
    public void deleteRegisteredProduct(Integer productId) throws Exception{

        if (!doesProductIdExist(productId))
            throw new IllegalArgumentException("This product does not exists");

        try {
            Product product = productRepository.findByProductId(productId);

            // Applying Cascade
            for (History history:
                 historyRepository.findAll()) {
                // Fetching sets
                Set<Product> browsingHistory = history.getBrowsingHistory();
                Set<Product> shoppingCart = history.getShoppingCart();

                // Removing the item
                browsingHistory.remove(product);
                shoppingCart.remove(product);

                // Update History
                history.setBrowsingHistory(browsingHistory);
                history.setShoppingCart(shoppingCart);
                historyRepository.save(history);
            }

            // Deleting Product
            productRepository.delete(product);
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (NullArgumentException exp){
            throw new NullArgumentException("Null Argument Error --> " + exp.getMessage());
        } catch (Exception exp){
            throw new Exception("General Error --> " + exp.getMessage());
        }
    }

    public void deleteRegisteredPendingTransaction(String fiscalCode) throws Exception{

        if (receiptRepository.findByFiscalCode(fiscalCode).getStatus() == OrderStatus.PENDING)
            throw new IllegalArgumentException("This is an illegal action! You cannot delete a pending transaction");

        try {
            receiptRepository.delete(fiscalCode);
        } catch (PersistenceException exp){
            throw new PersistenceException("Persistence Error --> " + exp.getMessage());
        } catch (NullArgumentException exp){
            throw new NullArgumentException("Null Argument Error --> " + exp.getMessage());
        } catch (Exception exp){
            throw new Exception("General Error --> " + exp.getMessage());
        }
    }

    // Auxiliary Functions
    private boolean doesProductIdExist(Integer productId){
        Product product = productRepository.findByProductId(productId);
        return (product != null);
    }
}
