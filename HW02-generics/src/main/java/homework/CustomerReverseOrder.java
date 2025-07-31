package homework;

import java.util.LinkedList;

public class CustomerReverseOrder {
    LinkedList<Customer> customers = new LinkedList<>();

    public void add(Customer customer) {
        customers.addFirst(customer);
    }

    public Customer take() {
        return customers.pop();
    }
}
