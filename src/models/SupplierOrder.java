package models;

import java.sql.Timestamp;

//TODO
public class SupplierOrder {
    int bookId;
    int requiredQuantity;
    Timestamp timestamp;

    public SupplierOrder(Timestamp timestamp){
        this.timestamp = timestamp;
    }

}
