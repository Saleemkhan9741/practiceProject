package reqres;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.saleem.utils.dbutils.HrDbWrapper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void setUp(){
        LOGGER.info("Setting up ");
    }

    @AfterSuite
    private void tearDown(){
        LOGGER.info("Closing all connections");
        HrDbWrapper.getInstance().closeConnections();
    }
}
