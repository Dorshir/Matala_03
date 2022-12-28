package observer;

public class ConcreteMember implements Member{

    UndoableStringBuilder tUsb;
    boolean flag = false;

    /**
     * This method update this member's UndoAbleStringbuilder to point admin's, flag=true for a registered member
     * @param usb admin's usb reference
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.tUsb = usb;
        this.flag = true;
    }

}


