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

   private int [][]sheet;
   public DesignSpreadsheet(int rows) {
     sheet = new int[26][rows];
   }
   
   public void setCell(String cell, int value) {
     int []rc = retrc(cell);
     sheet[rc[0]][rc[1]] = value;
   }
   
   public void resetCell(String cell) {
     int []rc = retrc(cell);
     sheet[rc[0]][rc[1]] = 0;
   }
   
   private int []retrc(String cell){
     int []rc = new int[]{
       (int)(cell.charAt(0)-'A'), 
       Integer.valueOf(cell.substring(1))-1
     };
     return rc;
   }
   
   public int getValue(String formula) {
     String []operands = formula.substring(1).split("\\+");
     int res = 0;
     for(String op: operands){
       if(op.charAt(0) >= 'A' && op.charAt(0) <= 'Z'){
         int []rc = retrc(op);
         res += sheet[rc[0]][rc[1]];
       }else{
         res += Integer.valueOf(op);
       }
     }
     return res;
   }
}