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
        logger.info(()-> JvmUtilities.objectFootprint(aMember,bMember));

        /* Check for registering a member */
        aAdmin.register(aMember);
        assertTrue(aAdmin.cMembers.contains(aMember));
        logger.info(()-> JvmUtilities.objectFootprint(aAdmin));
        logger.info(()-> JvmUtilities.objectFootprint(aMember));

        /* Check for registering same member twice */
        logger.info(()-> "Seq.start, Before: \n" + JvmUtilities.objectFootprint(aAdmin));
        int aAdminListSize = aAdmin.cMembers.size();
        aAdmin.register(aMember);
        assertEquals(aAdmin.cMembers.size(), aAdminListSize);
        logger.info(()-> "Seq.end, After: \n" + JvmUtilities.objectFootprint(aAdmin));

        /* Check for registering a member to a new group admin, while he has already registered to another */
        logger.info(()-> "Seq.start, Before: \n" + JvmUtilities.objectFootprint(bAdmin));
        bAdmin.register(aMember);
        assertTrue(bAdmin.cMembers.isEmpty());
        logger.info(()-> "Seq.end, After: \n" + JvmUtilities.objectFootprint(bAdmin));

        /* Check for 2 registered members point the same usb */
        aAdmin.register(bMember);
        assertEquals(aMember.tUsb,bMember.tUsb);
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb,bMember.tUsb));


        /* Check for registering null, printing StackTrace */
        logger.info(()-> "Seq.start, Before: \n" + JvmUtilities.objectTotalSize(aAdmin));
        aAdmin.register(null);
        logger.info(()-> "Seq.end, After: \n" + JvmUtilities.objectTotalSize(aAdmin));

    }

    @Test
    void unregister() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();

        /* Check for unregistering an unsigned member */
        logger.info(()-> "Seq.start, Before: \n" + JvmUtilities.objectTotalSize(aAdmin));
        int collectionSize = aAdmin.cMembers.size();
        aAdmin.unregister(aMember);
        assertEquals(aAdmin.cMembers.size(),collectionSize);
        logger.info(()-> "Seq.end, After: \n" + JvmUtilities.objectTotalSize(aAdmin));

        /* Check for unregistering a signed member */
        logger.info(()-> "Seq.start, Before: \n" + JvmUtilities.objectTotalSize(aAdmin));
        aAdmin.register(aMember);
        int collectionSizeNow = aAdmin.cMembers.size();
        aAdmin.unregister(aMember);
        assertEquals(aAdmin.cMembers.size(),collectionSizeNow-1);
        logger.info(()-> "Seq.end, After: \n" + JvmUtilities.objectTotalSize(aAdmin));
    }

    @Test
    void insert() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb));
        String s = "insert check";
        aAdmin.insert(0,s);
        assertEquals(aMember.tUsb.toString(),s);
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb));

    }

    @Test
    void append() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb));
        String s = "append check";
        aAdmin.append(s);
        assertEquals(aMember.tUsb.toString(),s);
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb));
    }

    @Test
    void delete() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        String s = "delete check";
        aAdmin.append(s);
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb));
        aAdmin.delete(0,7);
        assertEquals(aMember.tUsb.toString(),"check");
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb));
    }

    @Test
    void undo() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        aAdmin.register(aMember);
        String s = "undo check";
        aAdmin.append(s);
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb));
        aAdmin.delete(0,6);
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb));
        aAdmin.undo();
        assertEquals(aMember.tUsb.toString(),s);
        logger.info(()-> JvmUtilities.objectFootprint(aMember.tUsb));
    }


    //Cannot test notifyObservers because we use shallow copy,
    //they members immediately point to admin's usb when registered.
    @Test
    void notifyObservers() {
    }
}