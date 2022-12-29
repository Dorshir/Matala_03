package observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UndoableStringBuilderTest {

    @Test
    void append() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        assertEquals("to be or not to be", usb.append("to be or not to be").toString());
    }

    @Test
    void delete() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("to be or not to be");
        int length = usb.toString().length();

        assertAll("This is my check group",
                () -> assertEquals("to or not to be", usb.delete(3, 6).toString()),
                () -> assertEquals("to or not to be", usb.delete(-1,7).toString()),
                () -> assertEquals("to or not to be", usb.delete(length+1,length+2).toString()));
    }

    @Test
    void insert() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("to be or to be");
        assertAll("This is my check group",
                () -> assertEquals("to be or not to be", usb.insert(9, "not ").toString()),
                () -> assertEquals("to be or not to be", usb.insert(-1,"test1").toString()),
                () -> assertEquals("to be or not to be", usb.insert(usb.toString().length()+1,"test1").toString()));
    }

    @Test
    void replace() {
        UndoableStringBuilder usbTestOne = new UndoableStringBuilder();
        UndoableStringBuilder usbTestTwo = new UndoableStringBuilder();
        usbTestOne.append("to be or not to be");
        assertAll("This is my check group",
                () -> assertEquals("to be or definitely to be",usbTestOne.replace(9, 12, "definitely").toString()),
                () -> assertEquals("to be or definitely to be",usbTestOne.replace(-1, 12, "test1").toString()),
                () -> assertEquals("to be or definitely to be",usbTestOne.replace(12, 5, "test2").toString()),
                () -> assertEquals("to be or definitely to be",usbTestOne.replace(usbTestOne.toString().length()+1, usbTestOne.toString().length()+2, "test3").toString()),
                () -> assertEquals("to be or definitely to be",usbTestOne.replace(9, 12, null).toString()));
    }

    @Test
    void reverse() {
        UndoableStringBuilder usbOne = new UndoableStringBuilder();
        UndoableStringBuilder usbTwo = new UndoableStringBuilder();
        usbOne.append("to be or not to be");
        usbTwo.append(null);
        String t = "eb ot ton ro eb ot";
        String s = "llun";
        assertAll("This is my check group",
                () -> assertEquals(t, usbOne.reverse().toString()),
                () -> assertEquals(s,usbTwo.reverse().toString()));
    }

    @Test
    void undo() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        UndoableStringBuilder usbTest = new UndoableStringBuilder();
        int length = usb.toString().length();
        String s1 = "to be or not to";
        String s2 = " be";
        usb.append(s1);
        usb.append(s2);
        usb.undo();
        usbTest.undo();
        assertAll("This is my check group",
                () -> assertEquals(s1,usb.toString()),
                () -> assertEquals("",usbTest.toString()));
    }
}