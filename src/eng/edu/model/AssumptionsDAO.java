/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.model;

/**
 *
 * @author deeptichavan
 */
public class AssumptionsDAO {
    
    String assumption;
    boolean isCorrect;

    public AssumptionsDAO(String assumption, boolean isCorrect) {
        this.assumption = assumption;
        this.isCorrect = isCorrect;
    }

    public String getAssumption() {
        return assumption;
    }

    public void setAssumption(String assumption) {
        this.assumption = assumption;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    
}
