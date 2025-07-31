package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {
    private final NavigableMap<Customer, String> customers = new TreeMap<>(Comparator.comparing(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> customerStringEntry = customers.firstEntry();
        return Map.entry(
                new Customer(
                        customerStringEntry.getKey().getId(),
                        customerStringEntry.getKey().getName(),
                        customerStringEntry.getKey().getScores()),
                customerStringEntry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> customerStringEntry = customers.higherEntry(customer);
        if (customerStringEntry == null) {
            return null;
        }
        return Map.entry(
                new Customer(
                        customerStringEntry.getKey().getId(),
                        customerStringEntry.getKey().getName(),
                        customerStringEntry.getKey().getScores()),
                customerStringEntry.getValue());
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
