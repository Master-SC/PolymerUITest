Feature: Purchase 2 Items - Happy Path
  Scenario: Successfully purchase items from Men’s and Ladies’ Outwear categories

    # Step 1: Add Men's Outwear item to cart with Size XL and Quantity 2
    Given I am on the Home Page
    When I navigate to "Men's Outerwear" Section
    And I Select an item of my choice with Size "XL" and Quantity "2"
    Then the item should be "Added to cart" successfully

    # Step 2: Verify item is added successfully
    When I view the cart
    Then I should see the "Men's" Outwear item with Size "XL" and Quantity "2" in the cart

    # Step 3: Return to Home Page to add the second item
    When I click on "SHOP" to go back to the Home Page

    # Step 4: Add Ladies' Outwear item to cart with Size XS and Quantity 3
    When I navigate to "Ladies Outerwear" Section
    And I Select an item of my choice with Size "XS" and Quantity "3"
    Then the item should be "Added to cart" successfully

    # Step 5: Verify the cart reflects the addition of both items and Confirm size, Quantity, order are correct
    When I view the cart
    Then I should see both the "Men's" Outwear with Size "XL" and Quantity "2" at line "1"
    And the "Ladies" Outwear with Size "XS" and Quantity "3" at line "2" in the cart

    # Step 6: Confirm the total price is showing correctly
    Then The Total should reflect the correct amount based on the items selected

    # Step 7: Change the quantity of "Ladies' Outwear" item to 1 and verify price update
    When I update the quantity of the "Ladies" Outwear item to "1"
    Then the cart should reflect the updated quantity "1"
    And the Total should be recalculated correctly

    # Step 8: Proceed to Checkout
    When I click on "CHECKOU" Button

    # Step 9: Complete Checkout Form using data from Excel
    And I enter my account information as follows:
#      Account Information
#      | Email         | abc@abc.com      |
#      | Phone         | 1111111111       |
#    Shipping Information
#      | Address       | 1 abc street     |
#      | City          | abc              |
#      | State         | abc              |
#      | Zip           | 123456           |
#      | Country       | Canada           |
#    Payment details
#      | Cardholder    | ABC ABC          |
#      | Card Number   | 1111111111111111 |
#      | Expiry Date   | Feb 2026         |
#      | CVV           | 123              |

    # Step 10: Place the order
    When I click on "Place Order" at Checkout Page

    # Step 11: Confirm Thank You message appears
    Then I should see a confirmation message saying "Thank you"

    # Step 12: Complete the process by clicking on Finish
    When I click on "FINISH" at Confirmation Page
    Then I should be redirected to the Home Page