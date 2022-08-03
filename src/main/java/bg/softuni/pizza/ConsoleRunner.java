package bg.softuni.pizza;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import bg.softuni.pizza.service.IngredientService;
import bg.softuni.pizza.service.ProductService;
import bg.softuni.pizza.service.UserService;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ProductService productService;
    private final UserService userService;
	private final IngredientService ingredientService;

    public ConsoleRunner(ProductService productService, UserService userService, IngredientService ingredientService) {
        this.productService = productService;
        this.userService = userService;
		this.ingredientService = ingredientService; 
    }

    @Override
    public void run(String... args) throws Exception {
    	this.ingredientService.initIngredients();
        this.productService.initProducts();
        this.userService.initUsersAndRoles();
    }
}