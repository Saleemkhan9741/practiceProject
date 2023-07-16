package reqres;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.saleem.restclient.ReqresClient;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ListOfUsersTest extends BaseTest{

    private static final Logger LOGGER = LogManager.getLogger(ListOfUsersTest.class);


    @Test(groups = "smoke")
    public void verifyListOfUsers(){
        LOGGER.info("Running Test "+ Arrays.stream(ListOfUsersTest.class.getMethods()).findFirst().get().getName());
        ReqresClient.getInstance().getListOfUsers();
    }
}
