package reqres;

import org.saleem.restclient.ReqresClient;
import org.testng.annotations.Test;

public class ListOfUsersTest extends BaseTest{

    @Test(groups = "smoke")
    public void verifyListOfUsers(){
        ReqresClient.getInstance().getListOfUsers();
    }
}
