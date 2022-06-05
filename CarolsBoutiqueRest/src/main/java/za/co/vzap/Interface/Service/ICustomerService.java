package za.co.vzap.Interface.Service;

import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Customer.Model.Review;

public interface ICustomerService {
    int addCustomer(Customer customer);//here
    
    int addReview(Review review);//here
}
