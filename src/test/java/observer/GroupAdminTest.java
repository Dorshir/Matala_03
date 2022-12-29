package observer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {

    public static final Logger logger = LoggerFactory.getLogger(GroupAdminTest.class);
    @BeforeAll
    static void jvmInfo(){
        logger.info(()-> JvmUtilities.jvmInfo());
    }

    @Test
    void register() {
        GroupAdmin aAdmin = new GroupAdmin();
        GroupAdmin bAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        ConcreteMember bMember = new ConcreteMember();

        /* Check for registering a member */
        aAdmin.register(aMember);
        assertTrue(aAdmin.getcMembers().contains(aMember));

        /* Check for registering same member twice */
        int aAdminListSize = aAdmin.getcMembers().size();
        aAdmin.register(aMember);
        assertEquals(aAdmin.getcMembers().size(), aAdminListSize);

        /* Check for registering a member to a new group admin, while he has already registered to another */
        bAdmin.register(aMember);
        assertTrue(bAdmin.getcMembers().isEmpty());

        /* Check for 2 registered members point the same usb */
        aAdmin.register(bMember);
        assertEquals(aMember.gettUsb(), bMember.gettUsb());

        /* Check for registering null, printing StackTrace */
        aAdmin.register(null);
    }

    @Test
    void unregister() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();

        /* Check for unregistering an unsigned member */
        int collectionSize = aAdmin.getcMembers().size();
        aAdmin.unregister(aMember);
        assertEquals(aAdmin.getcMembers().size(),collectionSize);

        /* Check for unregistering a signed member */
        aAdmin.register(aMember);
        int collectionSizeNow = aAdmin.getcMembers().size();
        aAdmin.unregister(aMember);
        assertEquals(aAdmin.getcMembers().size(),collectionSizeNow-1);
    }

    @Test
    void insert() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        String s = "insert check";
        aAdmin.insert(0,s);
        assertEquals(aMember.gettUsb().toString(),s);
    }

    @Test
    void append() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        String s = "append check";
        aAdmin.append(s);
        assertEquals(aMember.gettUsb().toString(),s);
    }

    @Test
    void delete() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        String s = "delete check";
        aAdmin.append(s);
        aAdmin.delete(0,7);
        assertEquals(aMember.gettUsb().toString(),"check");
    }

    @Test
    void undo() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        String s = "undo check";
        aAdmin.append(s);
        aAdmin.delete(0,6);
        aAdmin.undo();
        assertEquals(aMember.gettUsb().toString(),s);
    }

    //Cannot test notifyObservers because we use shallow copy,
    //they members immediately point to admin's usb when registered.
    @Test
    void notifyObservers() {
    }
}