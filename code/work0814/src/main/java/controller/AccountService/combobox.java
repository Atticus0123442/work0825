package controller.AccountService;

//宣告 ComboItem 類別
public class combobox {
 private String text;
 private String value;

 public combobox(String text, String value) {
     this.text = text;
     this.value = value;
 }

 public String toString() {
     return text;
 }

 public String getValue() {
     return value;
 }
}