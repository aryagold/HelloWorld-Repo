package za.co.vzap.Customer.Service;

import za.co.vzap.Communication.Email.Email;
import za.co.vzap.Communication.Model.CommunicationDto;
import za.co.vzap.Communication.Model.EmailTypeEnum;
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
        this.reviewRepository = new ReviewRepository();
        this.customerRepository = new CustomerRepository();
    }
    
    @Override
    public int addReview(Review review) {
        
        if(review.getComment().isEmpty()) review.setComment("No comment");
        
        int id = reviewRepository.add(review);
        
        return id;
    }

    
    @Override
    public int addCustomer(Customer customer) {
        
        int id = customerRepository.add(customer);
        CommunicationDto dto = new CommunicationDto();
        dto.emailAddressTo = customer.getEmail();
        dto.emailType = EmailTypeEnum.SUBSCRIPTIONNOTIFICATION;
        
        new Email(dto).start();
        
        return id;
    }
    
}
