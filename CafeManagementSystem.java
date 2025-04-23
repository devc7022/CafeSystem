import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return item.getPrice() * quantity;
    }
}

class Order {
    private List<OrderItem> orderItems;

    public Order() {
        this.orderItems = new ArrayList<>();
    }

    public void addItem(MenuItem item, int quantity) {
        orderItems.add(new OrderItem(item, quantity));
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getTotal();
        }
        return total;
    }

    public void displayOrder() {
        System.out.println("=== Order Details ===");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem.getItem().getName() + " x " + orderItem.getQuantity() +
                    " = ₹" + orderItem.getTotal());
        }
        System.out.println("Total Bill: ₹" + calculateTotal());
    }
}

public class CafeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("Coffee", 80));
        menu.add(new MenuItem("Tea", 40));
        menu.add(new MenuItem("Sandwich", 120));
        menu.add(new MenuItem("Burger", 150));
        menu.add(new MenuItem("Pastry", 90));

        Order order = new Order();
        System.out.println("Welcome to the Cafe!\n");

        while (true) {
            System.out.println("\n--- Menu ---");
            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.get(i);
                System.out.println((i + 1) + ". " + item.getName() + " - ₹" + item.getPrice());
            }

            System.out.print("Enter item number to add to order (0 to finish): ");
            int itemNum = sc.nextInt();
            if (itemNum == 0) break;

            if (itemNum < 1 || itemNum > menu.size()) {
                System.out.println("Invalid item number.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();
            order.addItem(menu.get(itemNum - 1), quantity);
            System.out.println("Item added to order!");
        }

        order.displayOrder();
        System.out.println("Thank you for visiting!");
    }
}
