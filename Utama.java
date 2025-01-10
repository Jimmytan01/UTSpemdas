import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

class Item {         // initialize item
    String code;
    String name;
    double price;
    int quantity;
    String currency;
    String unit;

    Item(String code, String name, double price, int quantity, String currency, String unit) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.currency = currency;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Code: " + code + ", Name: " + name + ", Price: " + currency + price + ", Quantity: " + quantity + ", Unit: " + unit;
    }
}

class SalesOrder {
    String orderID;
    String customerName;
    String status;
    Date date;
    String paymentMethod;
    ArrayList<Item> items;
    double totalAmount;
    Date lastUpdate;

    SalesOrder(String orderID, String customerName, String paymentMethod) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.status = "Pending";
        this.date = new Date();
        this.paymentMethod = paymentMethod;
        this.items = new ArrayList<>();
        this.totalAmount = 0;
        this.lastUpdate = null;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // Tambahkan item ke dalam pesanan dan update total harga
    public void addItem(Item item, int quantity) {
        this.items.add(new Item(item.code, item.name, item.price, quantity, item.currency, item.unit));
        this.totalAmount += item.price * quantity;
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        for (Item item : items) {
            details.append("\n    Item Code: ").append(item.code)
                    .append(", Name: ").append(item.name)
                    .append(", Quantity: ").append(item.quantity)
                    .append(", Price: ").append(item.currency).append(item.price);
        }
        return String.format ("\nOrder ID: " + orderID + ", Customer: " + customerName + ", Payment Method: " + paymentMethod + ", Total: " + totalAmount + ", Date: " + date + ", Status: " + status + "\nDetails: " + details.toString() + "\nLast Updated: " + (lastUpdate != null ? lastUpdate : "Not updated"));
    }
}

public class Utama {        // Main Menu

    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<SalesOrder> salesOrders = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n====Selamat Datang====");
            System.out.println("Main Menu:");
            System.out.println("1. Items");
            System.out.println("2. Sales Orders");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageItems();
                    break;
                case 2:
                    manageSalesOrders();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.out.println("Terimakasih telah menggunakan program ini ^_^");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageItems() {     // Items Menu
        while (true) {
            System.out.println("\nItems Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Delete Item");
            System.out.println("4. Search Items");
            System.out.println("5. Items List");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    updateItem();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    searchItem();
                    break;
                case 5:
                    ListItems();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addItem() {         // add item
        System.out.print("How many items do you want to add? ");
        int numItems = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numItems; i++) {
            System.out.print("Enter item code: ");
            String code = scanner.nextLine();

            System.out.print("Enter item name: ");
            String name = scanner.nextLine();

            System.out.print("Enter item price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter item quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter item currency: ");
            String currency = scanner.nextLine();

            System.out.print("Enter item unit: ");
            String unit = scanner.nextLine();

            items.add(new Item(code, name, price, quantity, currency, unit));
            System.out.println("Item added successfully.");
        }
    }


    private static void removeItem() {     // remove item
        System.out.print("How Many Items Do You Want To Remove? ");
        int numItems = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numItems; i++) {
            System.out.print("Delete by code or name? ");
            String choice = scanner.nextLine();
            boolean itemFound = false;
            if (choice.equalsIgnoreCase("code")) {
                System.out.print("Enter item code to remove: ");
                String code = scanner.nextLine();
                itemFound = items.removeIf(item -> item.code.equalsIgnoreCase(code));
                if (itemFound) {
                    System.out.println("Item removed successfully.");
                }
                else {
                    System.out.println("Item not found.");
                }
            }
            else if (choice.equalsIgnoreCase("name")) {
                System.out.print("Enter item name to remove: ");
                String name = scanner.nextLine();
                itemFound = items.removeIf(item -> item.name.equalsIgnoreCase(name));
                if (itemFound) {
                    System.out.println("Item removed successfully.");
                }
                else {
                    System.out.println("Item not found.");
                }
            }
            else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void searchItem() {    // search item
        System.out.print("Search by code or name: ");
        String choice = scanner.nextLine();
        boolean itemFound = false;

        if (choice.equalsIgnoreCase("code")) {
            System.out.print("Enter item code to search: ");
            String code = scanner.nextLine();
            itemFound = items.stream()
                    .filter(item -> item.code.equalsIgnoreCase(code))
                    .peek(System.out::println)
                    .findAny()
                    .isPresent();
            if (!itemFound) {
                System.out.println("Item not found.");
            }
        }
        else if (choice.equalsIgnoreCase("name")) {
            System.out.print("Enter item name to search: ");
            String name = scanner.nextLine();
            itemFound = items.stream()
                    .filter(item -> item.name.equalsIgnoreCase(name))
                    .peek(System.out::println)
                    .findAny()
                    .isPresent();
            if (!itemFound) {
                System.out.println("Item not found.");
            }
        }
        else {
            System.out.println("Invalid choice.");
        }
    }

    private static void updateItem() {        // update item
        System.out.print("Enter item code to update: ");
        String code = scanner.nextLine();
        for (Item item : items) {
            if (item.code.equalsIgnoreCase(code)) {
                System.out.print("Enter new name (leave blank to skip): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    item.name = newName;
                }

                System.out.print("Enter new price (leave blank to skip): ");
                String newPriceInput = scanner.nextLine();
                if (!newPriceInput.isEmpty()) {
                    item.price = Double.parseDouble(newPriceInput);
                }

                System.out.print("Enter new quantity (leave blank to skip): ");
                String newQuantityInput = scanner.nextLine();
                if (!newQuantityInput.isEmpty()) {
                    item.quantity = Integer.parseInt(newQuantityInput);
                }

                System.out.print("Enter new currency (leave blank to skip): ");
                String newCurrency = scanner.nextLine();
                if (!newCurrency.isEmpty()) {
                    item.currency = newCurrency;
                }

                System.out.print("Enter new unit (leave blank to skip): ");
                String newUnit = scanner.nextLine();
                if (!newUnit.isEmpty()) {
                    item.unit = newUnit;
                }

                System.out.println("Item updated successfully.");
                return;
            }
        }
        System.out.println("Item not found.");
    }


    private static void ListItems() {       // items list
        if (items.isEmpty()) {
            System.out.println("There is no item.");
        }
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private static void manageSalesOrders() {       // sales order menu
        while (true) {
            System.out.println("\nSales Order Menu:");
            System.out.println("1. Create Sales Order");
            System.out.println("2. Update Sales Order");
            System.out.println("3. Remove Sales Order");
            System.out.println("4. Search Sales Order");
            System.out.println("5. Sales Orders List");
            System.out.println("6. Refund Order");
            System.out.println("7. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addSalesOrder();
                    break;
                case 2:
                    updateSalesOrder();
                    break;
                case 3:
                    removeSalesOrder();
                    break;
                case 4:
                    searchSalesOrder();
                    break;
                case 5:
                    ListSalesOrders();
                    break;
                case 6:
                    refundSalesOrder();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addSalesOrder() {          // add sales order
        System.out.print("\nEnter order ID: ");
        String orderID = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter payment method: ");
        String paymentMethod = scanner.nextLine();

        SalesOrder salesOrder = new SalesOrder(orderID, customerName, paymentMethod);
        double totalAmount = 0;

        while (true) {
            System.out.print("Enter item code (or type 'exit' to finish): ");
            String itemCode = scanner.nextLine();

            if (itemCode.equalsIgnoreCase("exit")) {
                if (totalAmount > 0) {
                    salesOrders.add(salesOrder); // Add the completed sales order to the list
                    System.out.println("Sales order added successfully.");
                } else {
                    System.out.println("No items were added to the order.");
                }
                return; // Exit the method after the sales order is completed or canceled
            }

            // Find the item in the list by its code
            Item selectedItem = null;
            for (Item item : items) {
                if (item.code.equalsIgnoreCase(itemCode)) {
                    selectedItem = item;
                    break;
                }
            }

            if (selectedItem == null) {
                System.out.println("Item not found in item list. Please try again or type 'exit' to cancel.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Clear newline

            // Check if stock is sufficient
            if (quantity > selectedItem.quantity) {
                System.out.println("Insufficient stock. Available quantity: " + selectedItem.quantity);
                continue;
            }

            // Calculate item total and reduce stock
            totalAmount = quantity * selectedItem.price;
            selectedItem.quantity -= quantity; // Reduce stock in item list

            // Add item to sales order with specific quantity
            salesOrder.addItem(selectedItem, quantity);

            // Ask if the user wants to add another item
            while (true) {
                System.out.print("Do you want to add another item (y/n)? ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("y")) {
                    break;  // Go back to the start of the loop for the next item
                } else if (choice.equalsIgnoreCase("n")) {
                    salesOrders.add(salesOrder); // Add the completed sales order to the list
                    System.out.println("Sales order added successfully.");
                    return; // Exit the method after the sales order is completed
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }
        }
    }


    private static void updateSalesOrder() {         // update sales order
        System.out.print("Enter order ID to update: ");
        String orderID = scanner.nextLine();
        for (SalesOrder order : salesOrders) {
            if (order.orderID.equalsIgnoreCase(orderID)) {
                System.out.print("Enter new customer name (leave blank to skip): ");
                String newCustomerName = scanner.nextLine();
                if (!newCustomerName.isEmpty()) {
                    order.customerName = newCustomerName;
                }

                System.out.println("Choose new order status:");
                System.out.println("1. Confirmed");
                System.out.println("2. Shipped");
                System.out.print("Enter choice: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        order.status = "Confirmed";
                        break;
                    case "2":
                        order.status = "Shipped";
                        break;
                    default:
                        System.out.println("Invalid choice. Status not updated.");
                        return;
                }
                order.setLastUpdate(new Date());

                System.out.println("Sales order updated successfully.");
                return;
            }
        }
        System.out.println("Order not found.");
    }

    private static void removeSalesOrder() {        // remove sales order
        System.out.print("Enter order ID to remove: ");
        String orderID = scanner.nextLine();
        boolean remove = salesOrders.removeIf(order -> order.orderID.equalsIgnoreCase(orderID));
        if (remove) {
            System.out.println("Order removed successfully.");
        }
        else {
            System.out.println("Order ID not found.");
        }
    }

    private static void searchSalesOrder() {         // search sales order
        System.out.print("Enter order ID to search: ");
        String orderID = scanner.nextLine();
        boolean search = salesOrders.stream().anyMatch(order -> order.orderID.equalsIgnoreCase(orderID));
        if (search) {
            salesOrders.stream()
            .forEach(System.out::println);
        }
        else {
            System.out.println("Order ID not found.");
        }
    }

    private static void ListSalesOrders() {          // sales order list
        if (salesOrders.isEmpty()) {
            System.out.println("There is no order.");
        }
        for (SalesOrder order : salesOrders) {
            System.out.println(order);
        }
    }

    private static void refundSalesOrder() {      // refund order
        System.out.print("Enter order ID to refund: ");
        String orderID = scanner.nextLine();

        // Cari pesanan berdasarkan orderID
        SalesOrder orderToRefund = null;
        for (SalesOrder order : salesOrders) {
            if (order.orderID.equalsIgnoreCase(orderID)) {
                orderToRefund = order;
                break;
            }
        }

        if (orderToRefund == null) {
            System.out.println("Order not found.");
            return;
        }

        // Cek apakah pesanan sudah dibatalkan sebelumnya
        if (orderToRefund.status.equalsIgnoreCase("Cancelled")) {
            System.out.println("Order has already been cancelled.");
            return;
        }

        // Kembalikan stok item yang ada di dalam pesanan
        for (Item orderedItem : orderToRefund.items) {
            for (Item inventoryItem : items) {
                if (inventoryItem.code.equalsIgnoreCase(orderedItem.code)) {
                    inventoryItem.quantity += orderedItem.quantity;
                    break;
                }
            }
        }

        // Ubah status pesanan menjadi "Cancelled" dan reset totalAmount
        orderToRefund.status = "Cancelled";
        orderToRefund.totalAmount = 0;
        System.out.println("Sales order has been refunded and cancelled.");
    }
}