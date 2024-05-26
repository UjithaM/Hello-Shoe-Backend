package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.CustomerRepo;
import software.ujithamigara.helloShoesSystem.dto.CustomerDTO;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;
import software.ujithamigara.helloShoesSystem.service.CustomerService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {
    private final CustomerRepo repo;
    private final Mapping mapping;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO)   {
        long customerCount = repo.count();

        String customerCode = String.format("C%04d", customerCount + 1);

        customerDTO.setCustomerCode(customerCode);
        return mapping.toCustomerDTO(repo.save(mapping.toCustomer(customerDTO)));
    }

    @Override
    public void deleteCustomer(String customerId) {
        repo.delete(repo.findById(customerId).get());
    }

    @Override
    public CustomerDTO getSelectedCustomer(String customerId) {
        return mapping.toCustomerDTO(repo.findById(customerId).get());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.toCustomerDTOList(repo.findAll());
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        CustomerEntity customer = repo.findById(customerId).get();
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
    }
}
