```markdown

You will be helping me in creating Leetcode posts. Input would be provided in three parts, Description HTML(Description of code), the URL of page and Code block HTML(with it's solution-code contents will be used as output ). You'll ask for the ones which aren't provided until you get all three pieces of input.

Generate the response in (Key: Value) format. Values will be filled by you. Keys are as follows:

"Filename": Use question description to generate a filename to be used. Upper-camelcase the question title to generate it
"Code": DO NOT CHANGE THE CODE LOGIC. Change the filename. Retain existing comments if present. Correct spelling mistakes if found and add a few emojis. If no comments describing the code are present, add block-comments in the code with some details of what's happening within the code, URL, Complexity-info and tags as provided in example. Add short line comments for really crucial details to describe the step.
"Tags": values to be `-` separated lowercase words for each individual tag's word, preceded by #, add the difficulty tag in the end
"Complexity": time-complexity & space-complexity, "java-filename"(make it up in Upper camel case), "page link". 
"Index row": Also add in a row for .md file with following format.

```markdown
| [QNo. Question title](file-link) | #tag1 #tag2 #tag3 #question-difficulty |
```

Output would be something like:

Filename: MinNumberOfSwapsToMakeStrBalanced.java
Code: 

```java
public class MinNumberOfSwapsToMakeStrBalanced {
  /**
   * https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
   * Simply, kept track of the number of '[' and ']' in the string. coming from left or right.
   * If any voilation is found, swap the characters and reset the counters. Could have used stack as well.
   * 
   * TC: O(n) SC: O(1)
   * #stack #greedy #string #medium
   */
  public int minSwaps(String s) {
    int res = 0;
    char []str = s.toCharArray();
    int l=0, r=str.length-1, cntl = 0, cntr = 0, cnt=0;
    for(char c: str){
      if(c==']') cnt++;
      else if(c=='[') cnt--;
    }
    if(cnt!=0) return -1;
    while(l<r){
      while(l<r){
        if(str[l]=='[') cntl++;
        else if(str[l]==']') cntl--;
        if(cntl>=0){l++;}else break;
      }
      while(l<r){
        if(str[r]==']') cntr++;
        else if(str[r]=='[') cntr--;
        if(cntr>=0){r--;}else break;
      }
      if(cntl<0 && cntr<0){
        char ch = str[l];
        str[l] = str[r];
        str[r] = ch;
        cntr = cntl = 0;
        res++;
      }
    }
    return res;
  }
}
```

Complexity: TC: O(n) SC: O(1)
Tags: #stack #greedy #string #medium
Index row:

```markdown
| [1963. Minimum Number of Swaps to Make the String Balanced](.dsa/leetcode/kuiperBelt/MinNumberOfSwapsToMakeStrBalanced.java) | #stack #greedy #string #medium |
```

```