package dsa.leetcode.VallesMarineris;

import java.util.HashMap;

public class DesignSpreadsheet {

  /**
   * https://leetcode.com/problems/design-spreadsheet/description/
   *
   * Use a hashmap to represent the cells, where the key is the cell reference (e.g., "A1")
   * and the value is the integer stored in the cell.
   *
   * TC: O(1) for setCell and resetCell, O(n) for getValue where n is the length of the formula
   * SC: O(m) where m is the number of cells
   *
   * #array #hash-table #string #design #matrix #medium
   */

  private HashMap<String, Integer> sheet;

  public DesignSpreadsheet(int rows) {
    sheet = new HashMap<>();
  }

  public void setCell(String cell, int value) {
    sheet.put(cell, value);
  }

  public void resetCell(String cell) {
    sheet.put(cell, 0);
  }

  public int getValue(String formula) {
    int res = 0;
    String[] operands = formula.substring(1).split("\\+");
    for (String operand : operands) {
      if (sheet.containsKey(operand)) {
        res += sheet.get(operand);
      } else {
        res += Integer.parseInt(operand);
      }
    }
    return res;
  }
}