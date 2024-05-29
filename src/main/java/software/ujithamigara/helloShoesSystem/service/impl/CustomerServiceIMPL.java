package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.CustomerRepo;
import software.ujithamigara.helloShoesSystem.dto.CustomerDTO;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;
import software.ujithamigara.helloShoesSystem.exception.NotFoundException;
import software.ujithamigara.helloShoesSystem.service.CustomerService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceIMPL.class);

    private final CustomerRepo repo;
    private final Mapping mapping;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        long customerCount = repo.count();
        String customerCode = String.format("C%04d", customerCount + 1);
        customerDTO.setCustomerCode(customerCode);

        CustomerEntity customer = mapping.toCustomer(customerDTO);
        CustomerDTO savedCustomerDTO = mapping.toCustomerDTO(repo.save(customer));

        logger.info("Customer saved successfully: {}", customerCode);
        return savedCustomerDTO;
    }

    @Override
    public void deleteCustomer(String customerId) {
        if (repo.existsById(customerId)) {
            repo.deleteById(customerId);
            logger.info("Customer deleted: {}", customerId);
        } else {
            logger.warn("Customer not found: {}", customerId);
            throw new NotFoundException("Customer not found" + customerId);
        }
    }

    @Override
    public CustomerDTO getSelectedCustomer(String customerId) {
        CustomerEntity customer = repo.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found" + customerId));

        CustomerDTO customerDTO = mapping.toCustomerDTO(customer);
        logger.info("Customer retrieved: {}", customerId);
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerEntity> customers = repo.findAll();
        logger.info("All customers retrieved. Count: {}", customers.size());
        return mapping.toCustomerDTOList(customers);
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        CustomerEntity customer = repo.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found" + customerDTO.getName()));

        customer.setName(customerDTO.getName());
        customer.setGender(customerDTO.getGender());
        customer.setJoinDate(customerDTO.getJoinDate());
        customer.setLevel(customerDTO.getLevel());
        customer.setTotalPoints(customerDTO.getTotalPoints());
        customer.setDob(customerDTO.getDob());
        customer.setAddressNo(customerDTO.getAddressNo());
        customer.setLane(customerDTO.getLane());
        customer.setMainCity(customerDTO.getMainCity());
        customer.setMainState(customerDTO.getMainState());
        customer.setPostalCode(customerDTO.getPostalCode());
        customer.setContactNumber(customerDTO.getContactNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setRecentPurchaseDate(customerDTO.getRecentPurchaseDate());

        repo.save(customer);
        logger.info("Customer updated: {}", customerId);
    }
}
