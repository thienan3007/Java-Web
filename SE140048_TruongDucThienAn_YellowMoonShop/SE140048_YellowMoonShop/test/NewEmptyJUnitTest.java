/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import antdt.users.UsersDAO;
import antdt.users.UsersDTO;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antru
 */
public class NewEmptyJUnitTest {
    
    @Test //biến 1 hàm đi kèm cái Annotation này thành hàm main()
    public void checkFatorialGivenRightArgumentReturnsWell() throws SQLException, ClassNotFoundException {
        UsersDTO b = null; // tui kì vọng 120 trả về
        UsersDTO a = UsersDAO.checkLogin(1, "123");// nếu tui tính 5!
        
        Assert.assertEquals(b, a);
        
        
    }
    
}
