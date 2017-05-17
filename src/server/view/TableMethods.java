package server.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

class TableMethods {
    //добавляем в таблицу : имя файла, имя пользователя, откуда блокируем, докуда блокируем, id строка
    //добавим на формочку информашку о последнем редактируемом файле
    static synchronized void addToTable(JTable table, String filename, String username, String start, String end, String UUID)
    {
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        int row_num = getRowByValue(model, username); //номер строки
        Object[] row = { filename, start + "-" + end, username, UUID};
        //если информашки не было-напишем
        if(row_num == -1)
            model.addRow(row);
        //если была, то вместо нее поставим новую
        else {
            model.removeRow(row_num);
            model.insertRow(row_num, row);
        }
    }

    //ищем по табличке есть ли такой value
    private static int getRowByValue(TableModel model, Object value) {
        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            for (int j = model.getColumnCount() - 1; j >= 0; --j) {
                if(model.getValueAt(i, j) == null)
                    continue;
                if (model.getValueAt(i, j).equals(value)) {
                    // what if value is not unique?
                    return i;
                }
            }
        }
        return -1;
    }
    // если нашли, то делетим, это когда перестали редактировать/отключились
    static synchronized void delFromTable(JTable table, String username)
    {
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        int row_num = getRowByValue(model, username);
        if(row_num == -1)  
            System.out.println("Error: user not found!");
        else 
            model.removeRow(row_num);
    }
}
