// See: http://www.gebish.org/manual/current/configuration.html

import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

environments {

	chrome {
		ChromeDriverManager.getInstance().setup()
		driver = { new ChromeDriver() }
	}

	chromeHeadless {
		driver = {
			ChromeOptions o = new ChromeOptions()
			o.addArguments('headless')
			new ChromeDriver(o)
		}
	}
}
