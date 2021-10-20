import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    public void refactedCodeLines(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        refactedCodeLines();
        LocalTime testOpeningTime = LocalTime.parse("11:00:00");
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(testOpeningTime);
        assertEquals(true,spiedRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        refactedCodeLines();
        LocalTime testOpeningTime = LocalTime.parse("22:00:01");
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(testOpeningTime);
        assertEquals(false,spiedRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        refactedCodeLines();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        refactedCodeLines();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        refactedCodeLines();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  //>>>>>>>>>>>>>>>>>>>>>>>>>>>Total Order Amount<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void totalamount_for_selected_items_which_exist_in_item_list_should_display_total_cost(){
        int totalAmount = 0;
        refactedCodeLines();
        List<String> selectedItems = Arrays.asList("Sweet corn soup","Vegetable lasagne");
        totalAmount = restaurant.totalOrderCost(selectedItems);
        assertEquals(388,totalAmount);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<Total Order Amount>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



}