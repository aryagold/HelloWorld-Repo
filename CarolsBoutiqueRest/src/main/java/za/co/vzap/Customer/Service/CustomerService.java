package za.co.vzap.Customer.Service;

import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Customer.Repository.CustomerRepository;
import za.co.vzap.Customer.Repository.ReviewRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.ICustomerService;

public class CustomerService implements ICustomerService {
    private IRepository reviewRepository = null;
    private IRepository customerRepository = null;
    
    public CustomerService(IRepository reviewRepository, IRepository customerRepository) {
        reviewRepository = new ReviewRepository();
        customerRepository = new CustomerRepository();
    }
    
    @Override
    public int addReview(Review review) {
        
        if(review.getComment().isBlank()) review.setComment("No comment");
        
        int id = reviewRepository.add(review);
        
        return id;
    }

    
    @Override
    public int addCustomer(Customer customer) {
        
        int id = customerRepository.add(customer);
        
        // code to send the customer an email notifying them they have joined the newsletter.
        // possibly substring the email to get a name from the customer and greet them with it in the email? 
        // as a little something special frill vibe?
        
        return id;
    }
    
}
