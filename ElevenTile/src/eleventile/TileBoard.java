/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eleventile;

/**
 *
 * @author James
 */
public class TileBoard {

    // private member variables for the current state of the board and the final state
    private final String _packed_state;
    private final String _final_state_packed;

    // getter for the packed state of the board
    public String get_packed_state() {
        return this._packed_state;
    }

    // constructor which initialises the board's packed current and final states
    public TileBoard(String input_state, String final_state) {
        this._packed_state = input_state;
        this._final_state_packed = final_state;
    }

    // method to find position of the blank tile in the packed state
    public int return_blank_pos() {
        for (int i = 0; i != this._packed_state.length(); i++) {
            Character test = this._packed_state.charAt(i);
            if (test.equals('_')) {
                return i;
            }
        }
        return 0;
    }
    
    // method to return up move as string
    public String return_up_string(){
        int blank = this.return_blank_pos();
        String result = "";
        if (blank > 3 && this._packed_state.charAt(blank-4) != '+'){
            for (int i = 0; i != this._packed_state.length(); i++){
                if (i == blank - 4){
                    result += this._packed_state.charAt(blank);
                } else if (i == blank){
                    result += this._packed_state.charAt(blank - 4);
                } else {
                    result += this._packed_state.charAt(i);
                }
            }
            return result;
        } else {
            return null;
        }
    }
    
    // method to return right move as string
    public String return_right_string(){
        int blank = this.return_blank_pos();
        String result = "";
        if (blank != 3 && blank != 7 && blank != 11 && blank != 15 && this._packed_state.charAt(blank+1) != '+'){
            for (int i = 0; i != this._packed_state.length(); i++){
                if (i == blank + 1){
                    result += this._packed_state.charAt(blank);
                } else if (i == blank){
                    result += this._packed_state.charAt(blank + 1);
                } else {
                    result += this._packed_state.charAt(i);
                }
            }
            return result;
        } else {
            return null;
        }
    }
    
    // method to return down move as string
    public String return_down_string(){
        int blank = this.return_blank_pos();
        String result = "";
        if (blank < 12 && this._packed_state.charAt(blank+4) != '+'){
            for (int i = 0; i != this._packed_state.length(); i++){
                if (i == blank + 4){
                    result += this._packed_state.charAt(blank);
                } else if (i == blank){
                    result += this._packed_state.charAt(blank + 4);
                } else {
                    result += this._packed_state.charAt(i);
                }
            }
            return result;
        } else {
            return null;
        }
    }
    
    // method to return left move as string
    public String return_left_string(){
        int blank = this.return_blank_pos();
        String result = "";
        if (blank != 0 && blank != 4 && blank != 8 && blank != 12 && this._packed_state.charAt(blank-1) != '+'){
            for (int i = 0; i != this._packed_state.length(); i++){
                if (i == blank - 1){
                    result += this._packed_state.charAt(blank);
                } else if (i == blank){
                    result += this._packed_state.charAt(blank - 1);
                } else {
                    result += this._packed_state.charAt(i);
                }
            }
            return result;
        } else {
            return null;
        }
    }
}
