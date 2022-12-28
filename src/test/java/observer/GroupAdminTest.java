package observer;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {

    public static final Logger logger = LoggerFactory.getLogger(GroupAdminTest.class);

    @Test
    void register() {
        GroupAdmin aAdmin = new GroupAdmin();
        GroupAdmin bAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        ConcreteMember bMember = new ConcreteMember();

        /* Check for registering a member */
        aAdmin.register(aMember);
        assertTrue(aAdmin.cMembers.contains(aMember));

        /* Check for registering same member twice */
        int aAdminListSize = aAdmin.cMembers.size();
        aAdmin.register(aMember);
        assertEquals(aAdmin.cMembers.size(), aAdminListSize);

        /* Check for registering a member to a new group admin, while he has already registered to another */
        bAdmin.register(aMember);
        assertTrue(bAdmin.cMembers.isEmpty());

        /* Check for 2 members hold same object */
        aAdmin.register(bMember);
        assertEquals(aMember.tUsb,bMember.tUsb);

        /* Check for registering null, printing StackTrace */
        aAdmin.register(null);
    }

    @Test
    void unregister() {
        GroupAdmin aAdmin = new GroupAdmin();
        GroupAdmin bAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        ConcreteMember bMember = new ConcreteMember();

        /* Check for unregistering an unsigned member */
        int collectionSize = aAdmin.cMembers.size();
        aAdmin.unregister(aMember);
        assertEquals(aAdmin.cMembers.size(),collectionSize);

        /* Check for unregistering a signed member */
        aAdmin.register(aMember);
        int collectionSizeNow = aAdmin.cMembers.size();
        aAdmin.unregister(aMember);
        assertEquals(aAdmin.cMembers.size(),collectionSizeNow-1);
    }

    @Test
    void insert() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        String s = "insert check";
        aAdmin.insert(0,s);
        assertEquals(aMember.tUsb.toString(),s);
    }

    @Test
    void append() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        String s = "append check";
        aAdmin.append(s);
        assertEquals(aMember.tUsb.toString(),s);
    }

    @Test
    void delete() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        String s = "delete check";
        aAdmin.append(s);
        aAdmin.delete(0,7);
        assertEquals(aMember.tUsb.toString(),"check");
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
        assertEquals(aMember.tUsb.toString(),s);
    }


    //Cannot test notifyObservers because we use shallow copy,
    //they members immediately point to admin's usb when registered.
    @Test
    void notifyObservers() {
    }
}