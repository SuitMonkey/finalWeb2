
package com.pucmm.web2.Repository;

import com.pucmm.web2.Entity.Receipt;
import com.pucmm.web2.Enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, String> {

    Receipt findByFiscalCode(String fiscalCode);

    @Query("select r from Receipt r where r.user.email = :email")
    List<Receipt> findByUser(@Param("email") String email);

    @Query("select r from Receipt r where r.status = :status")
    List<Receipt> findByOrderStatus(@Param("status") OrderStatus status);

    @Query("select r from Receipt r where r.transactionDate between :beginning and :ending")
    List<Receipt> findByTimestamp(@Param("beginning") Timestamp start, @Param("ending") Timestamp end);
}
