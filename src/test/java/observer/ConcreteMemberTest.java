package observer;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {

    public static final Logger logger = LoggerFactory.getLogger(ConcreteMemberTest.class);

    @Test
    void update() {
        GroupAdmin aAdmin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        ConcreteMember bMember = new ConcreteMember();
        aAdmin.register(aMember);
        aAdmin.register(bMember);

        /* Could test calling update() here for each member,
        /  but update() being called already when registering.
        /  hence we check for holding the right usb pointer: */

        String s = "update check";
        aAdmin.append(s);
        assertAll("This is my check list",
                ()->assertEquals(aMember.tUsb,bMember.tUsb),
                ()->assertEquals(aMember.tUsb.toString(),s));
    }
}