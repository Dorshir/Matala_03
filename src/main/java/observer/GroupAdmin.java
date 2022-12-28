package observer;
import jdk.jfr.StackTrace;

import java.awt.*;
import java.util.ArrayList;

import static observer.JvmUtilities.jvmInfo;

public class GroupAdmin implements Sender{

    final ArrayList<ConcreteMember> cMembers= new ArrayList<>();
    private UndoableStringBuilder usb = new UndoableStringBuilder();

    /**
     * This method register a ConcreteMember to the observables list, then notify this ConcreteMember
     * @param obj Member object (in our case, only ConcreteMember) to register
     */
    @Override
    public void register(Member obj) {
        try{
              if (((ConcreteMember) obj).flag != false) {
                  System.out.println("Member is already registered to another GroupAdmin.");
              } else if (this.cMembers.contains(obj)) {
                  System.out.println("Member is already registered.");
              } else {
                 cMembers.add((ConcreteMember) obj);
                 obj.update(usb);
              }
            }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * This method unregister a ConcreteMember from the observables list, updating Member's usb to null if success
     * @param obj Member object (in our case, only ConcreteMember) to unregister
     */
    @Override
    public void unregister(Member obj) {
        if (!this.cMembers.contains(obj)) {
            System.out.println("Member is not registered yet.");
            return;
        }
        cMembers.remove((ConcreteMember) obj);
        obj.update(null);
    }


    @Override
    public void insert(int offset, String obj) {
        UndoableStringBuilder usbTemp = this.usb;
        usb.insert(offset, obj);
        if(!usb.toString().equals(usbTemp)) {
            notifyObservers();
            System.out.println("Updated the object!");
        }
    }

    @Override
    public void append(String obj) {
        UndoableStringBuilder usbTemp = this.usb;
        usb.append(obj);
        if(!usb.toString().equals(usbTemp)) {
            notifyObservers();
            System.out.println("Updated the object!");
        }
    }

    @Override
    public void delete(int start, int end) {
        UndoableStringBuilder usbTemp = this.usb;
        usb.delete(start,end);
        if(!usb.toString().equals(usbTemp)) {
            notifyObservers();
            System.out.println("Updated the object!");
        }
    }

    @Override
    public void undo() {
        UndoableStringBuilder usbTemp = this.usb;
        usb.undo();
        if(!usb.toString().equals(usbTemp)) {
            notifyObservers();
            System.out.println("Updated the object!");
        }
    }

    /**
     * This method notify all ConcreteMembers in the observables list. Calling their update() method.
     */
    public void notifyObservers(){
        for (ConcreteMember cm: cMembers) {cm.update(usb);
        }
    }
}
