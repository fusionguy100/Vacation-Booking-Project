package com.example.demo.bootstrap;

import com.example.demo.Dao.CustomerRepository;
import com.example.demo.Dao.DivisionRepository;
import com.example.demo.Entities.Customer;
import com.example.demo.Entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    // Constructor for dependency injection
    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override

    public void run(String... args) throws Exception {
        Division newDivision = divisionRepository.findById(2L).orElse(null);


       Customer customer1 = new Customer("Jake", "Henry","123 main street", "12345", "670-241-2451", newDivision);
       Customer customer2 = new Customer("Mike", "Stevens","123 main street", "12345", "670-241-2451", newDivision);
       Customer customer3 = new Customer("Lynne", "Microsoft","123 main street", "12345", "670-241-2451", newDivision);
       Customer customer4 = new Customer("Sam", "Williams","123 main street", "12345", "670-241-2451", newDivision);
       Customer customer5 = new Customer("Jessica", "Harris","123 main street", "12345", "670-241-2451", newDivision);



       customerRepository.save(customer1);;
       customerRepository.save(customer2);
       customerRepository.save(customer3);
       customerRepository.save(customer4);
       customerRepository.save(customer5);

    }
}
