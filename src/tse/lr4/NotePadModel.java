package tse.lr4;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author aNNiMON
 */
public class NotePadModel implements ListModel {

    @Override
    public int getSize() {
        return NotePadManager.getInstance().getNotepads().size();
    }

    @Override
    public Object getElementAt(int index) {
        return NotePadManager.getInstance().getNotepads().get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }

}
