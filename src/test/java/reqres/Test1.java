package reqres;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.saleem.model.User;
import org.saleem.restclient.ReqresClient;
import org.saleem.utils.SerializationHelper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test1 extends BaseTest{

    private static final Logger LOGGER = LogManager.getLogger(Test1.class);

    @DataProvider(name = "createUsers")
    private Object[][] createUsers(){
        return new Object[][]{
                {"saleem","leader"},
                {"pooja","SDET"}
        };
    }

    @Test(dataProvider = "createUsers")
    public void verifyUserCreation(String name, String job) throws JsonProcessingException {
        User user = User.builder().name(name).job(job).build();
        User createdUser = ReqresClient.getInstance()
                .createUsers(SerializationHelper.getObjectMapper().writeValueAsString(user));
        Assert.assertEquals(createdUser.getJob(),job);
        Assert.assertEquals(createdUser.getName(),name);
    }

}
