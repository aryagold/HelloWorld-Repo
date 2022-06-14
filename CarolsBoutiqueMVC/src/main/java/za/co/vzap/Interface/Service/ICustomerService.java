package za.co.vzap.Interface.Service;

import za.co.vzap.Model.Customer.Customer;
import za.co.vzap.Model.Customer.Review;

public interface ICustomerService {
    int addCustomer(Customer customer);//here
    
    int addReview(Review review);//here
}
