package ru.job4j.ood.lsp.menu;

import java.util.Iterator;

public class MenuPrinterClass implements MenuPrinter {

    private String createIndent(String number) {
        int indentLevel = (int) number.chars().filter(ch -> ch == '.').count() - 1;
        return "    ".repeat(Math.max(0, indentLevel));
    }

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo item = iterator.next();
            String indent = createIndent(item.getNumber());
            System.out.println(indent + item.getName());
        }
    }
}