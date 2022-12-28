package observer;
import java.util.Objects;
import java.util.Stack;

public class UndoableStringBuilder {
    private StringBuilder sb = new StringBuilder();
    private Stack<String> s = new Stack<String>();

    public UndoableStringBuilder(){
        this.sb = new StringBuilder();
        this.s = new Stack<String>();
    }
    /**
     * push the last sequence to the stack,
     * then appends the specified string to this sequence.
     * @param str a string to append.
     * @return the updated undoable character sequence.
     */
    public UndoableStringBuilder append(String str) {
        this.s.push(sb.toString());
        this.sb.append(str);
        return this;
    }
    /**
     * push the last sequence to the stack,
     * then removes the characters in a substring of this sequence.
     * @param start,end integers for a location in the sequence.
     * @return the updated undoable character sequence.
     */
    public UndoableStringBuilder delete(int start, int end){
        try {
            this.s.push(sb.toString());
            this.sb.delete(start, end);
        }
        catch(StringIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return this;
    }
    /**
     * push the last sequence to the stack,
     * then inserts the string into this sequence.
     * @param offset,str offset integer for a start location in the sequence, a string to insert.
     * @return the updated undoable character sequence.
     */
    public UndoableStringBuilder insert(int offset, String str){
        try{
            this.s.push(sb.toString());
            this.sb.insert(offset, str);
        }
        catch(StringIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return this;
    }
    /**
     * push the last sequence to the stack
     * then replaces the characters in a substring
     * of this sequence with characters in the specified string.
     * @param start,end,str integers for a specific location at this sequence, a string to replace with.
     * @return the updated undoable character sequence
     */
    public UndoableStringBuilder replace(int start,int end, String str){
        try {
            this.s.push(sb.toString());
            this.sb.replace(start, end, str);
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        catch(StringIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return this;
    }
    /**
     * push the last sequence to the stack
     * then causes this character sequence to be replaced by the reverse of the sequence.
     * @return the updated undoable character sequence.
     */
    public UndoableStringBuilder reverse(){
        this.s.push(sb.toString());
        this.sb.reverse();
        return this;
    }
    /**
     * if stack isn't empty, replace this sequence with the last sequence we pushed to the stack.
     */
    public void undo() {
        if (!this.s.isEmpty())
            sb.replace(0, sb.length(), s.pop());
        else {
            System.out.println("Unavailable to undo. No previous associated actions.");
        }
    }

    public String toString() {
        return this.sb.toString();
    }


}