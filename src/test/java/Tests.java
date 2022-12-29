import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

public class Tests {

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility

    @BeforeAll
    static void jvmInfo(){
        logger.info(()-> JvmUtilities.jvmInfo());
    }
    @Test
    void test(){

        GroupAdmin Admin = new GroupAdmin();
        ConcreteMember aMember = new ConcreteMember();
        ConcreteMember bMember = new ConcreteMember();

        Admin.register(aMember);
        Admin.register(bMember);
        logger.info(()-> "Register check:\n" + JvmUtilities.objectFootprint(aMember,bMember));
        logger.info(()-> JvmUtilities.objectFootprint(Admin));

        Admin.unregister(aMember);
        logger.info(()-> "Unregister check:\n" + JvmUtilities.objectFootprint(aMember));
        logger.info(()-> JvmUtilities.objectFootprint(Admin));

        Admin.append("Append check");
        logger.info(()-> "Append check:\n" + JvmUtilities.objectFootprint(aMember,bMember));
        logger.info(()-> JvmUtilities.objectFootprint(Admin));

        Admin.delete(0,7);
        logger.info(()-> "Delete check:\n" + JvmUtilities.objectFootprint(aMember,bMember));
        logger.info(()-> JvmUtilities.objectFootprint(Admin));


        Admin.insert(4," again");
        logger.info(()-> "Insert check:\n" + JvmUtilities.objectFootprint(aMember,bMember));
        logger.info(()-> JvmUtilities.objectFootprint(Admin));

        Admin.undo();
        Admin.undo();
        logger.info(()-> "Undo check:\n" + JvmUtilities.objectFootprint(aMember,bMember));
        logger.info(()-> JvmUtilities.objectFootprint(Admin));
    }
}
