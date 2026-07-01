package com.example;

import org.junit.*;
import com.example.UserService.User;
import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;
    private User testUser1;
    private User testUser2;
    private User testUser3;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("=== Starting UserService Tests ===");
    }

    @Before
    public void setUp() {
        System.out.println("Setting up test environment...");
        userService = new UserService();
        
        testUser1 = new User(1, "John Doe", "john@example.com");
        testUser2 = new User(2, "Jane Smith", "jane@example.com");
        testUser3 = new User(3, "Bob Johnson", "bob@example.com");
        
        userService.addUser(testUser1);
        userService.addUser(testUser2);
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning up test environment...");
        userService.clearAllUsers();
        userService = null;
        testUser1 = null;
        testUser2 = null;
        testUser3 = null;
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("=== Finished UserService Tests ===");
    }

    @Test
    public void testAddUser_AAA_Pattern() {
        User newUser = new User(3, "Alice Wonder", "alice@example.com");
        int expectedCount = userService.getUserCount() + 1;
        
        userService.addUser(newUser);
        User retrievedUser = userService.findUserById(3);
        int actualCount = userService.getUserCount();
        
        assertNotNull("User should be found", retrievedUser);
        assertEquals("User name should match", "Alice Wonder", retrievedUser.getName());
        assertEquals("User count should increase by 1", expectedCount, actualCount);
    }

    @Test
    public void testFindUserById_AAA_Pattern() {
        int userId = 1;
        String expectedName = "John Doe";
        
        User foundUser = userService.findUserById(userId);
        
        assertNotNull("User should be found", foundUser);
        assertEquals("User ID should match", userId, foundUser.getId());
        assertEquals("User name should match", expectedName, foundUser.getName());
    }

    @Test
    public void testRemoveUser_AAA_Pattern() {
        int userIdToRemove = 2;
        int expectedCount = userService.getUserCount() - 1;
        userService.removeUser(userIdToRemove);
        User removedUser = userService.findUserById(userIdToRemove);
        int actualCount = userService.getUserCount();
        
        assertNull("User should not be found after removal", removedUser);
        assertEquals("User count should decrease by 1", expectedCount, actualCount);
    }

    @Test
    public void testGetAllUsers_AAA_Pattern() {
        int expectedSize = 2; 
        
        java.util.List<User> allUsers = userService.getAllUsers();
        
        assertNotNull("User list should not be null", allUsers);
        assertEquals("User count should match", expectedSize, allUsers.size());
        assertTrue("Users list should contain testUser1", allUsers.contains(testUser1));
        assertTrue("Users list should contain testUser2", allUsers.contains(testUser2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateUser_AAA_Pattern() {
        User duplicateUser = new User(1, "Duplicate", "duplicate@example.com");
        
        userService.addUser(duplicateUser);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullUser_AAA_Pattern() {
        userService.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentUser_AAA_Pattern() {
        int nonExistentId = 999;
        userService.removeUser(nonExistentId);
    }

    @Test
    public void testClearAllUsers_AAA_Pattern() {
        int expectedCount = 0;
        userService.clearAllUsers();
        assertEquals("User count should be 0", expectedCount, userService.getUserCount());
        assertTrue("User list should be empty", userService.getAllUsers().isEmpty());
    }
    @Test
    public void testUserUpdate_AAA_Pattern() {
        int userId = 1;
        String newName = "Johnathan Doe";
        String newEmail = "johnathan@example.com";
        User user = userService.findUserById(userId);
        user.setName(newName);
        user.setEmail(newEmail);
        User updatedUser = userService.findUserById(userId);
        assertEquals("Updated user name should match", newName, updatedUser.getName());
        assertEquals("Updated user email should match", newEmail, updatedUser.getEmail());
    }

    @Test
    public void testGetUserCount_AAA_Pattern() {
        int expectedCount = 2;
        int actualCount = userService.getUserCount();
        
        assertEquals("User count should match", expectedCount, actualCount);
    }

    @Test
    public void testUserServiceOperations_AAA_Pattern() {
        User newUser = new User(3, "Charlie Brown", "charlie@example.com");
        int initialCount = userService.getUserCount();
        userService.addUser(newUser);
        User foundUser = userService.findUserById(3);
        int countAfterAdd = userService.getUserCount();
        userService.removeUser(1);
        int countAfterRemove = userService.getUserCount();
        
        assertNotNull("New user should be found", foundUser);
        assertEquals("User count should increase by 1", initialCount + 1, countAfterAdd);
        assertEquals("User count should decrease by 1 after removal", initialCount, countAfterRemove);
        assertNull("Removed user should not be found", userService.findUserById(1));
    }
}