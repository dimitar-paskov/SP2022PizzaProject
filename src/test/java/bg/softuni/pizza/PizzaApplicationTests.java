package bg.softuni.pizza;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PizzaApplicationTests {

	@Test
	void contextLoads() throws FileNotFoundException, IOException {
		
//		String resourceName = "application.yml";
//
//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource(resourceName).getFile());
//		String absolutePath = file.getAbsolutePath();
//
//		System.out.println(absolutePath);
//		
//		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//			   String line;
//			   while ((line = br.readLine()) != null) {
//			       System.out.println(line);
//			   }
//			}
	}

}
