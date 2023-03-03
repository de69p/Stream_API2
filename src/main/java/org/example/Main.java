package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product(Type.BOOK, 300, false, LocalDate.of(2023, 2, 12)),
                new Product(Type.BOOK, 200, false, LocalDate.of(2023, 1, 15)),
                new Product(Type.BOOK, 270, true, LocalDate.of(2023, 2, 9)),
                new Product(Type.BOOK, 70, true, LocalDate.of(2023, 3, 1)),
                new Product(Type.MAGAZINE, 150, false, LocalDate.of(2023, 1, 30)),
                new Product(Type.MAGAZINE, 80, true, LocalDate.of(2023, 2, 14)),
                new Product(Type.MAGAZINE, 220, false, LocalDate.of(2023, 2, 5)),
                new Product(Type.TOY, 50, true, LocalDate.of(2022, 10, 31)),
                new Product(Type.TOY, 80, false, LocalDate.of(2022, 11, 14)),
                new Product(Type.CLOTHING, 270, true, LocalDate.of(2023, 2, 20)),
                new Product(Type.CLOTHING, 300, false, LocalDate.of(2022, 12, 26))
        );

        /** Task 1
         * Реалізувати метод отримання всіх продуктів у вигляді списку,
         * категорія яких еквівалентна “Book” та ціна більш ніж 250.
         */
        List<Product> expensiveBooks = products.stream()
                .filter(p -> p.getType() == Type.BOOK && p.getPrice() > 250)
                .toList();
        System.out.println("Expensive books: " + expensiveBooks);

        /** Task 2
         * Реалізувати метод отримання всіх продуктів як списку,
         * категорія яких еквівалентна “Book” і з можливістю застосування знижки.
         * Фінальний список повинен містити продукти з застосованою знижкою 10%.
         * Так, якщо Продукт A був з ціною 1.0 USD, то його фінальна ціна залишатиметься 0.9 USD
         */
        List<Product> discountedBooks = products.stream()
                .filter(p -> p.getType().equals(Type.BOOK) && p.hasDiscount())
                .peek(p -> p.setPrice(p.getDiscountedPrice()))
                .toList();
        System.out.println("Discounted books: " + discountedBooks);


        /** Task 3
         * Реалізувати метод отримання найдешевшого продукту з категорії “Book”
         * У випадку, якщо жоден продукт не знайдено (ситуація, коли немає продукту з категорією),
         * викинути виняток з повідомленням “Продукт [категорія: ім'я_категорії] не знайдено”.
         */
        Optional<Product> cheapestBook = products.stream()
                .filter(p -> p.getType().equals(Type.BOOK))
                .min(Comparator.comparingDouble(Product::getPrice));
        if (cheapestBook.isPresent()) {
            System.out.println("Cheapest book: " + cheapestBook.get());
        } else {
            throw new NoSuchElementException("Product [category: " + Type.BOOK + "] not found");
        }

        /** Task 4
         * Реалізувати метод отримання трьох останніх доданих продуктів
         */
        List<Product> lastThreeProducts = products.stream()
                .sorted(Comparator.comparing(Product::getDateAdded).reversed())
                .limit(3)
                .toList();
        System.out.println("Last three products: " + lastThreeProducts);

        /** Task 5
         * Реалізувати метод калькуляції загальної вартості продуктів, які відповідають наступним критеріям:
         * - продукт додано протягом поточного року
         * - продукт має тип “Book”
         * - ціна продукту не перевищує 75
         */
        double totalCost = products.stream()
                .filter(p -> p.getType()
                        .equals(Type.BOOK) && p.getPrice() <= 75 && p.getDateAdded().getYear() == LocalDate.now().getYear())
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("Total cost of discounted books added this year: " + totalCost);

        /** Task 6
         * Реалізувати метод групування об'єктів за типом продукту. Таким чином результатом виконання методу
         * буде тип даних “Словник”, що зберігає пару ключ-значення: {тип: список_продуктів}
         */
        Map<Type, List<Product>> productsByType = products.stream()
                .collect(Collectors.groupingBy(Product::getType));
        System.out.println("Products by type: " + productsByType);
    }
}
